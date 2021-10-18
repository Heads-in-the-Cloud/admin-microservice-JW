package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ss.utopia.dao.SeatDAO;
import com.ss.utopia.entity.Seat;

class SeatDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
//	public static final String url = "jdbc:mysql://localhost/utopia";
//	public static final String username = "root";
//	public static final String password = "root";
//	public static Connection sqlConnection = null;
//	public static SeatDAO seatConnection = null;
//
//	@Test
//	void testAddSeat() throws SQLException {
//		sqlConnection = DriverManager.getConnection(url, username, password);
//		seatConnection = new SeatDAO(sqlConnection);
//		Seat seat = new Seat();
//		seat.setBooking_id(-100);
//		Assertions.assertThrows(SQLException.class, () -> {
//			seatConnection.addSeat(seat);
//		  });
//	}
//	
//	@Test
//	void testDeleteSeat() throws SQLException, ClassNotFoundException {
//		Seat seat = new Seat();
//		seat.setSeat_class(4);
//		List<Seat> seatsBefore = seatConnection.readSeats();
//		seatConnection.deleteSeat(seat);
//		List<Seat> seatsAfter = seatConnection.readSeats();
//
//		assertEquals(seatsBefore.size(), seatsAfter.size());
//	}
//	
//	@Test
//	void testReadSeat() throws SQLException, ClassNotFoundException {	
//		sqlConnection = DriverManager.getConnection(url, username, password);
//		seatConnection = new SeatDAO(sqlConnection);
//		assertNotEquals(0, seatConnection.readSeats().size());
//	}
//	
////	void testExtractPassengers() throws SQLException, ClassNotFoundException {	
////		sqlConnection = DriverManager.getConnection(url, username, password);
////		passengerConnection = new PassengerDAO(sqlConnection);
////		assertNotEquals(0, passengerConnection.extractData().size());
////	}
//	void testUpdateRoute() throws SQLException {
//		Assertions.assertThrows(SQLException.class, () -> {
////		    airportConnection.updateAirport(airport, "zzz", "test");
//		  });
//	}
}