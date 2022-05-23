package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;


public class UserDao{
	private static final String SQL_SELECT_ID_PASS = "select login_id ,password ,name from users where login_id = ? AND password = ?";
	
	private Connection connection;
	
	public UserDao(Connection connection) {
		this.connection = connection;
	}
	public User fintdByProductIdPass(String id, String password) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_PASS)) {
			
			stmt.setString(1, id);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new User(rs.getString("login_id"),rs.getString("password"),rs.getString("name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}