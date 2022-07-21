package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

public class ProductController implements Controller {
	private ProductRepository repository ;
	public ProductController() {
		repository = new ProductOracleRepository();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String servletPath = request.getServletPath();
		if("/productlist".equals(servletPath)) {
			return list(request, response);
		}else if ("/viewproduct".equals(servletPath)) {
			return view(request, response);
		}
		return null;
	}

	//productlist
	private String list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//		ProductRepository repository = new ProductOracleRepository();
		List<Product> products;

		ObjectMapper mapper = new ObjectMapper();
		//		Map<String, Object> map = new HashMap<>();
		String result = null;

		try {
			products = repository.selectAll();
			//	for(int i =0 ; i<products.size();i++) {
			//	Product p = products.get(i);
			//	map.put("prod_no", p.getProdNo());
			//	map.put("prod_name", p.getProdName());
			//	map.put("prod_price", p.getProdPrice());
			//	}
		} catch (FindException e) {
			e.printStackTrace();
			products = new ArrayList<>();
		}
		result = mapper.writeValueAsString(products); 
		//product 전체 가져와야하기 때문에 매개 인자로 map이 아닌 products를 담아야함
		return result;
	}


	//viewproduct
	private String view(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//	요청 전달데이터 얻기
		String prod_no = request.getParameter("prod_no");
		String result= "";
		//	결과값 확인
		//	ObjectMapper mapper = new ObjectMapper();
		//	Map<String, Object> map = new HashMap<>();

		//	DB와 연결하여 pro_no값 있는지 확인
		//	ProductRepository repository = new ProductOracleRepository();
		
		try {
			Product p = repository.selectByProdNo(prod_no);
			Map<String, Object> map = new HashMap<>();
			map.put("status", 1);
			map.put("p", p);
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);

		} catch (FindException e) { 
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", e.getMessage());			
		}
		return result;
	}



}
