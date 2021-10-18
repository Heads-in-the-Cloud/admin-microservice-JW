package com.ss.utopia.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;


public class BookingDAO extends BaseDAO<Booking>{
	public BookingDAO(Connection conn) {
		super(conn);
	}
	
	public void addBooking(Booking booking) throws ClassNotFoundException, SQLException{
		save("INSERT INTO booking (is_active, confirmation_code) VALUES (?,?)", new Object[] {
				booking.getIs_active(), booking.getConfirmation_code()
		});
	}
	
	public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException{
		save("UPDATE booking set is_active = ?, confirmation_code = ? where id =?", new Object[] {
				booking.getIs_active(), booking.getConfirmation_code(), booking.getId()});	
	}
	public void deleteBooking(Booking booking) throws ClassNotFoundException, SQLException{
		save("delete from booking where id = ?", new Object[]{booking.getId()});
	}
	public void overrideCancel(Booking booking) throws ClassNotFoundException, SQLException{
		if(booking.getIs_active()==1) {
			System.out.println("This booking has not been canceled");
			return;
		}
		save("UPDATE booking set is_active = 1 where id =?", new Object[] {booking.getId()});
		save("UPDATE booking_payment set refunded = 0 where booking_id =?", 
				new Object[] {booking.getId()});
	}
	public List<Booking> readBookings() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking", null);
	}
	
	
	public void printOutBookings(List<Booking> bookings) throws ClassNotFoundException, SQLException{
		System.out.println("Size of bookings: " + bookings.size());
		for(int k = 0; k< bookings.size(); k++) {
	
			String bookingInfo = String.format("booking_id: %s / is_active: %s / confirmation_code: %s", 
					bookings.get(k).getId(), bookings.get(k).getIs_active(), bookings.get(k).getConfirmation_code()); 
			System.out.println(bookingInfo);
		}
	}
	
	@Override
	protected List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> bookings = new ArrayList<>();
	
		while(rs.next()) {
			Booking booking = new Booking();
			booking.setId(rs.getInt("id"));
			booking.setIs_active(rs.getInt("is_active"));
			booking.setConfirmation_code(rs.getString("confirmation_code"));
			bookings.add(booking);
		}
		
		return bookings;
	}

}
