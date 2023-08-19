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

import com.aircrop.backend.model.IndiceClorofila;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.response.IndiceClorofilaResponseRest;
import com.aircrop.backend.service.IIndiceClorofilaService;

@RestController
@RequestMapping("/aircrop")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class IndiceClorofilaRestController {

	@Autowired
	IIndiceClorofilaService service;
	
	@GetMapping("/clorofila")
	public ResponseEntity<IndiceClorofilaResponseRest> consultaIndiceClorofilas(){
		ResponseEntity<IndiceClorofilaResponseRest> response = service.buscarIndiceClorofila();
		return response;
	}
	
	@GetMapping("/clorofila/{id}")
	public ResponseEntity<IndiceClorofilaResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<IndiceClorofilaResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@GetMapping("/clorofila/finca/{idFinca}")
	public ResponseEntity<IndiceClorofilaResponseRest> buscarPorFinca(@PathVariable Long idFinca){
		ResponseEntity<IndiceClorofilaResponseRest> response = service.buscarPorFinca(idFinca);
		return response;
	}
	

	@PostMapping("/clorofila/create")
	public ResponseEntity<IndiceClorofilaResponseRest> crear(@RequestBody IndiceClorofila request){
		ResponseEntity<IndiceClorofilaResponseRest> response = service.crear(request);
		return response;
	}
	

	@PutMapping("/clorofila/{id}")
	public ResponseEntity<IndiceClorofilaResponseRest> actualizar(@RequestBody IndiceClorofila request, @PathVariable long id){
		ResponseEntity<IndiceClorofilaResponseRest> response = service.actualizar(request,id);
		return response;
	}
	
	
	@DeleteMapping("/clorofila/delete/{id}")
	public ResponseEntity<IndiceClorofilaResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<IndiceClorofilaResponseRest> response = service.eliminar(id);
		return response;
	}
}
