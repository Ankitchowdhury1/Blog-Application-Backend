package com.ankit.blog.services;

import java.util.List;



import com.ankit.blog.payloads.CategoryDto;


public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getall
	List<CategoryDto> getCategories();
	
}
