package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Seat;
import com.ss.utopia.entity.User;
import com.ss.utopia.entity.Seat;

public class SeatDAO extends BaseDAO<Seat>{

	public SeatDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addSeat(Seat seat) throws ClassNotFoundException, SQLException{
		save("INSERT INTO flight_seat (booking_id, seat_class) VALUES(?,?)", new Object[] {seat.getBooking_id(), seat.getSeat_class()});
	}
	public void updateSeat(Seat seat) throws ClassNotFoundException, SQLException{
		save("UPDATE flight_seat set seat_class = ? where booking_id = ?", 
				new Object[] {seat.getSeat_class(), seat.getBooking_id()});
	}
	public void deleteSeat(Seat seat) throws ClassNotFoundException, SQLException{
		//NOTE: this is making the assumption that booking_id is a ticket, meaning each one is unique 
		save("delete from flight_seat where booking_id = ?", new Object[] { seat.getBooking_id()});
	}
	public List<Seat> readSeats() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight_seat", null);
	}
	public void printOutSeats(List<Seat> seats) {
		System.out.println("Here are all the seats: ");
		for(int k = 0; k< seats.size(); k++) {
	
			String seatInfo = String.format("booking_id: %s / seat_class: %s", 
					seats.get(k).getBooking_id(), seats.get(k).getSeat_class()); 
			System.out.println(seatInfo);
		}
			
	}
	@Override
	protected List<Seat> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Seat> seats = new ArrayList<>();
		while(rs.next()) {
			Seat seat = new Seat();
			seat.setBooking_id(rs.getInt("booking_id"));
			seat.setSeat_class(rs.getInt("seat_class"));
			seats.add(seat);
		}
		return seats;
	}

}
