package com.ss.utopia.entity;

import java.util.List;

//I am making the assumption that the add/update/read/delete tickets means to modify the booking table,
//1 bookin should be 1 ticket--confirmation code should equate to 1 ticket
public class Booking {
	private int id;
	private int is_active;
	private String confirmation_code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public String getConfirmation_code() {
		return confirmation_code;
	}
	public void setConfirmation_code(String confirmation_code) {
		this.confirmation_code = confirmation_code;
	}
}
