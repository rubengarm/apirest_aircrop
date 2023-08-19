package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.IndiceLAI;
import com.aircrop.backend.response.IndiceLaiResponseRest;



public interface IIndiceLaiService {
	
	public ResponseEntity<IndiceLaiResponseRest> buscarIndiceLai();
	
	public ResponseEntity<IndiceLaiResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<IndiceLaiResponseRest> crear (IndiceLAI indiceLAI);
	
	public ResponseEntity<IndiceLaiResponseRest> actualizar (IndiceLAI indiceLAI, Long id);
	
	public ResponseEntity<IndiceLaiResponseRest> eliminar (Long id);
	
	public ResponseEntity<IndiceLaiResponseRest> buscarPorFinca(Long  idFinca);
}
