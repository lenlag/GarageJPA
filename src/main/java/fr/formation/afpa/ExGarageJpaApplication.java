package fr.formation.afpa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.afpa.entity.Address;
import fr.formation.afpa.entity.Box;
import fr.formation.afpa.entity.Garage;
import fr.formation.afpa.entity.Person;
import fr.formation.afpa.entity.Rental;
import fr.formation.afpa.entity.Vehicle;
import fr.formation.afpa.repository.AddressRepository;
import fr.formation.afpa.repository.BoxRepository;
import fr.formation.afpa.repository.CarRepository;
import fr.formation.afpa.repository.GarageRepository;
import fr.formation.afpa.repository.PersonRepository;
import fr.formation.afpa.repository.RentalRepository;
import fr.formation.afpa.repository.VehicleRepository;

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

		initBDD();
		// *********UPLOAD THE DBASE WITH AN OWNER, 3 PERSONS,
		// ONE OF THOSE HAVING AL TEAST 2 CARS, I.E. WE'LL OBTAIN AT LEAST 4 OCCUPIED
		// BOXES***********

		
	}

	private void initBDD() { // to upload the DB

		Address address1 = new Address(null, "10 bis av des Anciens Combattants", "34000", "Montpellier");
		Address address2 = new Address(null, "Covent Garden", "WC2", "London");
		addrRep.save(address1);
		addrRep.save(address2);

		Date myDate = new Date();
		Date date = new Date();

		/*
		 * Car car1 = new Car(null, "RCZ", "Peugeot", year); Car car2 = new Car(null,
		 * "Sp255", "Infinity", year); Car car3 = new Car(null, "ZTK555", "Mazda",
		 * year); Car car4 = new Car(null, "TT557", "Lexus", year); Car car5 = new
		 * Car(null, "PM54VGFG", "Toyota", year);
		 * 
		 * carRep.save(car1); carRep.save(car2); carRep.save(car3); carRep.save(car4);
		 * carRep.save(car5);
		 * 
		 */

		Vehicle veh1 = new Vehicle(null, "RCZ", "Peugeot", myDate, "AX575NZ");
		Vehicle veh2 = new Vehicle(null, "Sp255", "Infinity", myDate, "HH855KL");
		Vehicle veh3 = new Vehicle(null, "ZTK555", "Mazda", myDate, "LH265PP");
		Vehicle veh4 = new Vehicle(null, "TT557", "Lexus", myDate, "GW544KX");
		Vehicle veh5 = new Vehicle(null, "PM54VGFG", "Toyota", myDate, "YF982PM");

		vehRep.save(veh1);
		vehRep.save(veh2);
		vehRep.save(veh3);
		vehRep.save(veh4);
		vehRep.save(veh5);

		HashSet<Vehicle> vehiclesMichael = new HashSet<Vehicle>();
		vehiclesMichael.add(veh5);

		HashSet<Vehicle> vehiclesRichard = new HashSet<Vehicle>();
		vehiclesRichard.add(veh1);

		HashSet<Vehicle> vehiclesJulia = new HashSet<Vehicle>();
		vehiclesJulia.add(veh2);
		vehiclesJulia.add(veh4);

		HashSet<Vehicle> vehiclesCindy = new HashSet<Vehicle>();
		vehiclesCindy.add(veh3);

		Person garageOwner = new Person(null, "Mickael", "Wayne", vehiclesMichael);
		Person p1 = new Person(null, "Richard", "Gere", vehiclesRichard);
		Person p2 = new Person(null, "Julia", "Roberts", vehiclesJulia);
		Person p3 = new Person(null, "Cindy", "Crawford", vehiclesCindy);

		persRep.save(garageOwner);
		persRep.save(p1);
		persRep.save(p2);
		persRep.save(p3);

		/*
		 * 
		 * log.info(p2.getVehicles().toString());
		 * 
		 * Set<Vehicle> mySet = p2.getVehicles(); for (Vehicle vehicle : mySet) {
		 * log.info(vehicle); }
		 * 
		 * 
		 * List<Vehicle> v = (List<Vehicle>) vehRep.findAll(); for (Vehicle veh : v) {
		 * log.info(veh); }
		 * 
		 * List<Person> pList = (List<Person>) persRep.findAll(); for (Person person :
		 * pList) { log.info(person); }
		 * 
		 */

		Person Richard = persRep.findById(1l).get();
		// log.info(Richard.toString());

		veh1.setOwner(p1);
		veh2.setOwner(p2);
		veh3.setOwner(p3);
		veh4.setOwner(p2);
		veh5.setOwner(garageOwner);

		vehRep.save(veh1);
		vehRep.save(veh2);
		vehRep.save(veh3);
		vehRep.save(veh4);
		vehRep.save(veh5);

		Box box1 = new Box(null, "53DZ", 6);
		Box box2 = new Box(null, "56PL", 15);
		Box box3 = new Box(null, "YT55", 7);
		Box box4 = new Box(null, "AZ777", 19);

		boxRep.save(box1);
		boxRep.save(box2);
		boxRep.save(box3);
		boxRep.save(box4);

		HashSet<Box> boxSet = new HashSet<Box>();
		boxSet.add(box1);
		boxSet.add(box2);
		boxSet.add(box3);
		boxSet.add(box4);

		Garage gar1 = new Garage(null, "Stendford's garage", address1, garageOwner, boxSet);
		garRep.save(gar1);

		HashSet<Garage> garSet = new HashSet<Garage>();
		garSet.add(gar1);

		garageOwner.setGarages(garSet);
		persRep.save(garageOwner);

		Rental rent1 = new Rental(null, box1, veh1, 150.0f, date, myDate);
		Rental rent2 = new Rental(null, box2, veh2, 40.0f, date, myDate);
		Rental rent3 = new Rental(null, box3, veh3, 50.0f, date, myDate);
		Rental rent4 = new Rental(null, box4, veh5, 70.0f, date, myDate);

		rentRep.save(rent1);
		rentRep.save(rent2);
		rentRep.save(rent3);
		rentRep.save(rent4);

		Set<Garage> theGarageSet = new HashSet<>();
		theGarageSet.add(gar1);
		
		gar1.loadRentals((List<Rental>) rentRep.findAll());
		garRep.save(gar1);

		garageOwner.setGarages(theGarageSet);

		
		log.info(gar1.toString());

	}

	

}
