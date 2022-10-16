package com.protalento.jdbc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public final class DataSource {
	private DataSource() {
		
	}
	
	public static DriverManagerDataSource getDriverManagerDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	driverManagerDataSource.setDriverClassName("org.maradb.jdbc.Driver");
	driverManagerDataSource.setUrl("jdbc:maradb//localhost:3306/tareaEndgame");
	driverManagerDataSource.setUsername("root");
	driverManagerDataSource.setPassword("");
	
	return driverManagerDataSource;
	
	}
}
