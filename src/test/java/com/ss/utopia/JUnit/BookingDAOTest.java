package test.java.com.ss.utopia.JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Update 
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;

class BookingDAOTest {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static BookingDAO bookingConnection = null;

	@Test
	void testAdd() throws SQLException {
		sqlConnection = DriverManager.getConnection(url, username, password);
		bookingConnection = new BookingDAO(sqlConnection);

		Booking booking = new Booking();
		booking.setId(5);

		Assertions.assertThrows(SQLException.class, () -> {
			bookingConnection.addBooking(booking);
		  });
	}
	
	@Test
	void testDeleteBooking() throws SQLException, ClassNotFoundException {
		Booking booking = new Booking();
		booking.setId(-5);
		List<Booking> bookingBefore = bookingConnection.readBookings();
		bookingConnection.deleteBooking(booking);
		List<Booking> bookingAfter = bookingConnection.readBookings();
		assertEquals(bookingBefore.size(), bookingAfter.size());
	}
	
	@Test
	void testRead() throws SQLException, ClassNotFoundException {	

//		assertNotEquals(0, bookingConnection.readBookings().size());
	}
	
//	@Test
//	void testUpdateBooking() throws SQLException {
//		Booking booking = new Booking();
//
//		Assertions.assertThrows(SQLException.class, () -> {
//		    bookingConnection.updateBooking(booking);
//		  });
//	}
//	
//	@Test
//	void overrideCancelTest() throws ClassNotFoundException, SQLException {
//		Booking booking = new Booking();
//		booking.setId(100);
//		booking.setIs_active(1);
//		booking.setConfirmation_code("test");
//		
//		bookingConnection.overrideCancel(booking);
//		
//		Booking booking2 = new Booking();
//		booking.setId(100);
//		booking.setIs_active(1);
//		booking.setConfirmation_code("test");
//		
//		assertEquals(booking.getId(),booking2.getId());
//	}
}
