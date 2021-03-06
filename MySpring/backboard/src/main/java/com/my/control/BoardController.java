package com.my.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.dto.ResultBean;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.BoardService;

import net.coobird.thumbnailator.Thumbnailator;
//@Controller
public class BoardController {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private BoardService service;

	@Autowired
	private ServletContext sc;

	@GetMapping("boardlist") 
	@ResponseBody // @ResponseBody -> 스프링 mvc구조의 모듈 사용하지 않겠다.
	public ResultBean<PageBean<Board>> list(@RequestParam(required = false, defaultValue="1") int currentPage) {
		// parameter값 전달이 안 되어도 상관 없고, 전달 되지 않을 시 초기값 1로 주겠다.
		// controller가 front에게 응답할 dto를 더 만들어도 좋음 -> ResultBean
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			PageBean<Board> pb = service.boardList(currentPage);
			rb.setStatus(1);
			rb.setT(pb);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	@GetMapping("searchboard")
	@ResponseBody
	public ResultBean<PageBean<Board>> search(
			@RequestParam(required = false, defaultValue="1") int currentPage, 
			@RequestParam(required = false, defaultValue="") String word){
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		PageBean<Board> pb ;
		try {
			if("".equals(word)) {
				pb = service.boardList(currentPage);
			}else {
				pb= service.searchBoard(word, currentPage);
			}
			rb.setStatus(1);
			rb.setT(pb);
		}catch(FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	@GetMapping("viewboard")
	@ResponseBody
	public ResultBean<Board> viewBoard(int boardNo){
		ResultBean<Board> rb = new ResultBean<>();
		try {
			Board b = service.viewBoard(boardNo);
			rb.setStatus(1);
			rb.setT(b);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	// 파일 업로드 같은 경우는 단위테스트 쉽지 않음 따라서 톰캣 작동 시켜서 Postman으로 테스트할 것
	@PostMapping("/writeboard")
	// @ResponseBody public Map wirte() -> 응답 성공 시 json 문자열로 format하여 응답내용으로 사용 
	@ResponseBody
	// ▼ 응답 성공, 실패 여부만 응답하고 싶음 
	// Json문자열로 응답하고 싶지 않은 것
	public ResponseEntity<?> write(  
			@RequestPart(required = false) List<MultipartFile> letterFiles 
			,@RequestPart(required = false) MultipartFile imageFile
			,Board board
			,String greeting 
			,HttpSession session){
		// greeting 은 샘플용으로 만들어 둔 것 
		// ResponseBody는 무조건 응답 내용을 mvc구조를 쓰지 않겠다 
		// 두 줄의 조합 (Response ~ write) HTTP버전이 먼저 응답되고 응답 상태 코드를 우리가 조절할 수 있음
		// 응답 상태 코드를 200번, 404, 500, 300번 등으로 강제시킬 수 있음 
		// ResponseEntity -> 응답 상태 코드 조절 / 응답 성공 시 200번으로 설정, 내용도 지정 가능
		// 상태코드값을 결정(제어)해서 간단히 응답 -> 네트워크 비용 절감 가능

		// 응답 상태 코드값 조절할 것 
		// 200번 : 파일 업로드 성공, 썸네일 파일 생성 성공 글쓰기 성공 (응답 내용 : 썸네일 파일 내용)
		// 500번 : 파일 업로드 실패. 글쓰기 실패
		// 원래 -> 응답 상태 200번 - status:1 성공, status:0 실패	

		// MultipartFile을 사용하기 위해선 RequestPart어노테이션을 붙여줘야함
		// required = false 인 경우 -> 필수로 첨부하지 않아도 됨

		logger.info("요청전달데이터 title=" + board.getBoardTitle() + ", content=" + board.getBoardContent());
		logger.info("letterFiles.size()=" + letterFiles.size());
		logger.info("imageFile.getSize()=" + imageFile.getSize() + ", imageFile.getOriginalFileName()=" + imageFile.getOriginalFilename());
		logger.info(greeting);


		//게시글내용 DB에 저장
		try {
			//	String loginedId = (String)session.getAttribute("loginInfo");
			//---로그인대신할 샘플데이터--
			String loginedId = "id1";
			//----------------------
			board.setBoardId(loginedId);
			service.writeBoard(board);
			//			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AddException e1) {
			e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //DB저장에 문제가 생기면 500번 error 보냄
		}

		// 파일 경로 생성
		//	String saveDirectory = "D:\\files" ; 
		String saveDirectory = sc.getInitParameter("filePath"); // web.xml 파일에 param-name으로 filePath 설정 해 둠
		if (! new File(saveDirectory).exists()) {
			logger.info("업로드 실제경로생성");
			new File(saveDirectory).mkdirs();
		}

		int wroteBoardNo = board.getBoardNo(); //  글쓰기로 저장된 글의 글 번호 가지고 옴
		//d 드라이브의 저장될 파일 이름으로 사용할 것



		//letterFiles 저장
		int savedletterFileCnt = 0;//서버에 저장된 파일수
		if(letterFiles != null) {
			for(MultipartFile letterFile: letterFiles) {
				long letterFileSize = letterFile.getSize(); //파일크기 getSize()로 얻음
				if(letterFileSize > 0) {  // 업로드 되지 않았을 시 혹은 파일 이름이 없는 경우 -> 첨부되지 않은 경우 => letterFileSize == 0
					String letterOriginFileName = letterFile.getOriginalFilename(); //자소서 파일원본이름얻기
					//지원서 파일들 저장하기
					logger.info("지원서 파일이름: " + letterOriginFileName +" 파일크기: " + letterFile.getSize());
					//저장할 파일이름을 지정한다 ex) 글번호_letter_XXXX_원본이름
					String letterfileName = wroteBoardNo + "_letter_" + UUID.randomUUID() + "_" + letterOriginFileName; 
					File savevdLetterFile = new File(saveDirectory, letterfileName);//파일생성

					try {
						FileCopyUtils.copy(letterFile.getBytes(), savevdLetterFile);
						// 파일의 원본 이름은 letterFile에 있을 것이고 원본 이름을 가지고 새로운 파일 이름을 만들 것
						// savedLetterFile -> 서버에 저장될 파일 이름
						logger.info("지원서 파일저장:" + savevdLetterFile.getAbsolutePath());

					} catch (IOException e) {
						e.printStackTrace();
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					savedletterFileCnt++;
				}//end if(letterFileSize > 0)
			}
		}//end if(letterFiles != null)
		logger.info("저장된 letter 파일개수: " + savedletterFileCnt);
		//		return new ResponseEntity<>(HttpStatus.OK);

		File thumbnailFile = null;
		long imageFileSize = imageFile.getSize(); // 파일 크기
		int imageFileCnt = 0;//서버에 저장된 이미지파일수
		if(imageFileSize > 0) { // 정상 첨부
			//이미지파일 저장하기
			String imageOrignFileName = imageFile.getOriginalFilename(); //이미지파일원본이름얻기
			logger.info("이미지 파일이름:" + imageOrignFileName +", 파일크기: " + imageFile.getSize());

			//저장할 파일이름을 지정한다 ex) 글번호_image_XXXX_원본이름
			String imageFileName = wroteBoardNo + "_image_" + UUID.randomUUID() + "_" + imageOrignFileName;
			//이미지파일생성
			File savedImageFile = new File(saveDirectory, imageFileName);

			try {
				FileCopyUtils.copy(imageFile.getBytes(), savedImageFile);
				logger.info("이미지 파일저장:" + savedImageFile.getAbsolutePath());

				//파일형식 확인
				String contentType = imageFile.getContentType();
				if(!contentType.contains("image/")) { //이미지파일형식이 아닌 경우
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				//이미지파일인 경우 섬네일파일을 만듦
				String thumbnailName =  "s_"+imageFileName; //섬네일 파일명은 s_글번호_XXXX_원본이름
				thumbnailFile = new File(saveDirectory,thumbnailName);
				FileOutputStream thumbnailOS;
				thumbnailOS = new FileOutputStream(thumbnailFile);
				InputStream imageFileIS = imageFile.getInputStream();
				int width = 100;
				int height = 100;
				Thumbnailator.createThumbnail(imageFileIS, thumbnailOS, width, height);
				logger.info("섬네일파일 저장:" + thumbnailFile.getAbsolutePath() + ", 섬네일파일 크기:" + thumbnailFile.length());


				//이미지 썸네일다운로드하기
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set(HttpHeaders.CONTENT_LENGTH, thumbnailFile.length()+"");
				responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(thumbnailFile.toPath()));
				responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename="+URLEncoder.encode("a", "UTF-8"));
				logger.info("섬네일파일 다운로드");
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(thumbnailFile), 
						responseHeaders, 
						HttpStatus.OK);
				// 일반 응답가지고 안 됨. 다운로드용 응답헤더를ㄹ 설정 해 주어야함
				// content_length 응답 내용의 크기, content_type 응답 형식, content_disposition 다운로드 해야한다
				// 응답 내용 -> 썸네일 방식으로 
				// 썸네일 파일의 내용을 바이트 배열로 만들어서 응답 내용으로 쓰겠다.
				// Json형태로 응답받을 것이 아니라면 ResponseEntity형식으로 첫번째 인자를 string과 같은 것으로 받으면 됨


			} catch (IOException e2) {
				e2.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}//end if(imageFileSize > 0) 
		else {
			logger.error("이미지파일이 없습니다");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
