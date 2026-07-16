package com.bway.springproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class Product {
	@Id
	private int id;
	private String title;
	private double price;
	@Column(columnDefinition = "longtext")
	private String description;
	private String image;
	private String category;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="rating_id", referencedColumnName = "id")
	private Rating rating;
}
