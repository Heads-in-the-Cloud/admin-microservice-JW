//package com.ss.utopia.service;
//
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ss.utopia.dao.BookingRepository;
//import com.ss.utopia.dao.FlightBookingsRepository;
//import com.ss.utopia.dao.FlightRepository;
//import com.ss.utopia.entity.Airplane;
//import com.ss.utopia.entity.Booking;
//import com.ss.utopia.entity.Flight;
//import com.ss.utopia.entity.FlightBookings;
//import com.ss.utopia.entity.Route;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class FlightBookingsService {
//	@Autowired 
//	FlightRepository flightRepo;
//	
//	@Autowired
//	BookingRepository bookingRepo;
//	
//	@Autowired
//	FlightBookingsRepository fbRepo;
//	
//	public Iterable<FlightBookings> getFBs(){
//		return fbRepo.findAll();
//	}
//	
//	public ResponseEntity<FlightBookings> getFbByFlightId(Integer fID){
//		Optional<FlightBookings> fb = fbRepo.findById(fID);
//		if(fb.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(fb.get());
//	}
//	
//	public ResponseEntity<String> createFB(FlightBookings fb){
//		Optional <Flight> checkFlight = flightRepo.findById(fb.getFlight().getId());
//		Optional <Booking> checkBooking = bookingRepo.findById(fb.getBooking().getId());
//		if(checkFlight.isEmpty() || checkBooking.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight or Booking with this id does not exist");
//		}
//		fbRepo.save(fb);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Post Succeeded");
//	}
//	public ResponseEntity<String> updateFB(Integer id, FlightBookings fb){
//		Optional<FlightBookings> fbExist = fbRepo.findById(id);
//		if(fbExist.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight Booking with this id does not exist");
//		}
//		if(fb.getFlight().getId() != id) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight ID's do not match.");
//
//		}
//		Optional <Flight> checkFlight = flightRepo.findById(fb.getFlight().getId());
//		Optional <Booking> checkBooking = bookingRepo.findById(fb.getBooking().getId());
//		if(checkFlight.isEmpty() || checkBooking.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight or Booking with this id does not exist");
//		}
//		fbRepo.save(fb);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Update Succeeded");
//	}
//	
//	public ResponseEntity<String> deleteFB(Integer id){
//		Optional<FlightBookings> fbExist = fbRepo.findById(id);
//		if(fbExist.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight Bookings does not exist");
//		}
//		fbRepo.deleteById(id);
//		return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
//	}
//}
