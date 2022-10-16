package com.protalento.jdbc.entities.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protalento.entities.Address;
import com.protalento.entities.Company;
import com.protalento.entities.Usuario;

public class PutResponseDTO {
	private int id;
	private String nombre;
	private String usuario;
	private String correo;
	@JsonIgnore
	private String clave;
	private Address direccion;
	private String telefono;
	private String pagina;
	private Company company;
	private String mensaje;
	
	public static PutResponseDTO getPutResponseDTO(Usuario usuario) {
		
		return PutResponseDTO.builder().id(usuario.getId()).nombre(usuario.getNombre()).usuario(usuario.getUsuario()).correo(usuario.getCorreo()).clave(usuario.getClave()).direccion(usuario.getDireccion()).telefono(usuario.getTelefono()).company(usuario.getCompany()).build();
	}

}
