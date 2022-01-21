package com.xdpm.dao;

import java.util.List;

import javax.persistence.Query;

import com.xdpm.entity.Customer;
import com.xdpm.entity.RentalRecord;
import com.xdpm.entity.ReservationRecord;

public class CustomerDAO extends AbstractCRUD<Customer>{

	public List<Customer> getAllCustomer() {
		Query query = entityManager.createQuery("from Customer where enabled = true", Customer.class);
		return query.getResultList();
	}

	public Customer getCustomerByID(int id) {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer != null && customer.isEnabled()) {
			return customer;
		}
		return null;
	}

	public void deleteCustomer(int id) {
		Customer customer = entityManager.find(Customer.class, id);
		customer.setEnabled(false);
		update(customer);
	}

	@Override
	public Customer add(Customer t) {
		t.setEnabled(true);
		return super.add(t);
	}

	public List<RentalRecord> checkCustomerRented(int cusID) {
		Query query = entityManager.createQuery("from RentalRecord where customerID = :cusID and isPaid = 0",
				RentalRecord.class);
		query.setParameter("cusID", cusID);
		return query.getResultList();
	}

	public List<ReservationRecord> checkCustomerResertvation(int id) {
		Query query = entityManager.createQuery("from ReservationRecord where customerID=:id", ReservationRecord.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	
}
