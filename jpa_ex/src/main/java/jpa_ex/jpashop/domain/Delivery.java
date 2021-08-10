package jpa_ex.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
public class Delivery extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	private String street;
	private String zipcode;
	private String city;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
}
