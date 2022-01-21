package com.xdpm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.xdpm.entity.RentalRecord;
import com.xdpm.entity.ReservationRecord;

public class ReservationDAO extends AbstractCRUD<ReservationRecord>{
	
	//Kiểm tra kh đã đặt đĩa hay chưa
	public boolean checkReservationRecord(int cusID, int titleID) {
		Query query = entityManager.createQuery("select count(rc) from ReservationRecord rc where titleID = :titleID and customerID = :cusID");
		query.setParameter("titleID", titleID);
		query.setParameter("cusID", cusID);
		long rs = (long) query.getSingleResult();
		if (rs != 0) {
			return true;
		}
		return false;
	}
	
	public List<ReservationRecord> getListByTitleID(int titleID) {
		List<ReservationRecord> list = new ArrayList<ReservationRecord>();
		Query query = entityManager.createQuery("from ReservationRecord where titleID = :titleID and diskID is null order by id", ReservationRecord.class);
		query.setParameter("titleID", titleID);
		list = query.getResultList();
		return list;
	}
	
	public ReservationRecord getRecordByCusIDAndDiskID(int cusID, int diskID) {
		ReservationRecord record = null;
		try {
			Query query = entityManager.createQuery("from ReservationRecord where customerID = :cusID and diskID = :diskID", ReservationRecord.class);
			query.setParameter("cusID", cusID);
			query.setParameter("diskID", diskID);
			record = (ReservationRecord) query.getSingleResult();
		} catch (Exception e) {
			record = null;
		}
		
		return record;
	}
	
	public ReservationRecord getByDiskID(int diskID) {
		Query query = entityManager.createQuery("from ReservationRecord where diskID = :diskID", ReservationRecord.class);
		query.setParameter("diskID", diskID);
		return (ReservationRecord) query.getSingleResult();
	}
	
	public List<ReservationRecord> getListByCusID(int cusID) {
		List<ReservationRecord> list = new ArrayList<ReservationRecord>();
		Query query = entityManager.createQuery("from ReservationRecord where customerID = :cusID order by id", ReservationRecord.class);
		query.setParameter("cusID", cusID);
		list = query.getResultList();
		return list;
	}
	
	public List<ReservationRecord> getAllRecord() {
		List<ReservationRecord> list = new ArrayList<ReservationRecord>();
		Query query = entityManager.createQuery("from ReservationRecord", ReservationRecord.class);
		list = query.getResultList();
		return list;
	}
	
	public ReservationRecord getRecordByCusIDAndTitleID(int cusID, int titleID) {
		ReservationRecord record = null;
		try {
			Query query = entityManager.createQuery("from ReservationRecord where customerID = :cusID and titleID = :titleID", ReservationRecord.class);
			query.setParameter("cusID", cusID);
			query.setParameter("titleID", titleID);
			record = (ReservationRecord) query.getSingleResult();
		} catch (Exception e) {
			record = null;
		}
		return record;
	}
}
