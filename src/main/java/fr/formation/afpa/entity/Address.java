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
@Table(name = "Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "Street_Address", nullable = false, length = 255)
	private String streetAddress;
	
	@Column(name = "Zip_Code", nullable = false, length = 10)
	private String zipCode;
	
	@Column(name = "City", nullable = false, length = 255)
	private String city;
	
	
	
	public Address () {
		
	}

	
	public Address(Long id, String streetAddress, String zipCode, String city) {
		super();
		this.id = id;
		this.streetAddress = streetAddress;
		this.zipCode = zipCode;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetAddress=" + streetAddress + ", zipCode=" + zipCode + ", city=" + city
				+ "]";
	}
	
	

}
