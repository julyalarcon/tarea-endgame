package com.protalento.utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fechas {
	
public static final String SQL_PATRON_FECHA = "yyyy-MM-dd";
	
	private Fechas() {
		super(); 
	}
	
	//Utility Methods
	public static DateTimeFormatter getDateTimeFormatter(String format) {
		return DateTimeFormatter.ofPattern(format);
	}
	
	public static LocalDateTime getLocalDateTimeNow(String dateTimePattern) {
		DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(dateTimePattern);
		return LocalDateTime.parse(LocalDateTime.now().format(dateTimeFormatter), dateTimeFormatter);
	}
	
	public static String getLocalDateTimeString(LocalDateTime dateTime, String format) {
		return dateTime.format(getDateTimeFormatter(format));
	}
	

}
