package com.myapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myapp.dao.ProductDAO;
import com.myapp.utils.Product;

public class ProductService {

	private ProductDAO productDao = new ProductDAO();

	public List<Product> fetchProductListBasedOnType(HttpServletRequest request, String type) {
		return productDao.fetchProductListBasedOnType(request, type);
	}
}
