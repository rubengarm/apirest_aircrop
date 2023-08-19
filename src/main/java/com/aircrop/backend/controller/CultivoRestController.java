package com.aircrop.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aircrop.backend.model.Cultivo;
import com.aircrop.backend.model.Finca;
import com.aircrop.backend.response.CultivoResponseRest;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.service.ICultivoService;

@RestController
@RequestMapping("/aircrop")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class CultivoRestController {
	
	@Autowired
	ICultivoService service;
	
	@GetMapping("/cultivos")
	public ResponseEntity<CultivoResponseRest> consultaCultivos(){
		ResponseEntity<CultivoResponseRest> response = service.buscarCultivos();
		return response;
	}
	
	@GetMapping("/cultivos/{id}")
	public ResponseEntity<CultivoResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<CultivoResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@GetMapping("/cultivos/detailname/{idCultivo}")
	public ResponseEntity<CultivoResponseRest> buscarPorCliente(@PathVariable Long idCultivo){
		ResponseEntity<CultivoResponseRest> response = service.findByCliente(idCultivo);
		return response;
	}
	
	

	@PostMapping("/cultivos/crear")
	public ResponseEntity<CultivoResponseRest> crear(@RequestBody Cultivo request){
		ResponseEntity<CultivoResponseRest> response = service.crear(request);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/cultivos/{id}")
	public ResponseEntity<CultivoResponseRest> actualizar(@RequestBody Cultivo request, @PathVariable long id){
		ResponseEntity<CultivoResponseRest> response = service.actualizar(request,id);
		return response;
	}
	

	@DeleteMapping("/cultivos/delete/{id}")
	public ResponseEntity<CultivoResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<CultivoResponseRest> response = service.eliminar(id);
		return response;
	}
}
