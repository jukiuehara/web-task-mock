package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;


public class ProductDao{
	private static final String SQL_SELECT_KEY_NAME = "SELECT * FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE name LIKE ?;";
	private static final String SQL_SELECT_KEY_CATEGORY = "SELECT * FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE category LIKE ?;";

	private Connection connection;
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Product> fintdByProductKey(String name) {
		List<Product> p = new ArrayList<>();
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_KEY_NAME)) {
			
			stmt.setString(1, "%"+name+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			 p.add(new Product(rs.getInt("product_id"),rs.getInt("category_id"),rs.getString("name"),rs.getInt("price"),rs.getString("description"),rs.getString("category")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;
	}
	public List<Product> fintdByProductKey2(String name) {
		List<Product> p = new ArrayList<>();
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_KEY_CATEGORY)) {
			
			stmt.setString(1, "%"+name+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				p.add(new Product(rs.getInt("product_id"),rs.getInt("category_id"),rs.getString("name"),rs.getInt("price"),rs.getString("description"),rs.getString("category")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;
	}
}