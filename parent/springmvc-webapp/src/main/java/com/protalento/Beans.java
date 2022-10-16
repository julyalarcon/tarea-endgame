package com.protalento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.protalento.jdbc.implementation.UsuarioImplementation;

@Configuration
public class Beans {
	
	@Bean
	public UsuarioImplementation usuarioImplementation() {
		return new UsuarioImplementation();
	}

}
