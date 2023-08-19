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
import com.aircrop.backend.model.IndiceClorofila;
import com.aircrop.backend.model.dao.IFincaDao;
import com.aircrop.backend.model.dao.IIndiceClorofilaDao;
import com.aircrop.backend.response.IndiceClorofilaResponseRest;

@Service
public class IndiceClorofilaServiceImpl implements IIndiceClorofilaService{
	
private static final Logger log = LoggerFactory.getLogger(IndiceClorofilaServiceImpl.class);
	
	@Autowired
	private IIndiceClorofilaDao clorofilaDao;
	
	@Autowired
	private IFincaDao claseDao;
	
	@Override
	public ResponseEntity<IndiceClorofilaResponseRest> buscarIndiceClorofila() {
		log.info("Inicio método buscar indice Clorofila");
		IndiceClorofilaResponseRest response = new IndiceClorofilaResponseRest();
		
		try {
			
			
			List<IndiceClorofila> indiceClorofila = (List<IndiceClorofila>) clorofilaDao.findAll();
			response.getIndiceClorofilaResponse().setIndiceClorofila(indiceClorofila);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los indices Clorofila");
			log.error("Error al consultar cultivos ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<IndiceClorofilaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<IndiceClorofilaResponseRest> buscarPorId(Long id) {
		log.info("Inicio del método buscarPorId");

		IndiceClorofilaResponseRest response = new IndiceClorofilaResponseRest();
		List<IndiceClorofila> list = new ArrayList<>();
		
		try {
			
			Optional<IndiceClorofila> indiceClorofila = clorofilaDao.findById(id);
			
			if(indiceClorofila.isPresent()) {//si existe la categoría
				list.add(indiceClorofila.get());
				response.setMetadata("Respuesta ok", "00", "Indice Clorofila encontrado");
				response.getIndiceClorofilaResponse().setIndiceClorofila(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los indice Clorofila");
				response.setMetadata("Respuesta nok", "-1", "Indice Clorofila no encontrado");
				return new ResponseEntity<IndiceClorofilaResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los indices Clorofila");
			response.setMetadata("Respuesta nok","-1","Error al consultar el indice Clorofila");
			return new ResponseEntity<IndiceClorofilaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceClorofilaResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceClorofilaResponseRest> crear(IndiceClorofila indiceClorofila) {
		IndiceClorofilaResponseRest response = new IndiceClorofilaResponseRest();
		List<IndiceClorofila> list = new ArrayList<>();
		
		try {
			
			IndiceClorofila clorofilaGuardado = clorofilaDao.save(indiceClorofila);
			
			if (clorofilaGuardado != null) {
				list.add(clorofilaGuardado);
				response.getIndiceClorofilaResponse().setIndiceClorofila(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el indice Clorofila");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el indice Clorofila");
				return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el indice Clorofila");
			response.setMetadata("Respuesta noK", "-1", "Error al consultar el indice Clorofila");
			return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceClorofilaResponseRest> actualizar(IndiceClorofila indiceClorofila, Long id) {
		log.info("Inicio del metodo actualizar indice Clorofila");
		
		IndiceClorofilaResponseRest response = new IndiceClorofilaResponseRest();
		List<IndiceClorofila> list = new ArrayList<>();
		
		try {
			Optional<IndiceClorofila> indiceBuscado = clorofilaDao.findById(id);
			
			
			if(indiceBuscado.isPresent()) {
				indiceBuscado.get().setDate(indiceClorofila.getDate());
				indiceBuscado.get().setFinca(indiceClorofila.getFinca());
				indiceBuscado.get().setMin(indiceClorofila.getMin());
				indiceBuscado.get().setMed(indiceClorofila.getMed());
				indiceBuscado.get().setMax(indiceClorofila.getMax());
				
				IndiceClorofila indiceActualizar = clorofilaDao.save(indiceBuscado.get());
				
				if(indiceActualizar != null) {
					list.add(indiceActualizar);
					response.getIndiceClorofilaResponse().setIndiceClorofila(list);
					response.setMetadata("Respuesta ok", "00", "Indice Clorofila actualizado correctamente");
				}else {
					log.error("Error al actualizar el indice Clorofila");
					response.setMetadata("Respuesta nok", "-1", "Indice Clorofila no actualizado");
					return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				log.error("Error al actualizar el indice Clorofila");
				response.setMetadata("Respuesta nok", "-1", "Indice Clorofila no encontrado");
				return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			log.error("Error al actualizar el indice Clorofila", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice Clorofila no actualizado");
			return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.OK);
		
		
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceClorofilaResponseRest> eliminar(Long id) {
		log.info("Inicio del método eliminar");
		
		IndiceClorofilaResponseRest response = new IndiceClorofilaResponseRest();
		
		try {
			
			//Eliminamos el registro
			clorofilaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Indice Clorofila eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar indice Clorofila", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice Clorofila no eliminado");
			return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.OK);	
	}

	@Override
	public ResponseEntity<IndiceClorofilaResponseRest> buscarPorFinca(Long idFinca) {
			log.info("Inicio método buscar indice Clorofila por clase");
		
		IndiceClorofilaResponseRest response = new IndiceClorofilaResponseRest();
		
		try {
			
			Optional<Finca> finca = claseDao.findById(idFinca);
			
			if(finca.isPresent()) {
				Finca fincaFound=new Finca();
				fincaFound.setId(finca.get().getId());
				fincaFound.setNombre(finca.get().getNombre());
				fincaFound.setCultivo(finca.get().getCultivo());
				fincaFound.setDireccion(finca.get().getDireccion());
				fincaFound.setMunicipio(finca.get().getMunicipio());
				fincaFound.setHectareas(finca.get().getHectareas());
				fincaFound.setProvincia(finca.get().getProvincia());
				
				List<IndiceClorofila> indiceClorofila=(List<IndiceClorofila>) clorofilaDao.findByFinca(fincaFound);
				response.getIndiceClorofilaResponse().setIndiceClorofila(indiceClorofila);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			}else {
				log.error("Error al buscar la Finca");
				response.setMetadata("Respuesta nok", "-1", "Finca no encontrada");
				return new ResponseEntity<IndiceClorofilaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar indice Clorofila",e.getMessage());
			response.setMetadata("Respueta nok" , "-1", "Error al consultar indice clorofila");
			e.getStackTrace();
			return new ResponseEntity<IndiceClorofilaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<IndiceClorofilaResponseRest>(response,HttpStatus.OK);
	}
}
