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
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1079118684844849640L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String name;
	@Column(columnDefinition = "nvarchar(50)")
	private String address;
	@Column(columnDefinition = "nvarchar(20)")
	private String phoneNumber;
	
	private boolean enabled;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<RentalRecord> rentalRecords;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<ReservationRecord> reservationRecords;
	
	public Customer() {
	}

	public Customer(String name, String address, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public Customer(String name, String address, String phoneNumber, boolean enabled) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<RentalRecord> getRentalRecords() {
		return rentalRecords;
	}

	public void setRentalRecords(List<RentalRecord> rentalRecords) {
		this.rentalRecords = rentalRecords;
	}

	public List<ReservationRecord> getReservationRecords() {
		return reservationRecords;
	}

	public void setReservationRecords(List<ReservationRecord> reservationRecords) {
		this.reservationRecords = reservationRecords;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Customer(int id, String name, String address, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", enabled=" + enabled + "]";
	}


	
}
