package com.xdpm.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.xdpm.entity.RentalRecord;

public class RentalDAO extends AbstractCRUD<RentalRecord>{

	//lấy record của đĩa chưa trả
	public RentalRecord getByDiskID(int diskID) {
		RentalRecord record = null;
		try {
			Query query = entityManager.createQuery("from RentalRecord where diskID = :id and returnDate is null", RentalRecord.class);
			query.setParameter("id", diskID);
			record = (RentalRecord) query.getSingleResult();
		} catch (Exception e) {
			record = null;
		}
		return record;
	}
	
	//lấy record của đĩa
		public RentalRecord getByDiskIDAndCusID(int diskID, int cusID, Date returnDate) {
			Query query = entityManager.createQuery("from RentalRecord where diskID = :id and customerID = :cusID and returnDate = :date", RentalRecord.class);
			query.setParameter("id", diskID);
			query.setParameter("cusID", cusID);
			query.setParameter("date", returnDate, TemporalType.DATE);
			return (RentalRecord) query.getSingleResult();
		}
	
	//Kiểm tra trả trễ
	public boolean checkLateReturn(RentalRecord record) {
		Date present = new Date();
		//Kiểm tra hiện tại vs ngày trả, i > 0 => quá hạn trả
		int i = present.compareTo(record.getDueDate());
		return i>0 ? true:false;
	}
	
	//Lấy ds đĩa trễ chưa thanh toán của KH
	public List<RentalRecord> getListUnpaidRecord(int cusID){
		Query query = entityManager.createQuery("from RentalRecord where customerID = :cusID and isPaid = 0 and lateFee > 0", RentalRecord.class);
		query.setParameter("cusID", cusID);
		return query.getResultList();
	}
	
	
}
