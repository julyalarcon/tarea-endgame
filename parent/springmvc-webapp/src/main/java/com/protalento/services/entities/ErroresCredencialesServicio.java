package com.protalento.services.entities;

import java.util.HashMap;
import java.util.Map;

import com.protalento.jdbc.enums.CredencialesStatus;

public final class ErroresCredencialesServicio {

	private static Map<CredencialesStatus, ErrorCredencialServicio> errorServicio = new HashMap<CredencialesStatus, ErrorCredencialServicio>();
	
	private static {
		
		errorServicio.put(CredencialesStatus.CREDENCIALES_INCORRECTAS, new ErrorCredencialServicio("A01", "Credenciales incorrectas"));
		
		errorServicio.put(CredencialesStatus.CREDENCIALES_VACIAS, new ErrorCredencialServicio("A02", "Credenciales vacias"));
		
	}
	
	public static ErrorCredencialServicio getErrorCredencialServicio(CredencialesStatus credencialesStatus, String... message) {
		
		ErrorCredencialServicio errorCredencialServicio = errorServicio.get(credencialesStatus);
		String msj = message.length > 0 ? message[0] : "";
		
		ErrorCredencialServicio errorCredencialServicioDevuelve = ErrorCredencialServicio.builder().error(errorCredencialServicio.getError()).mensaje(errorCredencialServicio.getMensaje()).build();
		
		errorCredencialServicioDevuelve.setMensaje(errorCredencialServicioDevuelve.getMensaje() + msj);
		return errorCredencialServicioDevuelve;
			
	}
	
	private ErroresCredencialesServicio() {
		
	}
	
	public static void main(String[] args) {
		System.out.println(ErroresCredencialesServicio.getErrorCredencialServicio(CredencialesStatus.CREDENCIALES_INCORRECTAS));
	}
	
	
}
