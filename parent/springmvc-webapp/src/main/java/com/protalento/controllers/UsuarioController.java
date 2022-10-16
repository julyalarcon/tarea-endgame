package com.protalento.controllers;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.protalento.entities.Usuario;
import com.protalento.jdbc.entities.DTOS.DeleteDTO;
import com.protalento.jdbc.entities.DTOS.PostDTO;
import com.protalento.jdbc.entities.DTOS.PutRequestDTO;
import com.protalento.jdbc.entities.DTOS.PutResponseDTO;
import com.protalento.jdbc.entities.DTOS.UsuarioDTO;
import com.protalento.jdbc.enums.ServicioStatus;
import com.protalento.jdbc.implementation.UsuarioImplementation;
import com.protalento.services.entities.ErroresServicio;
import com.protalento.tareas.DTO.TareaDeleteDTO;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
private static Logger logger = LogManager.getLogger(); 
	
	@Autowired
	private UsuarioImplementation usuarioImplementation;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> getUsuarioById(@PathVariable int id){
		Usuario usuario = usuarioImplementation.findById(id);
		
		if (Objects.isNull(usuario)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErroresServicio.getServicioMensaje(ServicioStatus.ELEMENTO_NO_ENCONTRADO, "Id =" + id + " el usuario no existe"));

		}
		
		logger.info("UsuarioController Controller. method getUsuarioById ... " + id + ". Usuario: " + usuario);
		
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
		
	}
	
	@PostMapping(path = "/agregar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postUsuario(@RequestBody UsuarioDTO usuarioDTO){
		
		Usuario usuario = UsuarioDTO.getUsuario(usuarioDTO);
		
		boolean estaGuardado = usuarioImplementation.save(usuario);
		
		PostDTO postDTO;
		
		if (estaGuardado) {
			logger.info("Usuario recibidp como un JSON por medio de HTTP" + "Usuario = " + usuario);
			
			postDTO = PostDTO.builder().id(usuario.getId()).clave(usuario.getClave()).mensaje("Usuario creado correctamente").build();
			
			logger.info("elemento insertado " + estaGuardado + " Usuario = " + postDTO);
			
			return ResponseEntity.status(HttpStatus.OK).body(postDTO);
			
		}
		
		logger.info("Usuario recibido como un  JSON por medio de  HTTP post" + "Usuario = " + usuario);
		
		postDTO = PostDTO.builder().mensaje("El usuario NO  se ha podido crear correctament").build();
		
		logger.info("elemento insertado " + estaGuardado + ". Usuario = " + postDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(postDTO);
	}

	@PutMapping(path = "/modificar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> putUsuario(@RequestBody PutRequestDTO putRequestDTO) {

		Usuario usuario = PutRequestDTO.getUsuarioFromDTO(putRequestDTO);

		boolean estaGuardado = usuarioImplementation.save(usuario);
		
		if (!estaGuardado) {
			return ResponseEntity.status(HttpStatus.OK).body(ErroresServicio.getServicioMensaje(ServicioStatus.USUARIO_NO_ACTUALIZADO, " No se logro actualizar el elemento"));
		}

		PutResponseDTO putResponseDTO = PutResponseDTO.getPutResponseDTO(usuario);
		putResponseDTO.setMensaje("Se han actualizado correctamente los datos");

		logger.info("PutRequestDTO recibido como un JSON por medio de HTTP post" + "putRequestDTO = " + PutRequestDTO);
		
		logger.info("Usuario actualizado " + estaGuardado + ". PutRequestDTO= " + putRequestDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(putResponseDTO);
	}
	
	@DeleteMapping(path = "/eliminar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteUsuario(@RequestBody Usuario usuario) {
		
		
		if (usuarioImplementation.delete(usuario)) {
			return ResponseEntity.status(HttpStatus.OK).body(DeleteDTO.builder().message("Usuario " + usuario.getId() + " usuario eliminado correctamente").build());
		}
		return ResponseEntity.status(HttpStatus.OK).body(TareaDeleteDTO.builder().message("Usuario " + usuario.getId() + " NO se pudo eliminar.").build());
	}
	
	@GetMapping(path = "/listar")
	public ResponseEntity<Object> Listar() {
		
		logger.info("Listando elementos..."); 
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioImplementation.findAll());
	}
	
	@GetMapping(path = "/ping")
	public ResponseEntity<Object> ping() {
		
		logger.info("pong...");
		
		return ResponseEntity.ok("pong");
	}
			
}