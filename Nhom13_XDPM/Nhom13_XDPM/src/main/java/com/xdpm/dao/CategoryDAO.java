package com.xdpm.dao;

import java.util.List;

import javax.persistence.Query;

import com.xdpm.entity.Category;

public class CategoryDAO extends AbstractCRUD<Category>{
	
	public List<Category> getAllCategory() {
		Query query = entityManager.createQuery("from Category", Category.class);
		return query.getResultList();
	}
	
	public Category getCategoryByID(int id) {
		return entityManager.find(Category.class, id);
	}
	
	public Category getCategoryByName(String name) {
		Query query = entityManager.createQuery("from Category where name='" + name + "'", Category.class);
		return (Category) query.getSingleResult();
	}

	@Override
	public Category update(Category t) {
		Category category = entityManager.find(Category.class, t.getId());
		category.setLateFee(t.getLateFee());
		category.setRentalCharge(t.getRentalCharge());
		category.setRentalPeriod(t.getRentalPeriod());
		return super.update(category);
	}
}
