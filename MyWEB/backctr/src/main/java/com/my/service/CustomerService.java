package com.my.service;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.CustomerOracleRepository;
import com.my.repository.CustomerRepository;

public class CustomerService {
	private CustomerRepository repository;
	public CustomerService() {
		this.repository = new CustomerOracleRepository();
	}
	public Customer login(String id, String pwd) throws FindException{
			
			Customer c = repository.selectById(id);
			if(!c.getPwd().equals(pwd)) { //비밀번호가 같지 않다(로그인 실패), 예외가 발생 할 경우
				throw new FindException();
			}
			return c;
	}
	
	public void signup(Customer c) throws AddException{
		repository.insert(c);			
	}
	
	public Customer iddupchk(String id) throws FindException{
		return repository.selectById(id);
	}
	
	
}
