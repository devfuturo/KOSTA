package com.my.repository;

import java.util.List;

import com.my.dto.OrderInfo;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface OrderRepository {
	/**
	 * 주문 정보를 추가한다
	 * @param info 주문정보
	 * @throws AddException 한미래 정신차려 이각박한 세상속에서
	 */
	public void insert(OrderInfo info) throws AddException;

	/**
	 * 주문자 아이디가 주문한 정보들을 반환한다
	 * @param orderId 주문자 아이디
	 * @return 주문 정보들 (정보들이기 때문에 List로 반환)
	 * @throws FindException
	 */
	public List<OrderInfo> selectById(String orderId) throws FindException; // 주문 조회하는 메서드	
}

	// OrderInfo에는 order_info와 order_line 두 테이블에 대한 정보가 모두 들어있어야함