package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;

public class AirportDAO extends BaseDAO<Airport>{
	
	public AirportDAO(Connection conn) {
		super(conn);
	}
	
	
	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException{
		String iata_id = airport.getAirportCode();
		String city = airport.getCityName();
		save("INSERT INTO airport (iata_id, city) VALUES (?,?)", new Object[] {
				iata_id, city
		});
	}
	public void deleteAirport(Airport airport) throws ClassNotFoundException, SQLException{
		save("delete from airport where iata_id = ?", new Object[]{airport.getAirportCode()});
	}
	
	public void updateAirport(Airport airport, String newIataID, String newCityName) throws ClassNotFoundException, SQLException {
		save("UPDATE airport set iata_id = ?, city = ? where iata_id =?", new Object[] {
				newIataID, newCityName, airport.getAirportCode()});	
	}
	
	public List<Airport> readAirports() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airport", null);
	}
	
	public void printOutAirports(List<Airport> airports) {
		System.out.println("Size of flights: " + airports.size());
		for(int k = 0; k< airports.size(); k++) {
	
			String airportInfo = String.format("iata_id: %s / city: %s", 
					airports.get(k).getAirportCode(), airports.get(k).getCityName()); 
			System.out.println(airportInfo);
		}		
	}
	
	@Override
	protected List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airport> airports = new ArrayList<>();
		while(rs.next()) {
			Airport airport = new Airport();
			airport.setAirportCode(rs.getString("iata_id"));
			airport.setCityName(rs.getString("city"));
			airports.add(airport);
		}
		
		return airports;
	}

}
