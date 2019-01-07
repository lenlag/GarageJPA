package fr.formation.afpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Rental")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name = "id_box")
	private Box box;
	
	@OneToOne
	@JoinColumn(name = "id_hevicle")
	private Vehicle vehicle;
	
	@Column(name = "Fare", nullable = false)
	private double fare;
	
	@Column(name = "Start_Date", nullable = false)
	private Date startDate;
	
	@Column(name = "End_Date", nullable = false)
	private Date endDate;
	
	
	
	public Rental() {
		
	}



	public Rental(Long id, Box box, Vehicle vehicle, double fare, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.box = box;
		this.vehicle = vehicle;
		this.fare = fare;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Box getBox() {
		return box;
	}



	public void setBox(Box box) {
		this.box = box;
	}



	public Vehicle getVehicle() {
		return vehicle;
	}



	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}



	public double getFare() {
		return fare;
	}



	public void setFare(double fare) {
		this.fare = fare;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	@Override
	public String toString() {
		return "Rental [box=" + box + ", vehicle=" + vehicle + ", fare=" + fare + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	
	
	
}
