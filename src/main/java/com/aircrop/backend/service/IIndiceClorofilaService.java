package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.IndiceClorofila;
import com.aircrop.backend.response.IndiceClorofilaResponseRest;

public interface IIndiceClorofilaService {
	
public ResponseEntity<IndiceClorofilaResponseRest> buscarIndiceClorofila();
	
	public ResponseEntity<IndiceClorofilaResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<IndiceClorofilaResponseRest> crear (IndiceClorofila indiceClorofila);
	
	public ResponseEntity<IndiceClorofilaResponseRest> actualizar (IndiceClorofila indiceClorofila, Long id);
	
	public ResponseEntity<IndiceClorofilaResponseRest> eliminar (Long id);
	
	public ResponseEntity<IndiceClorofilaResponseRest> buscarPorFinca(Long idFinca);
}
