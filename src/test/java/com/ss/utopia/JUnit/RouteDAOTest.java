package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Route;

class RouteDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static RouteDAO routeConnection = null;

	@Test
	void testAddRoute() throws SQLException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		routeConnection = new RouteDAO(sqlConnection);
		Route route = new Route();
		route.setOriginAirport("NO IATA");
		Assertions.assertThrows(SQLException.class, () -> {
			routeConnection.addRoute(route);
		  });
	}
	
	@Test
	void testDeleteRoute() throws SQLException, ClassNotFoundException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		routeConnection = new RouteDAO(sqlConnection);
		Route route = new Route();
		route.setDestinationAirport("NONEXISTING");
		
		List<Route> routesBefore = routeConnection.readRoutes();
		routeConnection.deleteRoute(route);
		List<Route> routesAfter = routeConnection.readRoutes();

		assertEquals(routesBefore.size(), routesAfter.size());
	}
	
	@Test
	void testReadRoute() throws SQLException, ClassNotFoundException {	
		sqlConnection = DriverManager.getConnection(url, username, password);
		routeConnection = new RouteDAO(sqlConnection);
	//		assertNotEquals(0, routeConnection.readRoutes().size());
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