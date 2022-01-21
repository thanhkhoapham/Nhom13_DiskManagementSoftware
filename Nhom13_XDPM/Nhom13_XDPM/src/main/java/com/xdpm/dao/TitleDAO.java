package com.xdpm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.xdpm.entity.Disk;
import com.xdpm.entity.Title;

public class TitleDAO extends AbstractCRUD<Title>{
	private DiskDAO diskDao = new DiskDAO();
	
	public List<Title> getAllTitleByCategoryID(int categoryID) {
		Query query = entityManager.createQuery("from Title where categoryID = :categoryID and enabled = true", Title.class);
		query.setParameter("categoryID", categoryID);
		return query.getResultList();
	}
	
	public Title getTitleByID(int id) {
		Title title = entityManager.find(Title.class, id);
		if (title != null && title.isEnabled()) {
			return title;
		}
		return null;
	}
	
	public List<Title> findTitleByName(String name) {
		List<Title> list = new ArrayList<Title>();
		Query query = entityManager.createQuery("from Title where lower(name) like :titleName and enabled = true", Title.class);
		query.setParameter("titleName", "%" + name.toLowerCase() + "%");
		list = query.getResultList();
		return list;
	}
	
	//=============================================
	@SuppressWarnings("unchecked")
	public List<Title> getAllTitles() {
		Query query = entityManager.createQuery("from Title where enabled = true", Title.class);
		return query.getResultList();
	}

	public void deleteTitle(int id) {
		Title title = entityManager.find(Title.class, id);
		title.setEnabled(false);
		update(title);
		List<Disk> ds = diskDao.getAllDiskByTitleID(id);
		ds.forEach(e -> {
			System.out.println(e);
			e.setEnabled(false);
			diskDao.update(e);
		});

	}

	@Override
	public Title add(Title t) {
		t.setEnabled(true);
		t.setNumberOfCopies(0);
		return super.add(t);
	}

	public List<Disk> countOfmaxDisk(int id) {
		Query query = entityManager.createQuery("from Disk where titleID=" + id + " and enabled = 1", Disk.class);
		return query.getResultList();
	}

	public List<Disk> countOfonShelf(int id) {
		Query query = entityManager
				.createQuery("from Disk where titleID=" + id + " and enabled = true and status='onShelf'", Disk.class);
		return query.getResultList();

	}

	public Title update(Title t) {
		Title title = entityManager.find(Title.class, t.getId());
		title.setNumberOfCopies(t.getNumberOfCopies());
		return super.update(title);
	}
}
