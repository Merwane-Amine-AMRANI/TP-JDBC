package org.amrani.testConnexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnexion {

	public static void main(String[] args) {

		String url = "jdbc:mysql://127.0.0.1:3306/tp_jdbc?useSSL=false";
		String user = "user_jdbc";
		String password = "root";
		try (Connection connection = 
				DriverManager.getConnection(url, user, password);) {
			System.out.println("error");
			PreparedStatement statement = 
					connection.prepareStatement("select * from User");
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				
				System.out.printf("User: %d %s %d\n", id, name, age);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
