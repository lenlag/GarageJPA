package fr.formation.afpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "First_Name", nullable = false, length = 25)
	private String firstName;
	
	@Column(name = "Last_Name", nullable = false, length = 25)
	private String lastName;

	@OneToMany (cascade= {CascadeType.PERSIST}) //one owner - many garages
	private Set<Garage> garages;
	
	@OneToMany (			// one Person - many Vehicles
			targetEntity = fr.formation.afpa.entity.Vehicle.class, 
			cascade = {			
					CascadeType.PERSIST }, 
			mappedBy = "person")				//mandatory here!!! If not mentioned => an intermediate table will be generated!!		
	private Set<Vehicle> vehicles;
	
	
	public Person() {
		
	}


	public Person(Long id, String firstName, String lastName, Set<Vehicle> vehicles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.vehicles = vehicles;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Set<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


		
	public Set<Garage> getGarages() {
		return garages;
	}


	public void setGarages(Set<Garage> garages) {
		this.garages = garages;
	}


	@Override
	public String toString() {
		return " [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
	
}
