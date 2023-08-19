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
import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndicePSRI;
import com.aircrop.backend.model.dao.IFincaDao;
import com.aircrop.backend.model.dao.IIndicePsriDao;
import com.aircrop.backend.response.IndicePsriResponseRest;


@Service
public class IndicePsriServiceImpl implements IIndicePsriService{
	
	private static final Logger log = LoggerFactory.getLogger(IndicePsriServiceImpl.class);
	
	@Autowired
	private IIndicePsriDao psriDao;
	
	@Autowired
	private IFincaDao fincaDao;
	
	@Override
	public ResponseEntity<IndicePsriResponseRest> buscarIndicePsri() {
		log.info("Inicio método buscar indice PSRI");
		IndicePsriResponseRest response = new IndicePsriResponseRest();
		
		try {
			
			
			List<IndicePSRI> indicePSRI = (List<IndicePSRI>) psriDao.findAll();
			response.getIndicePsriResponse().setIndicePsri(indicePSRI);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los indices PSRI");
			log.error("Error al consultar los indice PSRI ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<IndicePsriResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<IndicePsriResponseRest> buscarPorId(Long id) {
		log.info("Inicio del método buscarPorId");

		IndicePsriResponseRest response = new IndicePsriResponseRest();
		List<IndicePSRI> list = new ArrayList<>();
		
		try {
			
			Optional<IndicePSRI> indicePsri = psriDao.findById(id);
			
			if(indicePsri.isPresent()) {//si existe la categoría
				list.add(indicePsri.get());
				response.setMetadata("Respuesta ok", "00", "Indice PSRI encontrado");
				response.getIndicePsriResponse().setIndicePsri(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los indices PSRI");
				response.setMetadata("Respuesta nok", "-1", "Indice PSRI no encontrado");
				return new ResponseEntity<IndicePsriResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los indices PSRI");
			response.setMetadata("Respuesta nok","-1","Error al consultar el indice PSRI");
			return new ResponseEntity<IndicePsriResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndicePsriResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<IndicePsriResponseRest> crear(IndicePSRI indicePSRI) {
		IndicePsriResponseRest response = new IndicePsriResponseRest();
		List<IndicePSRI> list = new ArrayList<>();
		
		try {
			
			IndicePSRI psriGuardado = psriDao.save(indicePSRI);
			
			if (psriGuardado != null) {
				list.add(psriGuardado);
				response.getIndicePsriResponse().setIndicePsri(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el indice PSRI");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el indice PSRI");
				return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el indice PSRI");
			response.setMetadata("Respuesta noK", "-1", "Error al consultar el indice PSRI");
			return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndicePsriResponseRest> actualizar(IndicePSRI indicePSRI, Long id) {
		log.info("Inicio del metodo actualizar indice PSRI");
		
		IndicePsriResponseRest response = new IndicePsriResponseRest();
		List<IndicePSRI> list = new ArrayList<>();
		
		try {
			Optional<IndicePSRI> indiceBuscado = psriDao.findById(id);
			
			if(indiceBuscado.isPresent()) {
				indiceBuscado.get().setDate(indicePSRI.getDate());
				indiceBuscado.get().setFinca(indicePSRI.getFinca());
				indiceBuscado.get().setMin(indicePSRI.getMin());
				indiceBuscado.get().setMed(indicePSRI.getMed());
				indiceBuscado.get().setMax(indicePSRI.getMax());
				
				IndicePSRI indiceActualizar = psriDao.save(indiceBuscado.get());
				
				if(indiceActualizar != null) {
					list.add(indiceActualizar);
					response.getIndicePsriResponse().setIndicePsri(list);
					response.setMetadata("Respuesta ok", "00", "Indice PSRI actualizado correctamente");
				}else {
					log.error("Error al actualizar el indice PSRI");
					response.setMetadata("Respuesta nok", "-1", "Indice PSRI no actualizado");
					return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				log.error("Error al actualizar el indice PSRI");
				response.setMetadata("Respuesta nok", "-1", "Indice PSRI no encontrado");
				return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			log.error("Error al actualizar el indice PSRI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice PSRI no actualizado");
			return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndicePsriResponseRest> eliminar(Long id) {
		log.info("Inicio del método eliminar");
		
		IndicePsriResponseRest response = new IndicePsriResponseRest();
		
		try {
			
			//Eliminamos el registro
			psriDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Indice PSRI eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar indice PSRI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice PSRI no eliminado");
			return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.OK);	
	}

	@Override
	public ResponseEntity<IndicePsriResponseRest> buscarPorFinca(Long idFinca) {
		log.info("Inicio método buscar indice PSRI por finca");
		
		IndicePsriResponseRest response = new IndicePsriResponseRest();
		
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
				
				List<IndicePSRI> indicePsri=(List<IndicePSRI>) psriDao.findByFinca(fincaFound);
				response.getIndicePsriResponse().setIndicePsri(indicePsri);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			}else {
				log.error("Error al buscar la finca");
				response.setMetadata("Respuesta nok", "-1", "Finca no encontrada");
				return new ResponseEntity<IndicePsriResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar indice PSRI",e.getMessage());
			response.setMetadata("Respueta nok" , "-1", "Error al consultar indice psri");
			e.getStackTrace();
			return new ResponseEntity<IndicePsriResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<IndicePsriResponseRest>(response,HttpStatus.OK);
	}

	
}
