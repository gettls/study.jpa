package jpa_ex.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Member extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
//	private String city;
//	private String street;
//	private String zipcode;
	
	@Embedded // Embeddable 있으면 생략 가능
	private Address address;

//	@OneToMany(mappedBy = "member")
//	private List<Order> orders = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
