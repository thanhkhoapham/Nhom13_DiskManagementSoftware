package com.xdpm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.xdpm.entity.Disk;

public class DiskDAO extends AbstractCRUD<Disk>{
	
	public List<Disk> getAllDiskByTitleID(int titleID) {
		Query query = entityManager.createQuery("from Disk where titleID = :titleID and enabled = true", Disk.class);
		query.setParameter("titleID", titleID);
		return query.getResultList();
	}
	
	public Disk getDiskByID(int id) {
		Disk disk = entityManager.find(Disk.class, id);
		if (disk != null && disk.isEnabled()) {
			return disk;
		}
		return null;
	}
	
	public void deleteDisk(int id) {
		Disk disk = entityManager.find(Disk.class, id);
		disk.setEnabled(false);
		update(disk);
	}
	
	//Lấy ds đĩa có thể thuê của 1 tựa
	public List<Disk> getListOfRentableDisk(int titleID) {
		List<Disk> list = new ArrayList<Disk>();
		Query query = entityManager.createQuery("from Disk where titleID = :titleID and status = 'onShelf' and enabled = true", Disk.class);
		query.setParameter("titleID", titleID);
		list = query.getResultList();
		return list;
	}
	
	public Disk CheckOnShelfDiskByID(int id) {
		Disk disk = entityManager.find(Disk.class, id);
		if (disk != null && disk.isEnabled() && disk.getStatus().equalsIgnoreCase("onShelf")) {
			return disk;
		}
		return null;
	}

	@Override
	public Disk add(Disk t) {
		t.setEnabled(true);
		t.setStatus("onShelf");
		return super.add(t);
	}

	public List<Disk> getAllDiskSorfByTitleID(int titleID) {
		Query query = entityManager
				.createQuery("from Disk where titleID = :titleID and enabled = true order by status asc", Disk.class);
		query.setParameter("titleID", titleID);
		return query.getResultList();
	}

	public List<Disk> getAllDiskRentedByTitleID(int titleID) {
		Query query = entityManager
				.createQuery("from Disk where titleID = :titleID and enabled = true and status='onShelf'", Disk.class);
		query.setParameter("titleID", titleID);
		return query.getResultList();
	}
}
