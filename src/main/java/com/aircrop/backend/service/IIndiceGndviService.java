package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.IndiceGNDVI;
import com.aircrop.backend.response.IndiceGndviResponseRest;


public interface IIndiceGndviService {
	
	public ResponseEntity<IndiceGndviResponseRest> buscarIndiceGndvi();
	
	public ResponseEntity<IndiceGndviResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<IndiceGndviResponseRest> crear (IndiceGNDVI indiceGNDVI);
	
	public ResponseEntity<IndiceGndviResponseRest> actualizar (IndiceGNDVI indiceGNDVI, Long id);
	
	public ResponseEntity<IndiceGndviResponseRest> eliminar (Long id);
	
	public ResponseEntity<IndiceGndviResponseRest> buscarPorFinca(Long id);

}
