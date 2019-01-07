package fr.formation.afpa.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // parent class
@Table(name = "Car")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "Model", nullable = false, length = 11)
	private String model;
	
	@Column(name = "Brand", nullable = false, length = 11)
	private String brand;
	
	@Column(name = "Year", nullable = false, length = 4)
	private Date year;
	
	
	public Car() {
		
	}


	public Car(Long id, String model, String brand, Date year) {
		super();
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.year = year;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Date getYear() {
		return year;
	}


	public void setYear(Date year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "Car [model=" + model + ", brand=" + brand + ", year=" + year + "]";
	}
	

}
