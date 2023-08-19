package com.aircrop.backend.service;

import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Cliente;
import com.aircrop.backend.model.Finca;
import com.aircrop.backend.response.FincaResponseRest;


public interface IFincaService {

	public ResponseEntity<FincaResponseRest> buscarFincas();
	
	public ResponseEntity<FincaResponseRest> buscarPorId(long id);
    
    public ResponseEntity<FincaResponseRest> crear (Finca finca);
    
    public ResponseEntity<FincaResponseRest> actualizar (Finca finca, long id);
    
    public ResponseEntity<FincaResponseRest> eliminar(long id); 
    
    public ResponseEntity<FincaResponseRest> buscarPorCultivo(Long idCultivo);
    
    public ResponseEntity<FincaResponseRest> buscarPorCliente(Long idCliente);
}
