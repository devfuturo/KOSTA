package com.my.repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface CustomerRepository {

//	Customer selectByIdAndPwd(String id, String pwd) throws FindException; // Customer라는 DTO로 설계
	
	/**
	 * 고객 정보를 추가한다
	 * @param customer 고객 정보
	 * @throws AddException 
	 */
	void insert(Customer customer) throws AddException;

	/**
	 * 아이디에 해당하는 고객을 검색한다
	 * @param id 아이디
	 * @return 고객(객체?!) 
	 * @throws FindException
	 */
	Customer selectById(String id) throws FindException; //인터페이스 메서드 선언

}
