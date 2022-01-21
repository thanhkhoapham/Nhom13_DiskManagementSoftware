package com.xdpm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8184485578994362265L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "nvarchar(30)")
	private String name;
	private int rentalPeriod;
	private double rentalCharge;
	private double lateFee;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Title> titles;
	
	public Category() {
	}

	public Category(String name, int rentalPeriod, double rentalCharge, double lateFee) {
		super();
		this.name = name;
		this.rentalPeriod = rentalPeriod;
		this.rentalCharge = rentalCharge;
		this.lateFee = lateFee;
	}
	
	public Category(int id, String name, double rentalCharge, double lateFee) {
		this.id = id;
		this.name = name;
		this.rentalCharge = rentalCharge;
		this.lateFee = lateFee;
	}

	public Category(int id, String name, int rentalPeriod) {
		this.id = id;
		this.name = name;
		this.rentalPeriod = rentalPeriod;
	}

	public Category(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRentalPeriod() {
		return rentalPeriod;
	}

	public void setRentalPeriod(int rentalPeriod) {
		this.rentalPeriod = rentalPeriod;
	}

	public double getRentalCharge() {
		return rentalCharge;
	}

	public void setRentalCharge(double rentalCharge) {
		this.rentalCharge = rentalCharge;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	
	
}
