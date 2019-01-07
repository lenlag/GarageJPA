package fr.formation.afpa.repository;

import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.afpa.entity.Person;
import fr.formation.afpa.entity.Vehicle;

@SpringBootApplication
public class ExGarageJpaApplication implements CommandLineRunner {

	private static Log log = LogFactory.getLog(ExGarageJpaApplication.class);
	
	@Autowired
	AddressRepository addrRep;
	
	@Autowired
	BoxRepository boxRep;
	
	@Autowired
	CarRepository carRep;
	
	@Autowired
	GarageRepository garRep;
	
	@Autowired
	PersonRepository persRep;
	
	@Autowired
	RentalRepository rentRep;
	
	@Autowired
	VehicleRepository vehRep;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExGarageJpaApplication.class, args);
	}

	
	@Transactional
	public void run(String... args) throws Exception {
	
		log.info("Coucou");
		
		// *********UPLOAD THE DBASE WITH AN OWNER, 3 PERSONS, 
		//ONE OF THOSE HAVING AL TEAST 2 CARS, I.E. WE'LL OBTAIN AT LEAST 4 OCCUPIED BOXES***********
/*		
		
		Vehicle veh1 = new Vehicle(null, "NX88", "Mazda", new Date(), "AX577NG");
		Vehicle veh2 = new Vehicle(null, "GH635", "Ferrari", new Date(), "DF531LK");
		
		
		Person garageOwner = new Person(null, "Mickael", "Wayne");
		
		Person pers1 = new Person(null, "Richard", "Gere");
		Person pers2 = new Person(null, "Julia", "Roberts");
		Person pers3 = new Person(null, "Cindy", "Crawford");
	*/	
		
	}

}

