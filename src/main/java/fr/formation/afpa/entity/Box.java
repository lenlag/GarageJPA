package fr.formation.afpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Box")
public class Box {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name ="Parking_Area_Number", nullable = false, length = 11)
	private String parkNumber;
	
	@Column(name = "Surface", nullable = false, length = 10)
	private double surface;
	
	
	public Box() {
		
	}

	public Box(Long id, String parkNumber, double surface) {
		super();
		this.id = id;
		this.parkNumber = parkNumber;
		this.surface = surface;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParkNumber() {
		return parkNumber;
	}

	public void setParkNumber(String parkNumber) {
		this.parkNumber = parkNumber;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	

	@Override
	public String toString() {
		return "[parkNumber=" + parkNumber + ", surface=" + surface + "]";
	}
	
	
}
