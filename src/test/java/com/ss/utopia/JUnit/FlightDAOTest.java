package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.entity.Flight;

class FlightDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static FlightDAO flightConnection = null;

	@Test
	void testAddFlight() throws SQLException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		flightConnection = new FlightDAO(sqlConnection);
		Flight flight = new Flight();
		flight.setDeparture_time("This is not a date");
		Assertions.assertThrows(SQLException.class, () -> {
			flightConnection.addFlight(flight);
		  });
	}
	
	@Test
	void testDeleteFlight() throws SQLException, ClassNotFoundException {
		Flight flight = new Flight();
		flight.setReserved_seats(1000);
		List<Flight> flightsBefore = flightConnection.readFlights();
		flightConnection.deleteFlight(flight);
		List<Flight> flightsAfter = flightConnection.readFlights();

		assertEquals(flightsBefore.size(), flightsAfter.size());
	}
	
	@Test
	void testReadFlight() throws SQLException, ClassNotFoundException {	
		sqlConnection = DriverManager.getConnection(url, username, password);
		flightConnection = new FlightDAO(sqlConnection);
//		assertNotEquals(0, flightConnection.readFlights().size());
	}
	
	void testUpdateFlight() throws SQLException {
		Assertions.assertThrows(SQLException.class, () -> {
//		    airportConnection.updateAirport(airport, "zzz", "test");
		  });
	}
}
