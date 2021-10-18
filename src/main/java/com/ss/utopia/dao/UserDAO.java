package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.User;

public class UserDAO extends BaseDAO<User>{

	public UserDAO(Connection conn) {
		super(conn);
	}
	public void addUser(User user) throws ClassNotFoundException, SQLException{
		if(user.getRole_id() == 1) {
			System.out.println("Tried to add an admin, did not go through");
			return;
		}
		save("INSERT INTO User (role_id, given_name, family_name, username, email, password, phone) "
				+ "VALUES(?,?,?,?,?,?,?)", new Object[] {user.getRole_id(), user.getGiven_name(),
						user.getFamily_name(), user.getUsername(), user.getEmail(), 
						user.getPassword(), user.getPhone()});
	}
	//removed role_id from here so can't change permissions of a user
	public void updateUser(User User) throws ClassNotFoundException, SQLException{
		save("UPDATE User set given_name = ?, family_name = ?, username = ?, email = ?,"
				+ "password = ?, phone = ? where id = ?", 
				new Object[] {User.getGiven_name(),
						User.getFamily_name(), User.getUsername(), User.getEmail(), 
						User.getPassword(), User.getPhone(), User.getUser_id()});
	}
	public void deleteUser(User User) throws ClassNotFoundException, SQLException{
		save("delete from User where id = ?", new Object[] { User.getUser_id()});
	}
	public List<User> readAgents() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM User WHERE role_id = 2", null);
	}
	public List<User> readTravelers() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM User WHERE role_id = 3", null);
	}

	public List<User> readUsers() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM User", null);
	}
	public void printOutUsers(List<User> Users) {
		System.out.println("Size of Users: " + Users.size());
		for(int k = 0; k< Users.size(); k++) {
	
			String UserInfo = String.format("id: %s / role_id: %s / given_name: %s / family_name: %s /"
					+ "username: %s / email: %s / password: %s / phone: %s", 
					Users.get(k).getUser_id(), Users.get(k).getRole_id(), Users.get(k).getGiven_name(),
					Users.get(k).getFamily_name(), Users.get(k).getUsername(), Users.get(k).getEmail(), 
					Users.get(k).getPassword(), Users.get(k).getPhone()); 
			System.out.println(UserInfo);
		}
			
	}
	@Override
	protected List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<User> Users = new ArrayList<>();
		while(rs.next()) {
			User User = new User();
			User.setUser_id(rs.getInt("id"));
			User.setRole_id(rs.getInt("role_id"));
			User.setGiven_name(rs.getString("given_name"));
			User.setFamily_name(rs.getString("family_name"));
			User.setUsername(rs.getString("username"));
			User.setPassword(rs.getString("password"));
			User.setEmail(rs.getString("email"));
			User.setPhone(rs.getString("phone"));
			Users.add(User);
		}
		return Users;
	}

}
