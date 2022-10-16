package com.protalento.jdbc.entities.DTOS;

import com.protalento.entities.Address;
import com.protalento.entities.Company;
import com.protalento.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO {
	private String nombre;
	private String usuario;
	private String correo;
	private Address address;
	private String telefono;
	private String pagina;
	private Company company;
	
	public static UsuarioDTO getUsuarioDTO(Usuario usuario) {
		
		return UsuarioDTO.builder().nombre(usuario.getNombre()).usuario(usuario.getUsuario()).correo(usuario.getNombre()).address(usuario.getAddress()).telefono(usuario.getTelefono()).pagina(usuario.getPagina()).company(usuario.getCompany()).build();
		

	}
	
	public static Usuario getUsuario(UsuarioDTO usuarioDTO) {
		
		return Usuario.builder().nombre(usuarioDTO.getNombre()).usuario(usuarioDTO.getUsuario()).correo(usuarioDTO.getNombre()).address(usuarioDTO.getAddress()).telefono(usuarioDTO.getTelefono()).pagina(usuarioDTO.getPagina()).company(usuarioDTO.getCompany()).build();

	}

}
