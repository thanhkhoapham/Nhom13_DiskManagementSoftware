package com.xdpm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RentalRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3534284061707471065L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "diskID")
	private Disk disk;
	
	
	@ManyToOne
	@JoinColumn(name = "customerID")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	private Date rentDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@Temporal(TemporalType.DATE)
	private Date returnDate;
	private double rentalCharge;
	private double lateFee;
	private boolean isPaid;
	
	public RentalRecord() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Disk getDisk() {
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public double getRentalCharge() {
		return rentalCharge;
	}

	public void setRentalCharge(double rentalCharge) {
		this.rentalCharge = rentalCharge;
	}

	
}
