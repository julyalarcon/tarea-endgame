package com.protalento.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class Address {
	private String calle;
	private String ciudad;
	private String codigo_postal;
	private Geo geolocalizacion;

}
