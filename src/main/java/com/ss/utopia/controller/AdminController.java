package com.ss.utopia.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.SQLException;
import java.util.Collection;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.service.AirportService;

@RestController
@RequestMapping("/airport")
public class AdminController {
	@Autowired
	AirportService airportService; // = new AirportService();

	@PostMapping
	public @ResponseBody ResponseEntity<String> addAirport(@RequestParam String iata_id, @RequestParam String city)
			throws ClassNotFoundException, SQLException {
		return airportService.createAirport(iata_id, city);
	}

	@GetMapping
	public @ResponseBody Iterable<Airport> getAirports() throws ClassNotFoundException, SQLException {
		return airportService.getAirports();
	}
	
	@GetMapping("/{iata_id}")
	public @ResponseBody ResponseEntity<Airport> getAirportById(@PathVariable String iata_id){
		return airportService.getAirportById(iata_id);
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<String> updateAirport(@RequestParam String iata_id, @RequestParam String city) throws ClassNotFoundException, SQLException {
		return airportService.updateAirport(iata_id, city);
	}
	
	@DeleteMapping("/{iata_id}")
	public @ResponseBody void deleteAirport(@PathVariable String iata_id) throws ClassNotFoundException, SQLException {
		airportService.deleteAirport(iata_id);
	}
	
}
