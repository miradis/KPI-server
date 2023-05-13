package com.program.service;

import java.util.List;

import com.program.model.Category;
import com.program.exception.CategoryException;

public interface CategoryService {

	List<Category>  getAllCategories() throws CategoryException;

	Category addNewCategory(Category category)throws CategoryException;

	Category getCategoryById(Integer CId)throws CategoryException;

	Category updateCategoryById(Integer id, Category category) throws CategoryException;

	void deleteCategoryById(Integer CId) throws CategoryException;

	 
}
