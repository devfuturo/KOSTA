package com.my.repository; //저장소라는 의미. repository 패키지 생성, 자료 저장, 수정, 제거

import com.my.dto.Product; //ctrl + shift + o -> import자동 생성
import com.my.exception.AddException;

/**
 * 배열저장소
 * @author gram
 *
 */
public class ProductArrayRepository{
	private Product[] products; // 저장소
	private int cnt ; // 저장소에 저장된 상품 수
	public ProductArrayRepository(){
		this.products= new Product[5];
	}
	public ProductArrayRepository(int i){
		this.products= new Product[i];
	}
	
	/**
	 * 저장소에 상품을 추가한다
	 * @param product 상품
	 */
	public void insert(Product product) throws AddException{ // throws ArrayIndexOutOfBoundsException{
		try {
			this.products[this.cnt]=product; // good code
			this.cnt++;
//			products[cnt++] = product; // bad code 
//			System.out.println(this.cnt + "개 저장되었습니다.");
			System.out.println("상품종료개수:"+ this.cnt);
		}catch(ArrayIndexOutOfBoundsException e) { //예외처리 방법 : try, catch 
//			System.out.println("저장소가 꽉찼습니다. 현재 상품 종류의 개수 :" + cnt);
			throw new AddException("저장소가 꽉찼습니다. 현재 상품 종류의 개수 :" + cnt);
		}
	}
		//AddException 추가하다가 발생하는 예외 / RemoveException 제거하다가 / ModifyException 수정하다가
		
	/**
	 * 저장된 상품들을 반환한다
	 * @return
	 */
	public Product[] selectAll(){
		//return products;
		Product[]result = new Product[cnt];
		for(int i=0; i<cnt; i++) {
			result[i] = products[i];
		}
		return result;
	}
}
