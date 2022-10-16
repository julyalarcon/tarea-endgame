package com.protalento.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.protalento.entities.Address;
import com.protalento.entities.Company;
import com.protalento.entities.Geo;
import com.protalento.entities.Usuario;


public class UsuarioMapper implements RowMapper<Usuario> {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

		int id = rs.getInt("id");
		String nombre = rs.getString("nombre");
		String usuario = rs.getString("usuario");
		String correo = rs.getString("correo");
		String clave = rs.getString("clave");
		String telefono = rs.getString("telefono");
		String pagina = rs.getString("pagina");

		Company company = Company.builder().razon_social(rs.getString("razon_social")).area(rs.getString("area")).build();
		
		Geo geo = Geo.builder().latitud(rs.getString("latitud")).logitud(rs.getString("longitud")).build();
		
		Address address = Address.builder().calle(rs.getString("calle")).ciudad(rs.getString("ciudad")).codigo_postal(rs.getString("codigo_postal")).geo(geo).build();

		Usuario usuari = Usuario.builder().id(id).nombre(nombre).usuario(usuario).correo(correo).clave(clave).direccion(direccion).telefono(telefono).pagina(pagina).company(company).build();		
		
	}

}
