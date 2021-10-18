package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.SeatDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Passenger;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.Seat;
import com.ss.utopia.entity.User;

public class AdminService {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	public static Connection sqlConnection = null;
	public static FlightDAO flightConnection = null;
	public static RouteDAO routeConnection = null;
	public static SeatDAO seatConnection = null;
	public static BookingDAO bookingConnection = null;
	public static AirportDAO airportConnection = null;
	public static UserDAO userConnection = null;
	public static PassengerDAO passengerConnection = null;
	
	public static void main(String[] args) throws SQLException {
		try {
			// Set-up all SQL connections
			sqlConnection = DriverManager.getConnection(url, username, password);
			flightConnection = new FlightDAO(sqlConnection);
			routeConnection = new RouteDAO(sqlConnection);
			seatConnection = new SeatDAO(sqlConnection);
			bookingConnection = new BookingDAO(sqlConnection);
			airportConnection = new AirportDAO(sqlConnection);
			userConnection = new UserDAO(sqlConnection);
			passengerConnection = new PassengerDAO(sqlConnection);
			
			AdminService adminService = new AdminService();
			adminService.showMainMenu();
//			adminService.showFlightMenu();
//			adminService.showSeatsMenu();
//			adminService.showAirportMenu();
//			adminService.showUsersMenu();
//			adminService.showBookingsMenu();
//			adminService.showPassengersMenu();
			// conn.commit(); //don't forget this??
		} catch (Exception e) {
			System.out.println(e);
			if (sqlConnection != null) {
				sqlConnection.rollback();
			}
		} finally {
			if (sqlConnection != null) {
				sqlConnection.close();
			}
		}
	}

//	ConnectionUtil connUtil = new ConnectionUtil();

	public void showMainMenu() throws SQLException, ClassNotFoundException {
		System.out.println("Welcome to the Admin service menu! Select an option below to edit");

		System.out.println("1. Flights");
		System.out.println("2. Bookings/Tickets");
		System.out.println("3. Passengers");
		System.out.println("4. Airports");
		System.out.println("5. Seats ");
		System.out.println("6. Users");
		System.out.println("7. Override Trip Cancellation for a Booking/Ticket");
		
		int response =  checkIfValidMenuChoice(7);
		
		switch (response) {
		case 1:
			showFlightMenu();
			break;
		case 2:
			showBookingsMenu();
			break;
		case 3:
			showPassengersMenu();
			break;
		case 4:
			showAirportMenu();
			break;
		case 5:
			showSeatsMenu();
			break;
		case 6:
			showUsersMenu();
			break;
		case 7:
			overrideTicket();
			break;
		default:
			System.out.println("Error in choice");
		}

	}
	
//------------------------BOOKING/TICKET METHODS-----------------------------------------------
	public void showBookingsMenu() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the booking menu. What would you like to do?");
		int response = showCrudMenu("booking");
		printDivider();
		switch (response) {
		case 1:
			createBooking();
			break;
		case 2:
			readBookings();
			break;
		case 3:
			updateBooking();
			break;
		case 4:
			deleteBooking();
			break;
		case 5: 
			showMainMenu();
		default:
			System.out.println("Error in choosing booking option");
		}
		
		printDivider();
		showMainMenu();
	}
	
	public void createBooking() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the confirmation code: ");
		String confirmationCode = sc.next();
		int isActive = 1; 
		
		Booking newBooking = new Booking();
		newBooking.setConfirmation_code(confirmationCode);
		//new bookings should naturally be active
		newBooking.setIs_active(isActive);
		
		bookingConnection.addBooking(newBooking);
		System.out.println("Successfully created booking!");
	}
	
	public void readBookings() throws ClassNotFoundException, SQLException {
		List<Booking> bookings = bookingConnection.readBookings();
		bookingConnection.printOutBookings(bookings);
	}
	
	public void updateBooking() throws ClassNotFoundException, SQLException {
		List<Booking> bookings = bookingConnection.readBookings();

		System.out.println("Which booking would you like to update?");

		for (int index = 0; index < bookings.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". booking " + bookings.get(index).getId());
		}

		// Subtract by responseAsNumber by 1 to get appropriate flight object
		int index = checkIfValidMenuChoice(bookings.size()) - 1;
		
		Booking bookingToUpdate = bookings.get(index);
		String bookingInfo = String.format(
				"booking_id: %s / is_active: %s / confirmation_code: %s",
				bookingToUpdate.getId(), bookingToUpdate.getIs_active(), bookingToUpdate.getConfirmation_code());
		System.out.println("Current booking info:");
		System.out.println(bookingInfo);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Change active status:");
		int isActive = sc.nextInt();
		
		System.out.println("Enter new confirmation code:");
		String confirmationCode = sc.next();
		
		bookingToUpdate.setIs_active(isActive);
		bookingToUpdate.setConfirmation_code(confirmationCode);
		bookingConnection.updateBooking(bookingToUpdate);
		
		System.out.println("Successfully updated booking!");
	}
	
	public void deleteBooking() throws ClassNotFoundException, SQLException {
		List<Booking> bookings = bookingConnection.readBookings();

		System.out.println("Which booking would you like to delete?");

		for (int index = 0; index < bookings.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". booking" + bookings.get(index).getId());
		}

		// Subtract by responseAsNumber by 1 to get appropriate booking object
		int index = checkIfValidMenuChoice(bookings.size()) - 1;
		bookingConnection.deleteBooking(bookings.get(index));
		System.out.println("Successfully deleted booking!");
	}
	
	
	
