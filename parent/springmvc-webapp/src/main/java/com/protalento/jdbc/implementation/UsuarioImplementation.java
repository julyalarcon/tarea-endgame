package com.protalento.jdbc.implementation;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.protalento.entities.Address;
import com.protalento.entities.Company;
import com.protalento.entities.Geo;
import com.protalento.entities.Usuario;
import com.protalento.jdbc.DAO;
import com.protalento.jdbc.DataSource;
import com.protalento.jdbc.enums.Base64;
import com.protalento.jdbc.mappers.UsuarioMapper;

public class UsuarioImplementation implements DAO<Usuario, Integer> {
	
	private static final Logger logger = LogManager.getLogger();

	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource.getDriverManagerDataSource());

	@Override
	public Usuario findById(Integer id) {
		String query = "SELECT id_usuario, nombre, usuario, correo, clave, pagina, telefono, calle, ciudad, codigo_postal, razon_social, area, latitud, longitud FROM tareaendgame.usuarios where id = ?;";
		
		try {
			return jdbcTemplate.queryForObject(query, new UsuarioMapper(), id);
		} catch (Exception e) {
			logger.error("No existe el id que estas buscando: "+ e);
		}	
		
		return null;
		/*/
		try {
			Usuario usuario = jdbcTemplate.queryForObject(query, new UsuarioMapper(), id);
			logger.debug(usuario);
			return usuario;
		}catch (Exception e){
			logger.debug(e);
			return null;
		}
		*/
	}

	@Override
	public Boolean insert(Usuario usuario) {
		String query = "INSERT INTO tareaendgame.usuarios(id, nombre, usuario, correo, clave, pagina, telefono, calle, ciudad, codigo_postal, razon_social, area, latitud, longitud)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
		PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(query, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR); 
		
		usuario.setClave(getCodedRandom10DigitsValue());
		Company company = usuario.getCompany();
		Address address = usuario.getAddress();
		Geo geo = direccion.getGeo();
		Object[] parametros = new Object[] {
				usuario.getNombre(), 
				usuario.getUsuario(),
				usuario.getCorreo(),
				usuario.getClave(),
				usuario.getPagina(),
				usuario.getTelefono(),
				address.getCalle(),
				address.getCiudad(),
				address.getCodigo_postal(),
				company.getRazon_social(),
				company.getArea(),
				geo.getLatitud(),
				geo.getLongitud()
		};
		
		preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
		preparedStatementCreatorFactory.setGeneratedKeysColumnNames("id");
		
		PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(parametros);
		
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		
		byte insertarFilas = (byte) jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
		
		boolean inserto = insertarFilas == 1;
		
		Map<String, Object> llaveValor = generatedKeyHolder.getKeys();
		
		usuario.setId((Integer) generatedKeyHolder.getKey().get("id")); 

		return inserto;	
		
	}
		

	@Override
	public Boolean update(Usuario usuario) {
		String query = "UPDATE tareaendgame.usuarios SET nombre=?, usuario=?, correo=?, clave=?, pagina=?, telefono=?, calle=?, ciudad=?, codigo_postal=?, razon_social=?, area=?, latitud=?, longitud=? WHERE id_usuario= ?;";
		
		Company company = usuario.getCompany();
		Address address = usuario.getAddress();
		Geo geo = address.getGeo();
		
		int modificarFilas = jdbcTemplate.update(query,usuario.getNombre(), usuario.getUsuario(), usuario.getCorreo(), usuario.getClave(), usuario.getPagina(), usuario.getTelefono(), address.getCalle(), address.getCiudad(), address.getCodigo_postal(), company.getRazon_social(), company.getArea(), geo.getLatitud(), geo.getLongitud(), usuario.getId()); 
		
		boolean modificar = modificarFilas == 1;
		
		return modificar;
	}

	@Override
	public Boolean save(Usuario usuario) {
		boolean guardar;
		
		if (findById(usuario.getId()) == null) {
			guardar = insert(usuario);
		}else {
			guardar = update(usuario);
		}
		
		return guardar;
		/*
		String query = "";
		if (findById(usuario.getId()) == null) {
			query = "INSERT INTO tareaendgame.usuarios(id, nombre, usuario, correo, clave, pagina, telefono, calle, ciudad, codigo_postal, razon_social, area, latitud, longitud)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			return jdbcTemplate.update(query, usuario.getId(), usuario.getNombre(), usuario.getUsuario(), usuario.getCorreo(), usuario.getClave(), usuario.getPagina(), usuario.getTelefono(), usuario.getCalle(), usuario.getCiudad(), usuario.getCodigo_postal(), usuario.getRazon_social(), usuario.getArea(), usuario.getLatitud(), usuario.getLongitud())==1;
		}else {
			query = "UPDATE tareaendgame.usuarios SET nombre=?, usuario=?, correo=?, clave=?, pagina=?, telefono=?, calle=?, ciudad=?, codigo_postal=?, razon_social=?, area=?, latitud=?, longitud=? WHERE id_usuario= ?;";
			return jdbcTemplate.update(query, usuario.getNombre(), usuario.getUsuario(), usuario.getCorreo(), usuario.getClave(), usuario.getPagina(), usuario.getTelefono(), usuario.getCalle(), usuario.getCiudad(), usuario.getCodigo_postal(), usuario.getRazon_social(), usuario.getArea(), usuario.getLatitud(), usuario.getLongitud(), usuario.getId())==1;
		}
		*/
				
	}

	@Override
	public Boolean delete(Usuario usuario) {
		String query = "Delete from usuarios where id = ?";
		//return jdbcTemplate.update(query, usuario.getId())==1;
		
		int eliminarFilas = jdbcTemplate.update(query, usuario.getId());
		
		boolean eliminar = eliminarFilas == 1;
		
		return eliminar;
		
	}

	@Override
	public List<Usuario> findAll() {
		String query = "SELECT id_usuario, nombre, usuario, correo, clave, pagina, telefono, calle, ciudad, codigo_postal, razon_social, area, latitud, longitud FROM tareaendgame.usuarios ;";
		List<Usuario> usuariosLista = jdbcTemplate.query(query, new UsuarioMapper());
		logger.debug(usuariosLista);
		return usuariosLista;
	}
	
	private String getCodedRandom10DigitsValue() {
		double randomValue = Math.random();
		Long random10DigitsValue = Math.round(10000000000L * randomValue);

		String coded = Base64.operateBase64Schema(String.valueOf(random10DigitsValue),
				Base64Schema.ENCODE);

		return coded.replace("==", "");
	}

}
