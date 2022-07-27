package com.my.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * 주문 기본 정보
 * @author gram
 *
 */
public class OrderInfo {  //order_info 테이블의 class와 멤버변수 3개 만드는 것
	private int orderNo; //주문번호 
	private String orderId; //주문자 아이디
	@JsonFormat(pattern="yy/MM/dd" , timezone="Asia/Seoul") // 잭슨라이브러리에서 제공하는 어노테이션
	// ▲ Json 문자열 형태로 응답할 때 날짜값의 포맷을 패턴과 같은 형태로 표현 해 줌 
	private Date orderDt; // 주문일자
	private List<OrderLine> lines; // 주문 상세정보들 
	//1대 다로 설계한 것 (orderline이 여러개 들어있는 형태)
	
	public OrderInfo() {
		super();
	}
	
	public OrderInfo(String orderId, List<OrderLine> lines) {
		super();
		this.orderId = orderId;
		this.lines = lines;
	}

	public OrderInfo(int orderNo, String orderId, Date orderDt, List<OrderLine> lines) {
		super();
		this.orderNo = orderNo;
		this.orderId = orderId;
		this.orderDt = orderDt;
		this.lines = lines;
	}	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDt() {
		return orderDt;
	}
	public void setOrderDt(Date orderDt) {
		this.orderDt = orderDt;
	}
	public List<OrderLine> getLines() {
		return lines;
	}
	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderNo=" + orderNo + ", orderId=" + orderId + ", orderDt=" + orderDt + ", lines=" + lines
				+ "]";
	}
	
}
