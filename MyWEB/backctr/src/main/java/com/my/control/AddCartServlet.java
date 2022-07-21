package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dto.Product;

public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prod_no");
		String quantity = request.getParameter("quantity");
		
		HttpSession session = request.getSession();
		
		// 1.
		// 밑의 코드는 누적되는 개념이 아닌 추가 되는 코드
		// 매번 카트 새로 만들어서 그 카트에 맵 매번 추가하고 맵에 상품과 수량이 매번 새로 만들어짐
		// 따라서 장바구니에 적절한 코드가 X

		//		List<Map<Product,Integer>> cart = new ArrayList<>();
		//
		//		Map<Product,Integer> map = new HashMap<>();
		//		Product p = new Product(); p.setProdNo(prodNo); //Map의 키가 만들어짐
		//		Integer.parseInt(quantity);
		//		map.put(p, Integer.parseInt(quantity));
		//		cart.add(map);
		//
//				session.setAttribute("cart", cart); // List는 session의 attribute에 추가 

		
		// session에서 카트를 꺼내서 카트에 있는 같은 상품이라면 수량만 누적
//		List<Map<Product,Integer>> cart = (List)session.getAttribute("cart"); // 카트라는 이름의 attribute가 있는지 확인 
		
		// 3.
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		if(cart ==null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		Product p = new Product(); p.setProdNo(prodNo); 
		int newQuantity = Integer.parseInt(quantity);
			Integer oldQuantity = cart.get(p); // 맵에서 상품찾는다. 전달된 p 객체의 hashcode 매서드와 equals메서드가 true인 것을 찾아내는 것
			if(oldQuantity != null) { // 상품이 있는 경우 
				newQuantity += oldQuantity; //기존수량에 새 수량 추가해 줌
			
		 }
			 cart.put(p, newQuantity);
		
		System.out.println("장바구니 목록 수" + cart.size());
		System.out.println(cart);

		
		//2.
//		List<Map<Product,Integer>> cart = (List)session.getAttribute("cart"); // ?
//		boolean exist = false; // 상품존재여부
//		//장바구니에 기존 상품 있는지 확인 (있으면 기존에 수량 추가하는 형태로 가야하기 때문)
//		outer : for(Map<Product, Integer> map : cart) { //outer, inner : 라벨 주는 것
//			Set<Product> products = map.keySet(); // 장바구니에 저장되어있는 key로 쓰이는 상품들 
//			inner : for(Product p : products) { // 상품
//				if(p.getProdNo().equals(prodNo)) { // 지금 추가하려는 상품 번호가 이미 장바구니에 존재하면 
//					// 수량만 증가(누적), 반복 종료
//					int oldQuantity = map.get(p); // 기존 수량(oldQuantity)
//					map.put(p, oldQuantity + Integer.parseInt(quantity));
//					exist = true; // 상품 존재
//					break outer; // outer 반복문 종료하는 것
//				} 
//			}
//		}
//
//		if(!exist) {
//			// 반복문 끝까지 돌았는데 상품번호 존재하지 않을 시 
//			// 장바구니에 추가
//			Product p = new Product();
//			Map<Product,Integer> map = new HashMap<>();
//			p.setProdNo(prodNo);
//			map.put(p, Integer.parseInt(quantity));
//			cart.add(map);
//
//		}
		// 장바구니에 잘 추가 되었는지 확인
//		System.out.println("장바구니 목록 수" + cart.size());
//		System.out.println(cart);
	}

}
