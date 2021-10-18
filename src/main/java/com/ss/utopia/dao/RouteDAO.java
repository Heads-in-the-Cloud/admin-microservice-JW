package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Route;

public class RouteDAO extends BaseDAO<Route> {

	public RouteDAO(Connection conn) {
		super(conn);
	}

	public void addRoute(Route route) throws ClassNotFoundException, SQLException {
		save("INSERT INTO route (origin_id, destination_id) VALUES (?, ?)", new Object[] {
				route.getOriginAirport(), route.getDestinationAirport()});
	}

	public void updateRoute(Route route) throws ClassNotFoundException, SQLException {
		save("UPDATE route set origin_id = ? AND destination_id = ? where id = ?",
				new Object[] { route.getOriginAirport(),
						route.getDestinationAirport(), route.getRouteId() });
	}

	public void deleteRoute(Route route) throws ClassNotFoundException, SQLException {
		save("delete from route where id = ?", new Object[] { route.getRouteId() });
	}

	public List<Route> readRoutes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route", null);
	}
	
	public List<Route> readRoutesByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route WHERE origin_id = ? OR destination_id = ?", 
						new Object[] { airportCode, airportCode });
	}
	

	@Override
	protected List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Route> routes = new ArrayList<>();
		while (rs.next()) {
			Route route = new Route();
			route.setRouteId(rs.getInt("id"));
			route.setOriginAirport(rs.getString("origin_id"));
			route.setDestinationAirport(rs.getString("destination_id"));
			routes.add(route);				
		}
		return routes;
	}
}
