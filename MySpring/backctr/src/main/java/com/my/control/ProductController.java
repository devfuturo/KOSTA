package com.my.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	}
	
