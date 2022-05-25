package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;


public class ProductDao{
	private static final String SQL_SELECT_KEY_NAME = "SELECT product_id, category_id, p.name as p_name,description,price,c.name as c_name FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE p.name LIKE ? or c.name LIKE ?";
	private static final String SQL_INSERT_PRODUCT = "insert into products (product_id, category_id, name, price, description)values(?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ID = "SELECT product_id, category_id, p.name as p_name,description,price,c.name as c_name FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE product_id = ?";

	private Connection connection;
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Product> fintdByProductKey(String name) {
		List<Product> p = new ArrayList<>();
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_KEY_NAME)) {
			
			stmt.setString(1, "%"+name+"%");
			stmt.setString(2, "%"+name+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			 p.add(new Product(rs.getInt("product_id"),rs.getInt("category_id"),rs.getString("p_name"),rs.getInt("price"),rs.getString("description"),rs.getString("c_name")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;
	}
	
	public void insert(Product product) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_PRODUCT)) {

			stmt.setInt(1, product.getProduct_id());
			stmt.setInt(2, product.getCategory_id());
			stmt.setString(3, product.getName());	
			stmt.setInt(4, product.getPrice());
			stmt.setString(5, product.getDescription());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Product fintdByProductId(Integer userId) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Product(rs.getInt("product_id"),rs.getInt("category_id"),rs.getString("p_name"),rs.getInt("price"),rs.getString("description"),rs.getString("c_name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}