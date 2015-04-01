package com.ximalaya.sdk4j;

import java.util.List;

import org.junit.Test;

import com.ximalaya.sdk4j.model.XimalayaException;
import com.ximalaya.sdk4j.model.dto.category.Category;

public class CategoriesTest {

	Categories categoriesService = new Categories();
	
	@Test
	public void testGetCategories() throws XimalayaException {
		List<Category> categories = categoriesService.getCategories();
		System.out.println(categories);
	}
	
}