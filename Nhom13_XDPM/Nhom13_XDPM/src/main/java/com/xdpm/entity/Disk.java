package com.xdpm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6858669371337497824L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String status;
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "titleID")
	private Title title;
	
	@OneToMany(mappedBy = "disk", cascade = CascadeType.ALL)
	private List<RentalRecord> rentalRecords;
	
	@OneToMany(mappedBy = "disk", cascade = CascadeType.ALL)
	private List<ReservationRecord> reservationRecords;
	
	public Disk() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public List<RentalRecord> getRentalRecords() {
		return rentalRecords;
	}

	public void setRentalRecords(List<RentalRecord> rentalRecords) {
		this.rentalRecords = rentalRecords;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Disk(Title title) {
		this.title = title;
	}

	
}
