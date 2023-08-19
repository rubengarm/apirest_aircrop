package com.aircrop.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aircrop.backend.model.enums.TipoCultivo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CULTIVO")
public class Cultivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoCultivo tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cliente cliente;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoCultivo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCultivo tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	
}
