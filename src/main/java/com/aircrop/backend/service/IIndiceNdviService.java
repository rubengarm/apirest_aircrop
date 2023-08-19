package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.IndiceNDVI;
import com.aircrop.backend.response.IndiceNdviResponseRest;



public interface IIndiceNdviService {
	
public ResponseEntity<IndiceNdviResponseRest> buscarIndiceNdvi();
	
	public ResponseEntity<IndiceNdviResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<IndiceNdviResponseRest> crear (IndiceNDVI indiceNDVI);
	
	public ResponseEntity<IndiceNdviResponseRest> actualizar (IndiceNDVI indiceNDVI, Long id);
	
	public ResponseEntity<IndiceNdviResponseRest> eliminar (Long id);
	
	public ResponseEntity<IndiceNdviResponseRest> buscarPorFinca(Long idFinca);
}
