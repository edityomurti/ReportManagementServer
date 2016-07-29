package com.solusi247.reportManagementApi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.log.Log;
import com.solusi247.reportManagementApi.model.User;
import com.sun.istack.logging.Logger;

public class UserDao {
	
	private Connection connection;
	
	private final String getAllUser = "SELECT * FROM user";
	private final String getUser = "SELECT * FROM user WHERE email = ? AND password = ?;";
	private final String createUser = "INSERT INTO user(name, email, password)  VALUES (?,?,?);";
	private final String findUser = "SELECT * FROM user where email = ?;";
	
	public UserDao(){
		connection = Database.getConnection();
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(getAllUser);			
			while(rs.next()){
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
				user.setCreated_at(rs.getString("created_at"));
				users.add(user);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User getUser(String email, String password) {
		User user = new User();		
		try{
			PreparedStatement ps = connection.prepareStatement(getUser);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();			
			
			if(rs.next()){
				user.setUser_id(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setCreated_at(rs.getString("created_at"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void createUser(User user){
		PreparedStatement ps = null;
		try{
			ps = connection.prepareStatement(createUser);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public User findUser(String email){
		User user = new User();
		try {
			PreparedStatement ps = connection.prepareStatement(findUser);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				user.setUser_id(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			} else {
				return null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
//	public static boolean save(User user){
//		Connection connection = null;
//		
//		try {
//			connection = Database.getConnection();
//			Statement stmt = connection.createStatement();
////			String insertTableSQL = "INSERT INTO DBUSER"
////					+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
////					+ "(?,?,?,?)";
//			String query = "INSERT INTO USER (password, nama, email, salt) VALUES (?,?,?,?)";
//			
//		} catch (SQLException sqle) {
//            sqle.printStackTrace();
////            throw sqle;
//        } catch (Exception e) {
//            //e.printStackTrace();
//            // TODO Auto-generated catch block
//            if (connection != null) {
//            	connection.close();
//            }
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//            	connection.close();
//            }
//        }
//		
//		return true;
//	}
	
//	public static boolean loginProcess(User user){
//		
//	}
	
}
