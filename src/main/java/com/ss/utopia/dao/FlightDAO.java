package com.ss.utopia.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;


public class FlightDAO extends BaseDAO<Flight>{
	public FlightDAO(Connection conn) {
		super(conn);
	}
	
	// GOOD
	// TODO: Auto-increment flight_id
	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException {
		int flight_id = (int)(Math.random()*10000);
		save("INSERT INTO flight (id, route_id, airplane_id, departure_time, reserved_seats,seat_price) "
				+ "VALUES(?,?,?,?,?,?)", new Object[] {flight_id, flight.getRoute_id(), flight.getAirplane_id(),
						flight.getDeparture_time(), flight.getReserved_seats(), flight.getSeat_price()});
	}
	
	//	GOOD
	//TODO: when auto increment is figured out, change from getRoute_id to get_id
	public void deleteFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("delete from flight where id = ?", new Object[] { flight.getId()});
	}
	
	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("UPDATE flight set route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ? where id = ?", new Object[] {flight.getRoute_id(), flight.getAirplane_id(),
						flight.getDeparture_time(), flight.getReserved_seats(), flight.getSeat_price(), flight.getId()});
	}
	
	public List<Flight> readFlights() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight", null);
	}
	
	public void printOutFlights(List<Flight> flights) {
		System.out.println("Info of all flights:");
		for(int k = 0; k< flights.size(); k++) {
	
			String flightInfo = String.format("flight_id: %s / airplane_id: %s / route_id: %s / reserved_seats: %s / seat_price: %s / departure_time: %s", 
					flights.get(k).getId(), flights.get(k).getAirplane_id(), flights.get(k).getRoute_id(), flights.get(k).getReserved_seats(), flights.get(k).getSeat_price(), flights.get(k).getDeparture_time()); 
			System.out.println(flightInfo);
		}
			
	}
	
	@Override
	protected List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<>();
		while(rs.next()) {
			Flight flight = new Flight();
			flight.setId(rs.getInt("id"));
			flight.setAirplane_id(rs.getInt("airplane_id"));
			flight.setRoute_id(rs.getInt("route_id"));
			flight.setReserved_seats(rs.getInt("reserved_seats"));
			flight.setSeat_price(rs.getInt("seat_price"));
			flight.setDeparture_time(rs.getString("departure_time"));
			flights.add(flight);
		}
		
		return flights;
	}
}
