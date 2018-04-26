package com.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.myapp.utils.Product;

public class ProductDAO {

	public List<Product> fetchProductListBasedOnType(HttpServletRequest request, String type) {
		List<Product> productList = new ArrayList<Product>();

		ServletContext context = request.getServletContext();
		String driverName = context.getInitParameter("driverName");
		String dbUrl = context.getInitParameter("dburl");
		String dbUsername = context.getInitParameter("username");
		String dbPassword = context.getInitParameter("password");

		try {
			// Copy mysql-connector.jar to AppMainFolder -> WebContent -> WEB-INF -> lib
			// This step is required to connect to database.
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			Statement st = con.createStatement();
			String query = "select * from product where type = '" + type + "';";
			ResultSet rt = st.executeQuery(query);

			while (rt.next()) {
				Product p = new Product();
				int id = rt.getInt("id");
				String name = rt.getString("name");
				String description = rt.getString("description");
				double price = rt.getDouble("price");

				p.setId(id);
				p.setName(name);
				p.setDescription(description);
				p.setPrice(price);
				p.setType(type);

				productList.add(p);
			}

			System.out.println("ProductListServlet: fetchProductListBasedOnType() type: " + type + " success");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productList;
	}

}
