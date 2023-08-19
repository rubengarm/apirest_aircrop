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

import com.aircrop.backend.model.IndicePSRI;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.response.IndicePsriResponseRest;
import com.aircrop.backend.service.IIndicePsriService;

@RestController
@RequestMapping("/aircrop")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class IndicePsriRestController {
	
	@Autowired
	IIndicePsriService service;
	
	@GetMapping("/psri")
	public ResponseEntity<IndicePsriResponseRest> consultaIndicePsris(){
		ResponseEntity<IndicePsriResponseRest> response = service.buscarIndicePsri();
		return response;
	}
	
	@GetMapping("/psri/{id}")
	public ResponseEntity<IndicePsriResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<IndicePsriResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@GetMapping("/psri/finca/{idFinca}")
	public ResponseEntity<IndicePsriResponseRest> buscarPorFinca(@PathVariable Long idFinca){
		ResponseEntity<IndicePsriResponseRest> response = service.buscarPorFinca(idFinca);
		return response;
	}
	
	
	@PostMapping("/psri/create")
	public ResponseEntity<IndicePsriResponseRest> crear(@RequestBody IndicePSRI request){
		ResponseEntity<IndicePsriResponseRest> response = service.crear(request);
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/psri/{id}")
	public ResponseEntity<IndicePsriResponseRest> actualizar(@RequestBody IndicePSRI request, @PathVariable long id){
		ResponseEntity<IndicePsriResponseRest> response = service.actualizar(request,id);
		return response;
	}
	

	@DeleteMapping("/psri/delete/{id}")
	public ResponseEntity<IndicePsriResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<IndicePsriResponseRest> response = service.eliminar(id);
		return response;
	}

}