//------------------------PASSENGER METHODS-----------------------------------------------
	public void showPassengersMenu() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the passengers menu. What would you like to do?");
		int response = showCrudMenu("passenger");
		printDivider();

		switch (response) {
		case 1:
			createPassenger();
			break;
		case 2:
			readPassengers();
			break;
		case 3:
			updatePassenger();
			break;
		case 4:
			deletePassenger();
			break;
		case 5: 
			showMainMenu();
		default:
			System.out.println("Error in choosing flight option");
		}
		
		printDivider();
		showMainMenu();
	}
	
	public void createPassenger() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Passenger newPassenger = new Passenger();
		
		System.out.println("Enter booking id:");
		int bookingId = sc.nextInt();
		newPassenger.setBooking_id(bookingId);
		
		System.out.println("Enter given name:");
		String givenName = sc.next();
		newPassenger.setGiven_name(givenName);
		
		System.out.println("Enter family name:");
		String familyName = sc.next();
		newPassenger.setFamily_name(familyName);
		
		System.out.println("Enter date of birth:");
		String dob = sc.next();
		newPassenger.setDob(dob);
		
		System.out.println("Enter gender:");
		String gender = sc.next();
		newPassenger.setGender(gender);
		
		System.out.println("Enter address:");
		sc.nextLine();
		String address = sc.nextLine();
		newPassenger.setAddress(address);
		
		passengerConnection.addPassenger(newPassenger);
		System.out.println("Successfully added passenger!");
	}
	
	public void readPassengers() throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = passengerConnection.readPassengers();
		passengerConnection.printOutPassengers(passengers);
	}
	
	public void updatePassenger() throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = passengerConnection.readPassengers();

		System.out.println("Which passenger would you like to update?");

		for (int index = 0; index < passengers.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + passengers.get(index).getPassenger_id() + " / " + passengers.get(index).getGiven_name() + " " + passengers.get(index).getFamily_name());
		}

		// Subtract by responseAsNumber by 1 to get appropriate flight object
		int index = checkIfValidMenuChoice(passengers.size()) - 1;

		Passenger passengerToUpdate = passengers.get(index);
		
		String passengerInfo = String.format(
				"passenger_id: %s / booking_id: %s / given_name: %s / family_name: %s / dob: %s / gender: %s / address: %s",
				passengerToUpdate.getPassenger_id(), passengerToUpdate.getBooking_id(), passengerToUpdate.getGiven_name(),
				passengerToUpdate.getFamily_name(), passengerToUpdate.getDob(), passengerToUpdate.getGender(), passengerToUpdate.getAddress());
		System.out.println("Current passenger info:");
		System.out.println(passengerInfo);
		
		Scanner sc = new Scanner(System.in);
		
		//TODO: Functionality to check if the input is a valid booking_id and unique 
		System.out.println("Enter new booking id:");
		int bookingId = sc.nextInt();
		passengerToUpdate.setBooking_id(bookingId);
		
		System.out.println("Enter new given name:");
		String givenName = sc.next();
		passengerToUpdate.setGiven_name(givenName);
		
		System.out.println("Enter new family name:");
		String familyName = sc.next();
		passengerToUpdate.setFamily_name(familyName);
		
		System.out.println("Enter new date of birth:");
		String dob = sc.next();
		passengerToUpdate.setDob(dob);
		
		System.out.println("Enter new gender:");
		String gender = sc.next();
		passengerToUpdate.setGender(gender);
		
		System.out.println("Enter new address:");
		sc.nextLine();
		String address = sc.nextLine();
		passengerToUpdate.setAddress(address);
		
		passengerConnection.updatePassenger(passengerToUpdate);
		System.out.println("Successfully updated passenger!");
		printDivider();
		
	}
	
	public void deletePassenger() throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = passengerConnection.readPassengers();

		System.out.println("Which passenger would you like to delete?");

		for (int index = 0; index < passengers.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + passengers.get(index).getPassenger_id() + " / " + passengers.get(index).getGiven_name()+ " " + passengers.get(index).getFamily_name());
		}

		// Subtract by responseAsNumber by 1 to get appropriate flight object
		int index = checkIfValidMenuChoice(passengers.size()) - 1;
		String name = passengers.get(index).getGiven_name()+ " " + passengers.get(index).getFamily_name();
		passengerConnection.deletePassenger(passengers.get(index));
		System.out.println("Successfully deleted " + name + " from passengers!");
	}
