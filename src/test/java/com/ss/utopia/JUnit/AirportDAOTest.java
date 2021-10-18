package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.entity.Airport;


class AirportDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static AirportDAO airportConnection = null;
	
	
	@Test
	void testAddAirport() throws SQLException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		airportConnection = new AirportDAO(sqlConnection);
		Airport airport = new Airport();
		airport.setAirportCode("ASDFASFASF");
		airport.setCityName("BAD CITY");
		Assertions.assertThrows(SQLException.class, () -> {
		    airportConnection.addAirport(airport);
		  });
	}
	
	@Test
	void testDeleteAirport() throws SQLException, ClassNotFoundException {
		Airport airport = new Airport();
		airport.setAirportCode("ASDFASFASF");
		airport.setCityName("BAD CITY");
		
		List<Airport> airportsBefore = airportConnection.readAirports();
	    airportConnection.deleteAirport(airport);
		List<Airport> airportsAfter = airportConnection.readAirports();

		assertEquals(airportsBefore.size(), airportsAfter.size());
	}
	
	@Test
	void testReadAirport() throws SQLException, ClassNotFoundException {
//		assertNotEquals(0, airportConnection.readAirports().size());
	}
	
	@Test
	void testUpdateAirport() throws SQLException {
		Airport airport = new Airport();
//		Assertions.assertThrows(SQLException.class, () -> {
//		    airportConnection.updateAirport(airport, "zzz", "test");
//		  });
	}
}