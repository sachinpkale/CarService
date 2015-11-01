package com.jrd.test.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String make;
	
	@Column(nullable=false)
	private String model;
	
	@Column(nullable=false)
	private Integer year;
	
	@Column(nullable=false)
	private Integer engine;
	
	@OneToMany(mappedBy="car", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CarAttribute> attributes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getEngine() {
		return engine;
	}
	public void setEngine(Integer engine) {
		this.engine = engine;
	}
	public List<CarAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<CarAttribute> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model
				+ ", year=" + year + ", engine=" + engine + ", attributes="
				+ attributes + "]";
	}
	
}
