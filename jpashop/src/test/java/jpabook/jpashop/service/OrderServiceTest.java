package jpabook.jpashop.service;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		
		assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 함", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다", 10000 * orderCount, getOrder.getTotalPrice());
		assertEquals("주문한 수량만큼 재고가 줄어야 한다", 8, book.getStockQuantity());
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
		
		assertEquals("주문 취소시 상태는 CANCLE", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다", 10, item.getStockQuantity());
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
		member.setName("회원1");
		member.setAddres(new Address("seoul", "river", "123-13"));
		em.persist(member);
		return member;
	}
	
}
