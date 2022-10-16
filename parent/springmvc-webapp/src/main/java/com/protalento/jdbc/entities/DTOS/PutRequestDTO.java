package com.protalento.jdbc.entities.DTOS;

import com.protalento.entities.Address;
import com.protalento.entities.Company;
import com.protalento.entities.Usuario;

public class PutRequestDTO {
	private int id;
	private String nombre;
	private String usuario;
	private String correo;
	private String clave;
	private Address direccion;
	private String telefono;
	private String pagina;
	private Company company;
	private String mensaje;
	
	public static Usuario getUsuarioFromDTO(PutRequestDTO usuarioPutRequestDTO) {
		
		return Usuario.builder().id(usuarioPutRequestDTO.getId()).nombre(usuarioPutRequestDTO.getINombre()).usuario(usuarioPutRequestDTO.getUsuario()).correo(usuarioPutRequestDTO.getCorreo()).clave(usuarioPutRequestDTO.getClave()).direccion(usuarioPutRequestDTO.getDireccion()).telefono(usuarioPutRequestDTO.getTelefono()).pagina(usuarioPutRequestDTO.getPagina()).company(usuarioPutRequestDTO.getCompany()).build();
		
		
	}

}