//----------------------OVERRIDE TICKET MENU---------------------------------------
	
	public void overrideTicket() throws ClassNotFoundException, SQLException {
		System.out.println("Which ticket cancellation would you like to override?");
		List<Booking> bookings = bookingConnection.readBookings();
		List<Booking> inactiveBookings = new ArrayList<Booking>();
		
		for (int index = 0; index < bookings.size(); index++) {
			if(bookings.get(index).getIs_active() == 0) {
				inactiveBookings.add(bookings.get(index));
			}
		}
		for (int index = 0; index < inactiveBookings.size(); index++) {
			int adjustedIndex = index+1;
				System.out.println(adjustedIndex + ". " + inactiveBookings.get(index).getId() + " / " + inactiveBookings.get(index).getConfirmation_code());
		}
		
		System.out.println(inactiveBookings.size()+1 + ". Go back to Main Menu");
		int index = checkIfValidMenuChoice(inactiveBookings.size()+1);
		if(index == inactiveBookings.size()+1) {
			showMainMenu();
			printDivider();
		}
		bookingConnection.overrideCancel(inactiveBookings.get(index));
		System.out.println("Successfully overrode ticket cancellation!");
		
		printDivider();
		showMainMenu();
	}
	
//------------------------FLIGHT METHODS-----------------------------------------------

	public void showFlightMenu() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the flight menu. What would you like to do?");
		int response = showCrudMenu("flight");
		printDivider();

		switch (response) {
		case 1:
			createFlight();
			break;
		case 2:
			readFlights();
			break;
		case 3:
			updateFlight();
			break;
		case 4:
			deleteFlight();
			break;
		case 5: 
			showMainMenu();
		default:
			System.out.println("Error in choosing flight option");
		}
		
		printDivider();
		showMainMenu();
	}

	public void createFlight() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the airplane id: ");
		int airplaneId = sc.nextInt();

		System.out.println("Choose a route: ");
		int routeIndex = readRoutesAndReturnChoice() - 1;
		List<Route> routes = routeConnection.readRoutes();
		int routeId = routes.get(routeIndex).getRouteId();

		System.out.println("Enter a departure time: ");
		sc.nextLine();
		String departureTime = sc.nextLine();

		// Assumed that the flight is new, so currently 0 reserved seats
		int reservedSeats = 0;

		System.out.println("Enter a price: ");
		int price = sc.nextInt();

		Flight newFlight = new Flight();
		newFlight.setAirplane_id(airplaneId);
		newFlight.setRoute_id(routeId);
		newFlight.setDeparture_time(departureTime);
		newFlight.setReserved_seats(0);
		newFlight.setSeat_price(price);

		flightConnection.addFlight(newFlight);
		System.out.println("Successfully created flight!");
	}

	public void readFlights() throws ClassNotFoundException, SQLException {
		List<Flight> flights = flightConnection.readFlights();
		flightConnection.printOutFlights(flights);
	}

	public void updateFlight() throws ClassNotFoundException, SQLException {
		List<Flight> flights = flightConnection.readFlights();

		System.out.println("Which flight would you like to update?");

		for (int index = 0; index < flights.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". flight id: " + flights.get(index).getId());
		}

		// Subtract by responseAsNumber by 1 to get appropriate flight object
		int index = checkIfValidMenuChoice(flights.size()) - 1;

		Flight flightToUpdate = flights.get(index);

		String flightInfo = String.format(
				"flight_id: %s / airplane_id: %s / route_id: %s / reserved_seats: %s / seat_price: %s / departure_time: %s",
				flightToUpdate.getId(), flightToUpdate.getAirplane_id(), flightToUpdate.getRoute_id(),
				flightToUpdate.getReserved_seats(), flightToUpdate.getSeat_price(), flightToUpdate.getDeparture_time());
		System.out.println("Current flight info:");
		System.out.println(flightInfo);

		// Get new flight values
		Scanner sc = new Scanner(System.in);

		System.out.println("New airplane_id: ");
		int airplaneId = sc.nextInt();
		flightToUpdate.setAirplane_id(airplaneId);

		System.out.println("New route_id: ");
		int routeId = sc.nextInt();
		flightToUpdate.setRoute_id(routeId);

		System.out.println("New reserved seats: ");
		int reservedSeats = sc.nextInt();
		flightToUpdate.setReserved_seats(reservedSeats);

		System.out.println("New seat price");
		int seatPrice = sc.nextInt();
		flightToUpdate.setSeat_price(seatPrice);


		System.out.println("New departure time");
		sc.nextLine();
		String departureTime = sc.nextLine();
		flightToUpdate.setDeparture_time(departureTime);
		flightConnection.updateFlight(flightToUpdate);
		System.out.println("Successfully updated flight!");
		printDivider();
	}

	public void deleteFlight() throws ClassNotFoundException, SQLException {
		List<Flight> flights = flightConnection.readFlights();

		System.out.println("Which flight would you like to delete?");

		for (int index = 0; index < flights.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". flight id: " + flights.get(index).getId());
		}

		// Subtract by responseAsNumber by 1 to get appropriate flight object
		int index = checkIfValidMenuChoice(flights.size()) - 1;
		flightConnection.deleteFlight(flights.get(index));
		System.out.println("Successfully deleted flight!");
	}

