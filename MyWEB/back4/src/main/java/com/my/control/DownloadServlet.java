package com.my.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일이름 요청전달데이터 얻기
		String fileName = request.getParameter("filename");
		//다운로드할 파일의 실제 경로 얻기
		String saveDirectoryName = "d:\\files";
		
		Path path = Paths.get(saveDirectoryName, fileName);
		String contentType = Files.probeContentType(path);
		System.out.println(fileName+"파일의 contentType:" + contentType);
		

		File f = path.toFile();
		if(contentType.contains("image/")) { // 파일 형식에 image/가 들어가면 image형식으로 인지
			//이미지 파일일 때
			response.setContentType(contentType); // 미리보기 형태 (바로 다운X)
//			response.setContentType("application/octet-stream;charset=UTF-8"); // 바로 다운로드
			response.setHeader("Content-Length", String.valueOf(f.length())); 
			response.setHeader("Content-Disposition", "inline; filename="+URLEncoder.encode(fileName, "UTF-8"));
		}else {
			// 일반 파일일 때 
			//응답형식 : application/octet-stream(8비트 단위의 binary data) - 기본 응답형식
			response.setContentType("application/octet-stream;charset=UTF-8"); // 일반파일인 경우 무조건 다운로드함
			// ▲ 무조건 다운로드한다는 코드

			//다운로드 할 파일이름 결정
			//response.setHeader("Content-Disposition", "attachment;filename=" + name);
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			// url 인코딩 해 주어야 파일 이름 한글 깨지지 않음
		}

		//응답출력스트림 
		//PrintWriter out = response.getWriter(); (X) -- 문자형태로 응답출력
		ServletOutputStream sos = response.getOutputStream(); //-- 바이트형태로 파일을 출력
		// 바이트 단위로 응답할 수 있도록 하는 getOutputStream을 가지고 있는 ServletOutputStream 형식 가지고 옴
		// 이미지, 동영상, 사운드파일, 텍스트 파일 여러가지 다운로드 될 수 있으므로 
		// 문자단위보다 바이트단위로 응답하는 것이 적절 ( 이미지 > 바이트 단위로 구성되어있음 )

		FileInputStream fis = null;
		fis = new FileInputStream(f);
		IOUtils.copy(fis, sos); //파일복사붙여넣기
		// IOUtils -> 톰캣에서 제공하는 라이브러리
		// int v = -1
		// while((v = fis.readC) != -1){ sos.write(v); }
		// while 파일의 끝까지 읽어서 더이상 읽을게 없을 때 /-1이 아닐 때 : 파일을 끝까지 읽어온다 
		fis.close();
		sos.close();
	}
}
