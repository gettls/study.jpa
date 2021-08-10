package jpa_ex.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CategoryItem extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "CATEGORY_ITEM_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;
}
