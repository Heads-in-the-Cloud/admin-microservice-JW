package com.ss.utopia.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Service;

import com.ss.utopia.dao.AirportRepository;
import com.ss.utopia.entity.Airport;

@Service
public class AirportService{
	@Autowired
	private AirportRepository airportRepo;
	
	
	public ResponseEntity<String> createAirport(String airportCode, String cityName) throws ClassNotFoundException, SQLException {
		Collection<Airport> airportExist = airportRepo.findAirportByAirportCodeNative(airportCode);
		if(!airportExist.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This airport already exists");
		}
		Airport airport = new Airport();
		airport.setAirportCode(airportCode);
		airport.setCityName(cityName);
		airportRepo.save(airport);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Post Succeeded");
	}
	
	public Iterable<Airport> getAirports() throws ClassNotFoundException, SQLException {		
		Iterable<Airport> airports = airportRepo.findAll();
		return airports;
	}
	
	public ResponseEntity<Airport> getAirportById(String iata_id) {
		Collection<Airport> airports = airportRepo.findAirportByAirportCodeNative(iata_id);
		if(airports.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(airports.iterator().next());
	}
	public ResponseEntity<String> updateAirport(String airportCode, String updateCityName) throws ClassNotFoundException, SQLException {
		Collection<Airport> airportExist = airportRepo.findAirportByAirportCodeNative(airportCode);
		if(airportExist.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Airport does not exist");		}
		Airport airport = new Airport();
		airport.setAirportCode(airportCode);
		airport.setCityName(updateCityName);
		airportRepo.save(airport);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully updated");

	}
	
	public void deleteAirport(String iataId) throws ClassNotFoundException, SQLException {	
		airportRepo.deleteAirport(iataId);
	}
}
	