package com.ss.utopia.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Airport {
	@Id
	private String iataId;
	private String city;
	
	public String getAirportCode() {
		return iataId;
	}
	public void setAirportCode(String airportCode) {
		this.iataId = airportCode;
	}
	public String getCityName() {
		return city;
	}
	public void setCityName(String cityName) {
		this.city = cityName;
	}
}
