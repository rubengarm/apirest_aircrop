package com.aircrop.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="FINCA")
public class Finca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1947016231919273875L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="MUNICIPIO")
	private String municipio;
	
	@Column(name="PROVINCIA", nullable= false)
	private String provincia;
	
	@Column(name="Hectareas")
	private int hectareas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CULTIVO")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cultivo cultivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getHectareas() {
		return hectareas;
	}

	public void setHectareas(int hectareas) {
		this.hectareas = hectareas;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}
	
	
	
	
}
