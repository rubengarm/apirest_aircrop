package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.IndicePSRI;
import com.aircrop.backend.response.IndicePsriResponseRest;

public interface IIndicePsriService {
public ResponseEntity<IndicePsriResponseRest> buscarIndicePsri();
	
	public ResponseEntity<IndicePsriResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<IndicePsriResponseRest> crear (IndicePSRI indicePSRI);
	
	public ResponseEntity<IndicePsriResponseRest> actualizar (IndicePSRI indicePSRI, Long id);
	
	public ResponseEntity<IndicePsriResponseRest> eliminar (Long id);
	
	public ResponseEntity<IndicePsriResponseRest> buscarPorFinca(Long idFinca);
}
