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

import com.aircrop.backend.model.IndiceNDVI;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.response.IndiceNdviResponseRest;
import com.aircrop.backend.service.IIndiceNdviService;

@RestController
@RequestMapping("/aircrop")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class IndiceNdviRestController {
	
	@Autowired
	IIndiceNdviService service;
	
	@GetMapping("/ndvi")
	public ResponseEntity<IndiceNdviResponseRest> consultaIndiceNdvis(){
		ResponseEntity<IndiceNdviResponseRest> response = service.buscarIndiceNdvi();
		return response;
	}
	
	@GetMapping("/ndvi/{id}")
	public ResponseEntity<IndiceNdviResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<IndiceNdviResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@GetMapping("/ndvi/finca/{idFinca}")
	public ResponseEntity<IndiceNdviResponseRest> buscarPorFinca(@PathVariable Long idFinca){
		ResponseEntity<IndiceNdviResponseRest> response = service.buscarPorFinca(idFinca);
		return response;
	}
	

	@PostMapping("/ndvi/create")
	public ResponseEntity<IndiceNdviResponseRest> crear(@RequestBody IndiceNDVI request){
		ResponseEntity<IndiceNdviResponseRest> response = service.crear(request);
		return response;
	}
	
	
	@PutMapping("/ndvi/{id}")
	public ResponseEntity<IndiceNdviResponseRest> actualizar(@RequestBody IndiceNDVI request, @PathVariable long id){
		ResponseEntity<IndiceNdviResponseRest> response = service.actualizar(request,id);
		return response;
	}
	

	@DeleteMapping("/ndvi/delete/{id}")
	public ResponseEntity<IndiceNdviResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<IndiceNdviResponseRest> response = service.eliminar(id);
		return response;
	}
}
