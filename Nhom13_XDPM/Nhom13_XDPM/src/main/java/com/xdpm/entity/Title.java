package com.xdpm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Title implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4095958880370968223L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "nvarchar(50)")
	private String name;
	private int numberOfCopies;
	
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Category category;
	
	@OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
	private List<Disk> disks;
	
	@OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
	private List<ReservationRecord> reservationRecords;
	
	public Title() {
	}
	
	public Title(int id) {
		this.id = id;
	}

	public Title(int id, int numberOfCopies) {

		this.id = id;
		this.numberOfCopies = numberOfCopies;
	}

	public Title(String name, int numberOfCopies, Category category) {
		this.name = name;
		this.numberOfCopies = numberOfCopies;
		this.category = category;
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

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Disk> getDisks() {
		return disks;
	}

	public void setDisks(List<Disk> disks) {
		this.disks = disks;
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

	
}
