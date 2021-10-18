package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.entity.Passenger;

class PassengerDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static PassengerDAO passengerConnection = null;

	@Test
	void testAddPassenger() throws SQLException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		passengerConnection = new PassengerDAO(sqlConnection);
		Passenger passenger = new Passenger();
		passenger.setDob("Not a dob");
		Assertions.assertThrows(SQLException.class, () -> {
			passengerConnection.addPassenger(passenger);
		  });
	}
	
	@Test
	void testDeletePassenger() throws SQLException, ClassNotFoundException {
		Passenger passenger = new Passenger();
		passenger.setDob("1000");
		
		List<Passenger> passengersBefore = passengerConnection.readPassengers();
		passengerConnection.deletePassenger(passenger);
		List<Passenger> passengersAfter = passengerConnection.readPassengers();

		assertEquals(passengersBefore.size(), passengersAfter.size());
	}
	
	@Test
	void testReadPassenger() throws SQLException, ClassNotFoundException {	
		sqlConnection = DriverManager.getConnection(url, username, password);
		passengerConnection = new PassengerDAO(sqlConnection);
//		assertNotEquals(0, passengerConnection.readPassengers().size());
	}
	
//	void testExtractPassengers() throws SQLException, ClassNotFoundException {	
//		sqlConnection = DriverManager.getConnection(url, username, password);
//		passengerConnection = new PassengerDAO(sqlConnection);
//		assertNotEquals(0, passengerConnection.extractData().size());
//	}
	void testUpdatePassenger() throws SQLException {
		Assertions.assertThrows(SQLException.class, () -> {
//		    airportConnection.updateAirport(airport, "zzz", "test");
		  });
	}
}