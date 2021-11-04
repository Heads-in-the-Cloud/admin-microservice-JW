//package com.ss.utopia.controller;
//
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import java.sql.SQLException;
//
//import com.ss.utopia.entity.Flight;
//import com.ss.utopia.entity.FlightBookings;
//import com.ss.utopia.service.FlightBookingsService;
//
//@RestController
//@RequestMapping("/flight-bookings")
//public class FlightBookingsController {
//	@Autowired
//	FlightBookingsService fbService;
//	
//	@GetMapping
//	public @ResponseBody Iterable<FlightBookings> getFBs() throws ClassNotFoundException, SQLException{
//		return fbService.getFBs();
//	}
//	
//	@GetMapping("/{id}")
//	public @ResponseBody ResponseEntity<FlightBookings> getFbByFlightId(@PathVariable Integer id){
//		return fbService.getFbByFlightId(id);
//	}
//	
//	@PostMapping
//	public @ResponseBody ResponseEntity<String> addFB(@RequestBody FlightBookings fb){
//		return fbService.createFB(fb);
//	}
//	
//	@PutMapping
//	public @ResponseBody ResponseEntity<String> updateFB(@RequestParam Integer id, @RequestBody FlightBookings fb){
//		return fbService.updateFB(id, fb);
//	}
//	
//	@DeleteMapping("/{id}")
//	public @ResponseBody ResponseEntity<String> deleteFlight(@PathVariable Integer id) {
//		return fbService.deleteFB(id);
//	}
//}
