package jpabook.jpashop.controller;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

	@NotBlank(message =  "이름을 입력하세요.")
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
	
	
}
