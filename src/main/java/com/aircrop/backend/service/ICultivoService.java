package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Cultivo;
import com.aircrop.backend.response.CultivoResponseRest;


public interface ICultivoService {
	
	
	public ResponseEntity<CultivoResponseRest> buscarCultivos();
	
	public ResponseEntity<CultivoResponseRest> buscarPorId(long id);
    
    public ResponseEntity<CultivoResponseRest> crear (Cultivo cultivo);
    
    public ResponseEntity<CultivoResponseRest> actualizar (Cultivo cultivo, long id);
    
    public ResponseEntity<CultivoResponseRest> eliminar(long id); 
    
    public ResponseEntity<CultivoResponseRest> findByCliente(Long idCliente);
}
