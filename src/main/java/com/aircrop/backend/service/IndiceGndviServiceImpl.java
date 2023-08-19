package com.aircrop.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndiceGNDVI;

import com.aircrop.backend.model.dao.IFincaDao;
import com.aircrop.backend.model.dao.IIndiceGndviDao;
import com.aircrop.backend.response.IndiceGndviResponseRest;



@Service
public class IndiceGndviServiceImpl implements IIndiceGndviService{
	
	private static final Logger log = LoggerFactory.getLogger(IndiceGndviServiceImpl.class);
	
	@Autowired
	private IIndiceGndviDao gndviDao;
	
	@Autowired
	private IFincaDao fincaDao;
	
	@Override
	public ResponseEntity<IndiceGndviResponseRest> buscarIndiceGndvi() {
		log.info("Inicio método buscar indice GNDVI");
		IndiceGndviResponseRest response = new IndiceGndviResponseRest();
		
		try {
			
			
			List<IndiceGNDVI> indiceGNDVI = (List<IndiceGNDVI>) gndviDao.findAll();
			response.getIndiceGndviResponse().setIndiceGndvi(indiceGNDVI);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los indices GNDVI");
			log.error("Error al consultar los indice GNDVI ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<IndiceGndviResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<IndiceGndviResponseRest> buscarPorId(Long id) {
		log.info("Inicio del método buscarPorId");

		IndiceGndviResponseRest response = new IndiceGndviResponseRest();
		List<IndiceGNDVI> list = new ArrayList<>();
		
		try {
			
			Optional<IndiceGNDVI> indiceGndvi = gndviDao.findById(id);
			
			if(indiceGndvi.isPresent()) {//si existe la categoría
				list.add(indiceGndvi.get());
				response.setMetadata("Respuesta ok", "00", "Indice GNDVI encontrado");
				response.getIndiceGndviResponse().setIndiceGndvi(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los indices GNDVI");
				response.setMetadata("Respuesta nok", "-1", "Indice GNDVI no encontrado");
				return new ResponseEntity<IndiceGndviResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los indices GNDVI");
			response.setMetadata("Respuesta nok","-1","Error al consultar el indice GNDVI");
			return new ResponseEntity<IndiceGndviResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceGndviResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceGndviResponseRest> crear(IndiceGNDVI indiceGNDVI) {
		IndiceGndviResponseRest response = new IndiceGndviResponseRest();
		List<IndiceGNDVI> list = new ArrayList<>();
		
		try {
			
			IndiceGNDVI gndviGuardado = gndviDao.save(indiceGNDVI);
			
			if (gndviGuardado != null) {
				list.add(gndviGuardado);
				response.getIndiceGndviResponse().setIndiceGndvi(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el indice GNDVI");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el indice GNDVI");
				return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el indice GNDVI");
			response.setMetadata("Respuesta noK", "-1", "Error al consultar el indice GNDVI");
			return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceGndviResponseRest> actualizar(IndiceGNDVI indiceGNDVI, Long id) {
		log.info("Inicio del metodo actualizar indice GNDVI");
		
		IndiceGndviResponseRest response = new IndiceGndviResponseRest();
		List<IndiceGNDVI> list = new ArrayList<>();
		
		try {
			Optional<IndiceGNDVI> indiceBuscado = gndviDao.findById(id);
			
			if(indiceBuscado.isPresent()) {
				indiceBuscado.get().setDate(indiceGNDVI.getDate());
				indiceBuscado.get().setFinca(indiceGNDVI.getFinca());
				indiceBuscado.get().setMin(indiceGNDVI.getMin());
				indiceBuscado.get().setMed(indiceGNDVI.getMed());
				indiceBuscado.get().setMax(indiceGNDVI.getMax());
				
				IndiceGNDVI indiceActualizar = gndviDao.save(indiceBuscado.get());
				
				if(indiceActualizar != null) {
					list.add(indiceActualizar);
					response.getIndiceGndviResponse().setIndiceGndvi(list);
					response.setMetadata("Respuesta ok", "00", "Indice GNDVI actualizado correctamente");
				}else {
					log.error("Error al actualizar el indice GNDVI");
					response.setMetadata("Respuesta nok", "-1", "Indice GNDVI no actualizado");
					return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				log.error("Error al actualizar el indice GNDVI");
				response.setMetadata("Respuesta nok", "-1", "Indice GNDVI no encontrado");
				return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			log.error("Error al actualizar el indice GNDVI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice GNDVI no actualizado");
			return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceGndviResponseRest> eliminar(Long id) {
		log.info("Inicio del método eliminar");
		
		IndiceGndviResponseRest response = new IndiceGndviResponseRest();
		
		try {
			
			//Eliminamos el registro
			gndviDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Indice GNDVI eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar indice GNDVI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice GNDVI no eliminado");
			return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.OK);	
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceGndviResponseRest> buscarPorFinca(Long idFinca) {
		log.info("Inicio método buscar indice GNDVI por clase");
		
		IndiceGndviResponseRest response = new IndiceGndviResponseRest();
		
		try {
			
			Optional<Finca> finca = fincaDao.findById(idFinca);
			
			if(finca.isPresent()) {
				Finca fincaFound=new Finca();
				fincaFound.setId(finca.get().getId());
				fincaFound.setNombre(finca.get().getNombre());
				fincaFound.setCultivo(finca.get().getCultivo());
				fincaFound.setDireccion(finca.get().getDireccion());
				fincaFound.setMunicipio(finca.get().getMunicipio());
				fincaFound.setHectareas(finca.get().getHectareas());
				fincaFound.setProvincia(finca.get().getProvincia());
				
				List<IndiceGNDVI> indiceGndvi=(List<IndiceGNDVI>) gndviDao.findByFinca(fincaFound);
				response.getIndiceGndviResponse().setIndiceGndvi(indiceGndvi);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			}else {
				log.error("Error al buscar la clase");
				response.setMetadata("Respuesta nok", "-1", "Clase no encontrada");
				return new ResponseEntity<IndiceGndviResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar indice GNDVI",e.getMessage());
			response.setMetadata("Respueta nok" , "-1", "Error al consultar indice GNDVI");
			e.getStackTrace();
			return new ResponseEntity<IndiceGndviResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<IndiceGndviResponseRest>(response,HttpStatus.OK);
	}
}
