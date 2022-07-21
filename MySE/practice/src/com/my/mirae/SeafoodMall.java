package com.my.mirae;

class Seafood{
	String skuName; //상품명
	String skuCode; //상품코드
	int price; // 판매가
	String weight; // 중량
	boolean component ; // 구성품 여부
	String storageType; //보관방법 
	String origin; // 원산지
	boolean season; //제철 여부
	int stock ; //재고
	

	Seafood(){
	}
	
	Seafood(String skuName, String skuCode, int price, String weight, boolean component, 
			String storageType, String origin, boolean season, int stock){
		this.skuName = skuName;
		this.skuCode = skuCode;
		this.price = price;
		this.weight = weight;
		this.component = component;
		this.storageType = storageType;
		this.origin = origin;
		this.season = season;
		this.stock = stock;
	}
	void print() {
		System.out.println("sku명:"+skuName+" /skuCode: "+skuCode+" /판매가:"+price+ 
				" /중량:"+weight+" /구성품:"+component+ "/ 제철 여부:"+ season+ " /재고:"+stock);
	}
}

public class SeafoodMall {
	public static void main(String[] args) {
		Seafood hoi = new Seafood();
		hoi.skuName = "보리숭어회";
		hoi.skuCode = "H0001";
		hoi.price = 29900;
		hoi.weight = "400g";
		hoi.component = true;
		hoi.storageType = "냉장보관";
		hoi.origin = "국내산";
		hoi.season = true;
		hoi.stock = 5;
		hoi.print();


	}
}