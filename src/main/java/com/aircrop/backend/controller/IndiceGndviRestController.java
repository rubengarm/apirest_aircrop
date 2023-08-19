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

import com.aircrop.backend.model.IndiceGNDVI;
import com.aircrop.backend.response.FincaResponseRest;
import com.aircrop.backend.response.IndiceGndviResponseRest;
import com.aircrop.backend.service.IIndiceGndviService;

@RestController
@RequestMapping("/aircrop")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class IndiceGndviRestController {
	
	@Autowired
	IIndiceGndviService service;
	
	@GetMapping("/gndvi")
	public ResponseEntity<IndiceGndviResponseRest> consultaIndiceGndvis(){
		ResponseEntity<IndiceGndviResponseRest> response = service.buscarIndiceGndvi();
		return response;
	}
	
	@GetMapping("/gndvi/{id}")
	public ResponseEntity<IndiceGndviResponseRest> buscarPorId(@PathVariable long id){
		ResponseEntity<IndiceGndviResponseRest> response = service.buscarPorId(id);
		return response;
		}
	
	@GetMapping("/gndvi/finca/{idFinca}")
	public ResponseEntity<IndiceGndviResponseRest> buscarPorFinca(@PathVariable Long idFinca){
		ResponseEntity<IndiceGndviResponseRest> response = service.buscarPorFinca(idFinca);
		return response;
	}
	

	@PostMapping("/gndvi/create")
	public ResponseEntity<IndiceGndviResponseRest> crear(@RequestBody IndiceGNDVI request){
		ResponseEntity<IndiceGndviResponseRest> response = service.crear(request);
		return response;
	}
	
	
	@PutMapping("/gndvi/{id}")
	public ResponseEntity<IndiceGndviResponseRest> actualizar(@RequestBody IndiceGNDVI request, @PathVariable long id){
		ResponseEntity<IndiceGndviResponseRest> response = service.actualizar(request,id);
		return response;
	}
	

	@DeleteMapping("/gndvi/delete/{id}")
	public ResponseEntity<IndiceGndviResponseRest> eliminar(@PathVariable long id){
		ResponseEntity<IndiceGndviResponseRest> response = service.eliminar(id);
		return response;
	}

}
