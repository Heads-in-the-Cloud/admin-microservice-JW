package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Passenger;

public class PassengerDAO extends BaseDAO<Passenger>{
	public PassengerDAO(Connection conn) {
		super(conn);
	}
	
	
	public void addPassenger(Passenger passenger) throws ClassNotFoundException, SQLException{
		save("INSERT INTO passenger (booking_id, given_name, family_name, dob, gender, address) "
				+ "VALUES(?,?,?,?,?,?)", new Object[] {passenger.getBooking_id(), passenger.getGiven_name(),
						passenger.getFamily_name(), passenger.getDob(), passenger.getGender(), 
						passenger.getAddress()});
	}
	public void updatePassenger(Passenger passenger) throws ClassNotFoundException, SQLException{
		save("UPDATE passenger set booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender = ?,"
				+ "address = ? where id = ?", 
				new Object[] {passenger.getBooking_id(), passenger.getGiven_name(),
						passenger.getFamily_name(), passenger.getDob(), passenger.getGender(), 
						passenger.getAddress(), passenger.getPassenger_id()});
	}
	public void deletePassenger(Passenger passenger) throws ClassNotFoundException, SQLException{
		save("delete from passenger where id = ?", new Object[] { passenger.getPassenger_id()});
	}
	public List<Passenger> readPassengers() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM passenger", null);
	}
	
	public void printOutPassengers(List<Passenger> passengers) {
		System.out.println("Size of passengers: " + passengers.size());
		for(int k = 0; k< passengers.size(); k++) {
	
			String passengerInfo = String.format("id: %s / booking_id: %s / given_name: %s / family_name: %s /"
					+ "dob: %s / gender: %s / address: %s", 
					passengers.get(k).getPassenger_id(), passengers.get(k).getBooking_id(), passengers.get(k).getGiven_name(),
					passengers.get(k).getFamily_name(), passengers.get(k).getDob(), passengers.get(k).getGender(), 
					passengers.get(k).getAddress()); 
			System.out.println(passengerInfo);
		}
			
	}
	@Override
	protected List<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = new ArrayList<>();
		while(rs.next()) {
			Passenger passenger = new Passenger();
			passenger.setPassenger_id(rs.getInt("id"));
			passenger.setBooking_id(rs.getInt("booking_id"));
			passenger.setGiven_name(rs.getString("given_name"));
			passenger.setFamily_name(rs.getString("family_name"));
			passenger.setDob(rs.getString("dob"));
			passenger.setGender(rs.getString("gender"));
			passenger.setAddress(rs.getString("address"));
			passengers.add(passenger);
		}
		return passengers;
	}

}
