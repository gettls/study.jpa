package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id") 
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPirce; // 주문 가격
	private int count; // 주문 수량
	
	//==생성 메서드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setCount(count);
		orderItem.setOrderPirce(orderPrice);
		
		item.removeStock(count);
		return orderItem;
	}
	
	//==비즈니스 로직==//
	public void cancel() {
		getItem().addStock(count);
	}
	//==조회 로직==//
	// 주문 상품 전체 가격 조회 //
	public int getTotalPrcie() {
		return getOrderPirce() * getCount();
	}
}