//---------------------------------SEATS METHODS--------------------------------------------------
	public void showSeatsMenu() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the seats menu. What would you like to do?");
		int response = showCrudMenu("seat");
		printDivider();

		switch (response) {
		case 1:
			createSeat();
			break;
		case 2:
			readSeats();
			break;
		case 3:
			updateSeats();
			break;
		case 4:
			deleteSeat();
			break;
		case 5: 
			showMainMenu();
		default:
			System.out.println("Error in choosing flight option");
		}
		
		printDivider();
		showMainMenu();
	}

	public void createSeat() throws ClassNotFoundException, SQLException {
		List<Booking> bookings = bookingConnection.readBookings();		
		System.out.println("Enter a booking id to link the seat to: ");
		
		//TODO: check if booking already has a seat
		for (int index = 0; index < bookings.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + bookings.get(index).getId());
		}

		int index = checkIfValidMenuChoice(bookings.size()) - 1;
		
		System.out.println("What class is the seat?");
		System.out.println("1. First");
		System.out.println("2. Business");
		System.out.println("3. Economy");
		
		Scanner sc = new Scanner(System.in);
		int seatClass = sc.nextInt();
		
		Seat newSeat = new Seat();
		newSeat.setBooking_id(bookings.get(index).getId());
		newSeat.setSeat_class(seatClass);
		
		seatConnection.addSeat(newSeat);
		System.out.println("Successfully added seat!");
	}
	
	public void readSeats() throws ClassNotFoundException, SQLException {
		List<Seat> seats = seatConnection.readSeats();
		seatConnection.printOutSeats(seats);
	}
	
	public void updateSeats() throws ClassNotFoundException, SQLException {
		List<Seat> seats = seatConnection.readSeats();		
		System.out.println("Enter a booking id of the seat you'd like to update: ");
		
		for (int index = 0; index < seats.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". seat " + seats.get(index).getBooking_id());
		}
		
		// Subtract by responseAsNumber by 1 to get appropriate seat object
		int index = checkIfValidMenuChoice(seats.size()) - 1;
		Seat seatToUpdate = seats.get(index);
		
		System.out.println("What new class is the seat?");
		System.out.println("1. First");
		System.out.println("2. Business");
		System.out.println("3. Economy");
		
		Scanner sc = new Scanner(System.in);
		int newSeatClass = sc.nextInt();
		
		seatToUpdate.setSeat_class(newSeatClass);
		seatConnection.updateSeat(seatToUpdate);
		System.out.println("Successfully updated seat!");
	}
	
	
	public void deleteSeat() throws ClassNotFoundException, SQLException {
		List<Seat> seats = seatConnection.readSeats();		
		System.out.println("Enter a booking id from which you'd like to delete the seat from: ");
		
		for (int index = 0; index < seats.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". seat " + seats.get(index).getBooking_id());
		}
		
		// Subtract by responseAsNumber by 1 to get appropriate seat object
		int index = checkIfValidMenuChoice(seats.size()) - 1;
		Seat seatToDelete = seats.get(index);
		
		seatConnection.deleteSeat(seatToDelete);
		System.out.println("Succesfully deleted seat!");
	}
	
