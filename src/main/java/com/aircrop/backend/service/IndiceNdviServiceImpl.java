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
import com.aircrop.backend.model.IndiceNDVI;

import com.aircrop.backend.model.dao.IFincaDao;
import com.aircrop.backend.model.dao.IIndiceNdviDao;
import com.aircrop.backend.response.IndiceNdviResponseRest;


@Service
public class IndiceNdviServiceImpl implements IIndiceNdviService{
	
	private static final Logger log = LoggerFactory.getLogger(IndiceNdviServiceImpl.class);
	
	@Autowired
	private IIndiceNdviDao ndviDao;
	
	@Autowired
	private IFincaDao fincaDao;
	
	@Override
	public ResponseEntity<IndiceNdviResponseRest> buscarIndiceNdvi() {
		log.info("Inicio método buscar indice NDVI");
		IndiceNdviResponseRest response = new IndiceNdviResponseRest();
		
		try {
			
			
			List<IndiceNDVI> indiceNDVI = (List<IndiceNDVI>) ndviDao.findAll();
			response.getIndiceNdviResponse().setIndiceNdvi(indiceNDVI);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los indices NDVI");
			log.error("Error al consultar cultivos ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<IndiceNdviResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<IndiceNdviResponseRest> buscarPorId(Long id) {
		log.info("Inicio del método buscarPorId");

		IndiceNdviResponseRest response = new IndiceNdviResponseRest();
		List<IndiceNDVI> list = new ArrayList<>();
		
		try {
			
			Optional<IndiceNDVI> indiceNdvi = ndviDao.findById(id);
			
			if(indiceNdvi.isPresent()) {//si existe la categoría
				list.add(indiceNdvi.get());
				response.setMetadata("Respuesta ok", "00", "Indice NDVI encontrado");
				response.getIndiceNdviResponse().setIndiceNdvi(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los indice NDVI");
				response.setMetadata("Respuesta nok", "-1", "Indice NDVI no encontrado");
				return new ResponseEntity<IndiceNdviResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los indices NDVI");
			response.setMetadata("Respuesta nok","-1","Error al consultar el indice NDVI");
			return new ResponseEntity<IndiceNdviResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceNdviResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceNdviResponseRest> crear(IndiceNDVI indiceNDVI) {
		IndiceNdviResponseRest response = new IndiceNdviResponseRest();
		List<IndiceNDVI> list = new ArrayList<>();
		
		try {
			
			IndiceNDVI ndviGuardado = ndviDao.save(indiceNDVI);
			
			if (ndviGuardado != null) {
				list.add(ndviGuardado);
				response.getIndiceNdviResponse().setIndiceNdvi(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el indice NDVI");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el indice NDVI");
				return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el indice NDVI");
			response.setMetadata("Respuesta noK", "-1", "Error al consultar el indice NDVI");
			return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceNdviResponseRest> actualizar(IndiceNDVI indiceNDVI, Long id) {
		log.info("Inicio del metodo actualizar indice NDVI");
		
		IndiceNdviResponseRest response = new IndiceNdviResponseRest();
		List<IndiceNDVI> list = new ArrayList<>();
		
		try {
			Optional<IndiceNDVI> indiceBuscado = ndviDao.findById(id);
			
			if(indiceBuscado.isPresent()) {
				indiceBuscado.get().setDate(indiceNDVI.getDate());
				indiceBuscado.get().setFinca(indiceNDVI.getFinca());
				indiceBuscado.get().setMin(indiceNDVI.getMin());
				indiceBuscado.get().setMed(indiceNDVI.getMed());
				indiceBuscado.get().setMax(indiceNDVI.getMax());
				
				IndiceNDVI indiceActualizar = ndviDao.save(indiceBuscado.get());
				
				if(indiceActualizar != null) {
					list.add(indiceActualizar);
					response.getIndiceNdviResponse().setIndiceNdvi(list);
					response.setMetadata("Respuesta ok", "00", "Indice NDVI actualizado correctamente");
				}else {
					log.error("Error al actualizar el indice NDVI");
					response.setMetadata("Respuesta nok", "-1", "Indice NDVI no actualizado");
					return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				log.error("Error al actualizar el indice NDVI");
				response.setMetadata("Respuesta nok", "-1", "Indice NDVI no encontrado");
				return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			log.error("Error al actualizar el indice NDVI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice NDVI no actualizado");
			return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.OK);
		
		
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceNdviResponseRest> eliminar(Long id) {
		log.info("Inicio del método eliminar");
		
		IndiceNdviResponseRest response = new IndiceNdviResponseRest();
		
		try {
			
			//Eliminamos el registro
			ndviDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Indice NDVI eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar indice NDVI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice NDVI no eliminado");
			return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.OK);	
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceNdviResponseRest> buscarPorFinca(Long idFinca) {
		log.info("Inicio método buscar indice NDVI por finca");
		
		IndiceNdviResponseRest response = new IndiceNdviResponseRest();
		
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
				
				List<IndiceNDVI> indiceNdvi=(List<IndiceNDVI>) ndviDao.findByFinca(fincaFound);
				response.getIndiceNdviResponse().setIndiceNdvi(indiceNdvi);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			}else {
				log.error("Error al buscar la finca");
				response.setMetadata("Respuesta nok", "-1", "Finca no encontrada");
				return new ResponseEntity<IndiceNdviResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar indice NDVI",e.getMessage());
			response.setMetadata("Respueta nok" , "-1", "Error al consultar indice NDVI");
			e.getStackTrace();
			return new ResponseEntity<IndiceNdviResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<IndiceNdviResponseRest>(response,HttpStatus.OK);
	}

}
