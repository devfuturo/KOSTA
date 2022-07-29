package com.my.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.dto.ResultBean;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.service.BoardService;

import net.coobird.thumbnailator.Thumbnailator;

// 스프링 프레임워크에서 제공하는 RESTful용 API  
@RestController // @Controller 와 @ResponseBody 사용하는 경우라면 @RestController로 대체
@RequestMapping("/board/*") // 각 메서드 앞에 붙여도 OK
public class BoardRestController {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private BoardService service;

	@Autowired
	private ServletContext sc;

	//GET /backboard/board/list
	//GET /backboard/board/list/페이지번호
	@GetMapping(value = {"list", "list/{optCp}"}) //optional currentPage
	//RESTful 형태로 주소 구성할 때 요청전달데이터가 uri의 path로 사용됨 -> {}로 표현
	// ▲ /list 와  list/1 둘 다 작동하도록 하겠다
	public ResultBean<PageBean<Board>> list(@PathVariable //int currentPage) { // 매개변수 명과 {}안의 이름값 같아야함
			Optional<Integer> optCp){ //java에서 주는 라이브러리
		// 요청전달데이터 전달 될 때 requestparam통해 required = false로 설정할 수 있음
		// 요청전달데이터 전달되지 않을 때를 대비하여 사용하는 RESTful의 @pathVariacle은 Optional로 설정 해 둬야함
		// Optional은 java.util에서 제공. null값 체크를 쉽게 할 수 있음
		//currentPage 값을 전달하지 않으면 PathVariable값이 optional로 전달 되어 PathVariable 전달된 값 null인지 아닌지 확인 가능?!

		//		optCp.ifPresent(null); // currentPage값이 존재하면 ~이라는 메서드
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			int currentPage;
			if(optCp.isPresent()) { // 값이 있는지 없는지 확인 해 주는 메서드 (존재 시 true 반환, 미존재 시 false 반환)
				currentPage = optCp.get();
			} else { // 없으면
				currentPage = 1; // currentPage 값 1로 준다
			}
			// ▲ /list/2와 같이 currentPage번호 주어지면 그 값을 넣고,  /list 와 같이 전달되지 않으면 1로 준다는 의미
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

	//  GET /backboard/search/검색어/페이지번호   /search/답/3
	//  GET /backboard/search/검색어			  /search/답
	//  GET /backboard/search/페이지번호 		  /search//3 (X) -> 중간경로 생략 불가  
	//  GET /backboard/search/페이지번호 		  /search/3  (X) -> 3을 검색어로 처리함
	//  GET /backboard/search					  /search			
	@GetMapping(value = {"search/{optWord}/{optCp}", "search/{optWord}", "search"})
	public ResultBean<PageBean<Board>> search(
			@PathVariable Optional<Integer> optCp,
			@PathVariable Optional<String> optWord){
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			PageBean<Board> pb ;
			String word = "";
			if(optWord.isPresent()) {
				word = optWord.get();
			}
			else {
				word= "";
			}

			int currentPage =1;
			if(optCp.isPresent()) {
				currentPage = optCp.get();
			}
			//			else {
			//				currentPage = 1;
			//			}

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

	@GetMapping("view/{boardNo}")
	public ResultBean<Board> viewBoard(@PathVariable int boardNo){
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


	// Post /backboard/board/write/글제목/글내용 (X) 
	// 파일 업로드 할 때는 formData 필요한데 formData 사용할 때는 PathVariable 사용 X
	// RESTful 사용 시 파일 업로드 할 때 PathVariable 사용하지 않음
	// 파일 업로드 할 때는 요청전달데이터 꼭 필요하기 때문에 RESTful 사용 불가
	@PostMapping("write")
	public ResponseEntity<?> write(  
			@RequestPart(required = false) List<MultipartFile> letterFiles 
			,@RequestPart(required = false) MultipartFile imageFile
			,Board board
			,String greeting 
			,HttpSession session){

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


	//  PUT /backboard/board/글번호/글내용
	@PutMapping("modify/{boardNo}")
	public void modifyBoard(@PathVariable Board board) {
		try {
			service.modifyBoard(board);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
		
	}
	
	// DELETE /backboard/board/글번호 
	public void removeBoard(@PathVariable int boardNo) {
		ResultBean<Board> rb = new ResultBean<>();
	
	}
}
