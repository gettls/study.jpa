package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;


@SpringBootTest
@Transactional
class OrderServiceTest {

	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;
	
	@Test
	public void order() throws Exception{
		
		// given
		Member member = createMember();
		
		Book book = createItem("jpa", 10000, 10);
		
		int orderCount = 2;
		// when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		
		// then
		Order getOrder = orderRepository.findOne(orderId);
		
		
		
//		assertEquals("��ǰ �ֹ��� ���´� ORDER", OrderStatus.ORDER, getOrder.getStatus());
//		assertEquals("�ֹ��� ��ǰ ���� ���� ��Ȯ�ؾ� ��", 1, getOrder.getOrderItems().size());
//		assertEquals("�ֹ� ������ ���� * �����̴�", 10000 * orderCount, getOrder.getTotalPrice());
//		assertEquals("�ֹ��� ������ŭ ��� �پ�� �Ѵ�", 8, book.getStockQuantity());
	}


	@Test
	public void stockExceed() throws Exception {
		// given
		Member member = createMember();
		Item item = createItem("jpa", 10000, 10);
		
		int orderCount = 11;
		
		// when
		
		// then
		final NotEnoughStockException ex = assertThrows(NotEnoughStockException.class, 
				() -> orderService.order(member.getId(), item.getId(), orderCount));
		
		assertEquals(ex.getMessage(), "need more stock");
	}
	
	@Test
	public void cancel() throws Exception {
		// given
		
		Member member = createMember();
		Book item = createItem("jpa", 10000, 10);
		
		int ordercount=2;
		
		Long orderId = orderService.order(member.getId(), item.getId(), ordercount);
		// when
		orderService.cancelOrder(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
//		assertEquals("�ֹ� ��ҽ� ���´� CANCLE", OrderStatus.CANCEL, getOrder.getStatus());
//		assertEquals("�ֹ��� ��ҵ� ��ǰ�� �׸�ŭ ��� �����ؾ� �Ѵ�", 10, item.getStockQuantity());
	}
	
	private Book createItem(String name, int price, int cnt) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(cnt);
		em.persist(book);
		return book;
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("ȸ��1");
		member.setAddress(new Address("seoul", "river", "123-13"));
		em.persist(member);
		return member;
	}
	
}
