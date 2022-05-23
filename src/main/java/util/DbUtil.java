package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	public DbUtil() {

	}

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql:syouhin", "axizuser", "axiz");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}