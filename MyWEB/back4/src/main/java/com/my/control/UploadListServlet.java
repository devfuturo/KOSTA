package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class UploadListServlet
 */
@WebServlet("/uploadlist")
public class UploadListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		String []fileNames = null;
		if(opt == null) {
			fileNames = new String[]{"x.jpg", "F0001.jpg", "C0001.jpg","C0002.jpg", "R0001.txt"}; 
			// 여러파일 한꺼번에 다운로드 받고 싶을 때 listServlet사용
			// 실제 있는 파일만 응답하도록 
		}
		ObjectMapper mapper = new ObjectMapper();
		String saveDirectoryName = "d:\\files";
   		List<Map<String,Object>> list = new ArrayList<>();
   		for(String fileName : fileNames) {
   			Path path = Paths.get(saveDirectoryName, fileName); // 파일이 있는지 찾아보는 작업
			String contentType = Files.probeContentType(path); // 파일형식

			if(path.toFile().exists()) { // 파일 존재할 경우
				Map<String,Object> map = new HashMap<>();
				map.put("name", fileName);
				map.put("contentType", contentType);
				list.add( map ); // json형태로 클라이언트한테 응답하려는 과정 map을 list형태로 추가
			}
   		}
   		response.setContentType("application/json;charset=utf-8");
   		PrintWriter out = response.getWriter();
   		out.print(mapper.writeValueAsString(list)); // 클라이언트에게 json형태로 응답
	}

}