//--------------------------------AIRPORT METHODS------------------------------------------------
	public void showAirportMenu() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the airports menu. What would you like to do?");
		int response = showCrudMenu("airport");
		printDivider();

		switch (response) {
		case 1:
			createAirport();
			break;
		case 2:
			readAirports();
			break;
		case 3:
			updateAirport();
			break;
		case 4:
			deleteAirport();
			break;
		case 5: 
			showMainMenu();
		default:
			System.out.println("Error in choosing flight option");
		}
		
		printDivider();
		showMainMenu();
	}
	
	public void createAirport() throws ClassNotFoundException, SQLException {
		System.out.println("Create a new airport..");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the new airport's 3-letter code: ");
		String airportCode = sc.next();
		
		System.out.println("Enter the airport's city name: ");
		String cityName = sc.next();
		
		Airport airport = new Airport();
		airport.setAirportCode(airportCode);
		airport.setCityName(cityName);
		airportConnection.addAirport(airport);
		System.out.println("Successfully created airport!");
	}
	
	public void readAirports() throws ClassNotFoundException, SQLException {		
		List<Airport> airports = airportConnection.readAirports();
		airportConnection.printOutAirports(airports);
	}
	
	public void updateAirport() throws ClassNotFoundException, SQLException {
		System.out.println("What airport would you like to edit?");
		
		List<Airport> airports = airportConnection.readAirports();		
		
		for (int index = 0; index < airports.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + airports.get(index).getAirportCode());
		}
		
		// Subtract by responseAsNumber by 1 to get appropriate seat object
		int index = checkIfValidMenuChoice(airports.size()) - 1;
		Airport airportToUpdate = airports.get(index);
		
		System.out.println("Enter the new city's code:");
		Scanner sc = new Scanner(System.in);
		String newCode = sc.next();
		
		System.out.println("Enter the new city's name:");
		String newCityName = sc.next();
		
		
		airportConnection.updateAirport(airportToUpdate, newCode, newCityName);
		System.out.println("Successfully updated airport");
	}
	
	public void deleteAirport() throws ClassNotFoundException, SQLException {		
		List<Airport> airports = airportConnection.readAirports();	
		System.out.println("Which airport would you like to delete?");

		
		for (int index = 0; index < airports.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + airports.get(index).getAirportCode() + " / " + airports.get(index).getCityName());
		}
		
		// Subtract by responseAsNumber by 1 to get appropriate seat object
		int index = checkIfValidMenuChoice(airports.size()) - 1;
		Airport airportToDelete = airports.get(index);
		
		airportConnection.deleteAirport(airportToDelete);
		System.out.println("Succesfully deleted airport!");
	}
