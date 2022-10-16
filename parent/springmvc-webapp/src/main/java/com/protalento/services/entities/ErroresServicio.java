package com.protalento.services.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.protalento.jdbc.enums.ServicioStatus;

@Service
public class ErroresServicio {

	private static Map<ServicioStatus, ServicioMensaje> errorServicio = new HashMap<>();
	
	static  {
		
		errorServicio.put(ServicioStatus.ELEMENTO_NO_ENCONTRADO, new ServicioMensaje("A03", ServicioStatus.ELEMENTO_NO_ENCONTRADO.toString() + ": "));
		
		errorServicio.put(ServicioStatus.USUARIO_NO_EXISTE, new ServicioMensaje("A04", ServicioStatus.USUARIO_NO_EXISTE.toString() + ": "));
		
		errorServicio.put(ServicioStatus.USUARIO_NO_EXISTE, new ServicioMensaje("A05", ServicioStatus.USUARIO_NO_ACTUALIZADO.toString() + ": "));
	
	}
	
	public static ServicioMensaje getServicioMensaje(ServicioStatus servicioStatus, String... mensaje) {
		
		ServicioMensaje servicioMensaje = errorServicio.get(servicioStatus);
		String msj = mensaje.length > 0 ? mensaje[0] : "";
		
		ServicioMensaje servicioMensajeDevuelve = ServicioMensaje.builder().code(servicioMensaje.getCode()).mensaje(servicioMensaje.getMensaje()).build();
		
		servicioMensajeDevuelve.setMensaje(servicioMensajeDevuelve.getMensaje() + msj);
		
		return servicioMensajeDevuelve;
		
	}
	
	private ErroresServicio(){
		
	}
	
	public static void main(String[] args) {
		System.out.println(ErroresServicio.getServicioError(ServicioStatus.ELEMENTO_NO_ENCONTRADO));
	}
}
