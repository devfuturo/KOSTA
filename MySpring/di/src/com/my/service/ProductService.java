package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.repository.ProductRepository;

@Service(value = "productService")
public class ProductService {
	
	@Autowired
	private ProductRepository repository ;// 이미 만들어져있는 repository를 주입받아 사용

	// 어노테이션 사용 시 생성자, 셋터 게터 메서드 필요 x
//	public ProductService() {}
//	public ProductService(ProductRepository repository) {
//		this.repository = repository;
//	}
//
//	public void setRepository(ProductRepository repository) {
//		this.repository = repository;
//	}
//
//	public ProductRepository getRepository () {
//		return repository;
//	}
	
	public void list() {
//		repository.selectAll();
	}
	
	public void view() {
//		repository.selectByProdNo(prodNo);
	}
}
