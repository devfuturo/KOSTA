package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.CustomerOracleRepository;
import com.my.repository.CustomerRepository;

@Service(value = "customerService") //value로 네임속성 지정하지 않을 시 Class 이름이 name이 됨
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	//리파지토리도 스프링이 직접관리하게 하기 
//	public CustomerService() {
//		this.repository = new CustomerOracleRepository();
//	}
//	public CustomerService() {}
//	public CustomerService(CustomerRepository repository) {
//		this.repository = repository;
//	}
//	public CustomerRepository getRepository() {
//		return repository;
//	}
//	public void setRepository(CustomerRepository repository) {
//		this.repository = repository;
//	}
	public Customer login(String id, String pwd) throws FindException{
		Customer c = repository.selectById(id);
		if(!c.getPwd().equals(pwd)) {
			throw new FindException();
		}
		return c;
	}
	public void signup(Customer c) throws AddException {
		repository.insert(c);
	}
	
	public Customer iddupchk(String id) throws FindException {
		return repository.selectById(id);
	}

	
	//iddupchk
	//signup
	//logininstatus
	//logout
	//매개변수 , 리턴타입 , 예외처리(서비스에서 예외처리를 하면 컨트롤러에서 예외처리 했는지 알 수 없음)  
}
