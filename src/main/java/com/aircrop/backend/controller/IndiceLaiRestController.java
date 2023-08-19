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

import com.aircrop.backend.model.IndiceLAI;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.response.IndiceLaiResponseRest;
import com.aircrop.backend.service.IIndiceLaiService;

@RestController
@RequestMapping("/aircrop")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class IndiceLaiRestController {
	
	@Autowired
	IIndiceLaiService service;
	
	@GetMapping("/lai")
	public ResponseEntity<IndiceLaiResponseRest> consultaIndiceLais(){
		ResponseEntity<IndiceLaiResponseRest> response = service.buscarIndiceLai();
		return response;
	}
	
	@GetMapping("/lai/{id}")
	public ResponseEntity<IndiceLaiResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<IndiceLaiResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@GetMapping("/lai/finca/{idFinca}")
	public ResponseEntity<IndiceLaiResponseRest> buscarPorFinca(@PathVariable Long idFinca){
		ResponseEntity<IndiceLaiResponseRest> response = service.buscarPorFinca(idFinca);
		return response;
	}
	
	
	@PostMapping("/lai/create")
	public ResponseEntity<IndiceLaiResponseRest> crear(@RequestBody IndiceLAI request){
		ResponseEntity<IndiceLaiResponseRest> response = service.crear(request);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/lai/{id}")
	public ResponseEntity<IndiceLaiResponseRest> actualizar(@RequestBody IndiceLAI request, @PathVariable long id){
		ResponseEntity<IndiceLaiResponseRest> response = service.actualizar(request,id);
		return response;
	}
	

	@DeleteMapping("/lai/delete/{id}")
	public ResponseEntity<IndiceLaiResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<IndiceLaiResponseRest> response = service.eliminar(id);
		return response;
	}

}
