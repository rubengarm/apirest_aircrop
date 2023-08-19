package com.aircrop.backend.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="NDVI")
public class IndiceNDVI implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1930612279892027728L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FINCA")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Finca finca;
	
	@Column(name="FECHA")
	private Date date;
	
	@Column(name="MAX")
	private float max;
	
	@Column(name="MEDIA")
	private float med;
	
	@Column(name="MIN")
	private float min;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	public Finca getFinca() {
		return finca;
	}

	public void setFinca(Finca finca) {
		this.finca = finca;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMed() {
		return med;
	}

	public void setMed(float med) {
		this.med = med;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}
	
	
}
