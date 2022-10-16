package com.protalento.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Usuario {
	private int id;
	private String nombre;
	private String usuario;
	private String correo;
	private String clave;
	private Address direccion;
	private String telefono;
	private String pagina;
	private Company company;

}
