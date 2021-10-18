package com.ss.utopia.entity;

public class Flight {
	private int id;
	private int route_id;
	private int airplane_id;
	private String departure_time;
	private int reserved_seats;
	private float seat_price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	public int getAirplane_id() {
		return airplane_id;
	}
	public void setAirplane_id(int airplane_id) {
		this.airplane_id = airplane_id;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public int getReserved_seats() {
		return reserved_seats;
	}
	public void setReserved_seats(int reserved_seats) {
		this.reserved_seats = reserved_seats;
	}
	public float getSeat_price() {
		return seat_price;
	}
	public void setSeat_price(float seat_price) {
		this.seat_price = seat_price;
	}
}
