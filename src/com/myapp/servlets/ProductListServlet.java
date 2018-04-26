package com.myapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.ProductDAO;
import com.myapp.service.ProductService;
import com.myapp.utils.Product;

public class ProductListServlet extends HttpServlet {

	private ProductService productService = new ProductService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		System.out.println("ProductListServlet: doGet() productType: " + type);

		// Fetch the product based on query parameter
		List<Product> productList = productService.fetchProductListBasedOnType(request, type);

		request.setAttribute("productList", productList);

		RequestDispatcher rd = request.getRequestDispatcher("productList.jsp");
		rd.forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
