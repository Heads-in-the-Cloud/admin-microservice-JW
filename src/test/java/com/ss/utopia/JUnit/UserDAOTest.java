package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.User;

class UserDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static UserDAO userConnection = null;

	@Test
	void testAddUser() throws SQLException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		userConnection = new UserDAO(sqlConnection);
		User user = new User();
		user.setUser_id(-1);
		Assertions.assertThrows(SQLException.class, () -> {
			userConnection.addUser(user);
		  });
	}
	
	@Test
	void testDeleteUser() throws SQLException, ClassNotFoundException {
		User user = new User();
		user.setUser_id(-1);
		
		List<User> usersBefore = userConnection.readUsers();
		userConnection.deleteUser(user);
		List<User> usersAfter = userConnection.readUsers();

		assertEquals(usersBefore.size(), usersAfter.size());
	}
	
	@Test
	void testReadUser() throws SQLException, ClassNotFoundException {	
		sqlConnection = DriverManager.getConnection(url, username, password);
		userConnection = new UserDAO(sqlConnection);
//		assertNotEquals(0, userConnection.readUsers().size());
	}
	
//	void testExtractPassengers() throws SQLException, ClassNotFoundException {	
//		sqlConnection = DriverManager.getConnection(url, username, password);
//		passengerConnection = new PassengerDAO(sqlConnection);
//		assertNotEquals(0, passengerConnection.extractData().size());
//	}
	void testUpdateRoute() throws SQLException {
		Assertions.assertThrows(SQLException.class, () -> {
//		    airportConnection.updateAirport(airport, "zzz", "test");
		  });
	}
}