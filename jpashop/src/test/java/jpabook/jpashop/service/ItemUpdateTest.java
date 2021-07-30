package jpabook.jpashop.service;

import java.awt.print.Book;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

	@Autowired
	EntityManager em;
	
	@Test
	public void updateTest() {
		em.find(Book.class, 1L);
	}
}
