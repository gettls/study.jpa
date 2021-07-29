package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	
	@Test
	public void join() throws Exception {
		// Given
		Member member = new Member();
		member.setName("kim");
		// When
		Long saveId = memberService.join(member);
		// Then
		assertEquals(member, memberRepository.findOne(saveId));
	}

	@Test
	public void validate() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim1");
		Member member2 = new Member();
		member2.setName("kim2");
		// when
		memberService.join(member1);
		memberService.join(member2);	
		
		// then
		IllegalStateException thrown 
		= assertThrows(IllegalStateException.class, 
				() -> memberService.join(member2));
		assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
	}
}
