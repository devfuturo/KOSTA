package com.my.control;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload") //servlet mapping과 같은 효과
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 디렉토리에 파일 생성하는 과정 (2)번 과정
		request.setCharacterEncoding("UTF-8");
		String saveDirectoryName = "d:\\files"; // "c:\\Golflearn_lib\\images"
		File saveDirectory = new File(saveDirectoryName);

		try {   			
			if(!saveDirectory.exists()) {
				System.out.println("업로드 실제경로("+ saveDirectoryName +")생성");
				saveDirectory.mkdirs(); // 디렉토리에 파일이 없다면 파일을 만들어 주는 것
			}
			System.out.println("saveDirectory.getAbsolutePath()=" + saveDirectory.getAbsolutePath());
			// getAbsolutePath() : 파일의 절대 경로를 넘겨준다
			
			
			Collection<Part> parts = request.getParts(); // 요청시에 전달 된 formData를 얻어내는 API 
			// request.getParts()를 활성화 하기 위해
			
			for(Part part: parts) { 
				String paramName = part.getName();// 파트의 get Name 메서드가 요청시 전달 된 key가 되는 것
				// 실제 값음 fileName이라던지 part의 size이라던지 확인 해 볼 수 있음
				// paramName은 upload.html의 모든 (input의) name값을 가지고 옴
				System.out.println("part.getName()=" + paramName +", part.getSubmittedFileName()="+ part.getSubmittedFileName()+", part.getSize()=" + part.getSize());
				if("foodFile".equals(paramName) || "drinkFiles".equals(paramName)||"recipe".equals(paramName)) {
					
					// 요청 전달 데이터 part로 꺼내와야한다 > 이게 key 
					// 값이 file 형태라면 fileName과 fileSize 정도가 값이 될 수 있음
					String originFileName = part.getSubmittedFileName();
					String fileName =  originFileName;
					
					// 파일 이름이런식으로 바꿀 수 있음
					// 난수 발생하여 파일명이 중복되지 않도록 할 수 있음 ▼
					//Universal Unique Identifier
					//String fileName = UUID.randomUUID() + "_" + originFileName;
					
					//String fileName = "temp_"+originFileName; 
					
					part.write(saveDirectory.getAbsolutePath()+"\\"+fileName); // 특정 디렉토리에 파일이름으로 쓰기 함(복붙) 
				}

//			}

			System.out.println("-----request.getParameter(\"greeting\")----");
			System.out.println(request.getParameter("greeting"));
			System.out.println("-----request.getParameter(\"recipes\")----");
			System.out.println(request.getParameter("recipes"));		      

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
