package fr.formation.afpa.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Garage")
public class Garage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "Name", nullable = false, length = 25)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "id_address")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private Person garageOwner;
	
												
	@OneToMany(cascade= {CascadeType.PERSIST}) // one Garage - to many Boxes
	private Set<Box> boxes;					 //remove EAGER & put @Transactional into MainTest

	
	@Transient		//Hibernate ignores this list when loading the object
	private List<Rental> rentals;

	
	public Garage() {
		
	}


	public Garage(Long id, String name, Address address, Person garageOwner, Set<Box> boxes) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.garageOwner = garageOwner;
		this.boxes = boxes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Person getGarageOwner() {
		return garageOwner;
	}


	public void setGarageOwner(Person garageOwner) {
		this.garageOwner = garageOwner;
	}


	public Set<Box> getBoxes() {
		return boxes;
	}


	public void setBoxes(Set<Box> boxes) {
		this.boxes = boxes;
	}


	public void loadRentals(List<Rental> rentals) {

		this.rentals = rentals;
	}


	@Override
	public String toString() {
		String garageInfo = "\n Garage : " + name;
		for (Box box : boxes) {
			Rental myRental = getTheRental(box);
			if (myRental != null) {
				Vehicle vehicle = myRental.getVehicle();
				Person person = vehicle.getOwner();
				
				garageInfo = garageInfo + 
						"\n\t Box : " + box.toString() + 
						"\n\t\t Vehicule : " + vehicle.toString() + 
						"\n\t\t Personne : " + person.toString();
			}else {
				try {
					throw new Exception("ERROR, Locations are Empty!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return garageInfo;
		
	}
	
	
	
	private Rental getTheRental(Box box) {
		Rental theRental = null;
		if (rentals != null) {
			for (Rental rental : rentals) {
				Box boxRent = rental.getBox();
				if (box.getId().equals(boxRent.getId())) {
					theRental = rental;
				}
			}
		}
		return theRental;
	}
	

}