//---------------------------------USER METHODS-------------------------------------------------
	
	public void showUsersMenu() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the users menu. What would you like to do?");
		int response = showCrudMenu("user");
		printDivider();

		switch (response) {
		case 1:
			createUser();
			break;
		case 2:
			readUsers();
			break;
		case 3:
			updateUser();
			break;
		case 4:
			deleteUser();
			break;
		case 5: 
			showMainMenu();
		default:
			System.out.println("Error in choosing user option");
		}
		
		printDivider();
		showMainMenu();
	}
	
	public void createUser() throws ClassNotFoundException, SQLException {
		System.out.println("Create a user..");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is the role of the user?");
		System.out.println("2. Agent");
		System.out.println("3. Traveller");
		int userRole = sc.nextInt();
		
		System.out.println("What is their given name?");
		String givenName = sc.next();

		System.out.println("What is their family name?");
		String familyName = sc.next();
		
		System.out.println("What is their email?");
		String email = sc.next();

		System.out.println("What is their username?");
		String username = sc.next();
		
		System.out.println("What is their password?");
		String password = sc.next();
		
		System.out.println("What is their phone number?");
		String phoneNumber = sc.next();
		
		User newUser = new User();
		
		newUser.setRole_id(userRole);
		newUser.setGiven_name(givenName);
		newUser.setFamily_name(familyName);
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setPhone(phoneNumber);
		
		userConnection.addUser(newUser);
		System.out.println("Successfully added user!");
	}
	
	public void readUsers() throws ClassNotFoundException, SQLException {
		List<User> agents = userConnection.readAgents();		
		List<User> travelers = userConnection.readTravelers();
		
		System.out.println("All Agents:");
		userConnection.printOutUsers(agents);
		System.out.println();
		System.out.println("All Travelers:");
		userConnection.printOutUsers(travelers);
	}
	
	
	public void updateUser() throws ClassNotFoundException, SQLException {
		System.out.println("What user would you like to update?");
		
		List<User> agents = userConnection.readAgents();		
		List<User> travelers = userConnection.readTravelers();
		
		List<User> allUsers = new ArrayList<>(agents.size() + travelers.size());
		allUsers.addAll(agents);
		allUsers.addAll(travelers);
		
		System.out.println("All users:");
		for (int index = 0; index < allUsers.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + allUsers.get(index).getUsername());
		}
		
		
		int index = checkIfValidMenuChoice(allUsers.size()) - 1;
		
		User userToUpdate = allUsers.get(index);
		System.out.println("Here's the user's current info:");
		String userInfo = String.format("id: %s / role_id: %s / given_name: %s / family_name: %s /"
				+ "username: %s / email: %s / password: %s / phone: %s", 
				userToUpdate.getUser_id(), userToUpdate.getRole_id(), userToUpdate.getGiven_name(),
				userToUpdate.getFamily_name(), userToUpdate.getUsername(), userToUpdate.getEmail(), 
				userToUpdate.getPassword(), userToUpdate.getPhone()); 
		System.out.println(userInfo);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter user's new given name");
		String givenName= sc.next();
		
		System.out.println("Enter user's new family name");
		String familyName= sc.next();

		System.out.println("Enter user's new username");
		String username= sc.next();

		System.out.println("Enter user's new password");
		String password= sc.next();

		System.out.println("Enter user's new email");
		String email= sc.next();

		System.out.println("Enter user's new phone number");		
		String phoneNumber = sc.next();
				
		userToUpdate.setGiven_name(givenName);
		userToUpdate.setFamily_name(familyName);
		userToUpdate.setEmail(email);
		userToUpdate.setUsername(username);
		userToUpdate.setPassword(password);
		userToUpdate.setPhone(phoneNumber);
		
		userConnection.updateUser(userToUpdate);
		System.out.println("User successfully updated!");
	}
	
	public void deleteUser() throws ClassNotFoundException, SQLException {
		System.out.println("What user would you like to delete?");
		
		List<User> agents = userConnection.readAgents();		
		List<User> travelers = userConnection.readTravelers();
		
		List<User> allUsers = new ArrayList<>(agents.size() + travelers.size());
		allUsers.addAll(agents);
		allUsers.addAll(travelers);
		
		System.out.println("Agents:");
		for (int index = 0; index < allUsers.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + allUsers.get(index).getUsername());
		}
		
		
		int index = checkIfValidMenuChoice(allUsers.size()) - 1;
		
		User userToDelete = allUsers.get(index);
		userConnection.deleteUser(userToDelete);
		
		System.out.println("User successfully deleted!");
	}
//---------------------------------HELPER METHODS---------------------------------------------------
	public int readRoutesAndReturnChoice() throws ClassNotFoundException, SQLException {
		List<Route> routes = routeConnection.readRoutes();

		for (int index = 0; index < routes.size(); index++) {
			int adjustedIndex = index + 1;
			System.out.println(adjustedIndex + ". " + routes.get(index).getOriginAirport() + " -> "
					+ routes.get(index).getDestinationAirport());
		}

		return checkIfValidMenuChoice(routes.size());
	}

	public void printDivider() {
		System.out.println("-------------------------------");
	}

	public int showCrudMenu(String entity) throws ClassNotFoundException, SQLException {
		System.out.println("1. Create new " + entity);
		System.out.println("2. Read all " + entity + "s");
		System.out.println("3. Update " + entity);
		System.out.println("4. Delete " + entity);
		System.out.println("5. Go Back to Main Menu");
		return checkIfValidMenuChoice(5);
	}

	public int checkIfValidMenuChoice(int max) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		String response = sc.next();
		int responseAsNumber = Integer.parseInt(response);

		while (0 < responseAsNumber && responseAsNumber > max) {
			System.out.println("Please enter a valid choice.");
			response = sc.next();
			responseAsNumber = Integer.parseInt(response);
		}
		return responseAsNumber;
	}
}
