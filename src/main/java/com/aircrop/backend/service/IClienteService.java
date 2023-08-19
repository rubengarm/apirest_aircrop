package com.aircrop.backend.service;



import org.springframework.http.ResponseEntity;

import com.aircrop.backend.model.Cliente;
import com.aircrop.backend.response.ClienteResponseRest;

public interface IClienteService {
	
	public ResponseEntity<ClienteResponseRest> buscarClientes();
	
	public ResponseEntity<ClienteResponseRest> buscarPorId(Long id);
	

	
	public ResponseEntity<ClienteResponseRest> buscarPorNombreUsuario(String nombreUsuario);
    
    public ResponseEntity<ClienteResponseRest> crear (Cliente cliente);
    
    public ResponseEntity<ClienteResponseRest> actualizar (Cliente cliente, Long id);
    
    public ResponseEntity<ClienteResponseRest> eliminar(Long id); 
}