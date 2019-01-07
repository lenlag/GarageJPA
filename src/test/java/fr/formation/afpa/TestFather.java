package fr.formation.afpa;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.formation.afpa.entity.Address;
import fr.formation.afpa.entity.Box;
import fr.formation.afpa.entity.Garage;
import fr.formation.afpa.entity.Person;
import fr.formation.afpa.entity.Rental;
import fr.formation.afpa.entity.Vehicle;
import fr.formation.afpa.repository.RentalRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class TestFather {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RentalRepository rentRep;
	
	public Long idLastAddress = 0l;
	public Long idLastPerson = 0l;
	public Long idLastGarage = 0l;
	public Long idLastVehicule = 0l;
	public Long idLastBox = 0l;
	public Long idLastRent = 0l;

	@Before
	public void setUp() {
		initBdd();
	}
	
	
	@Test
	public void test() { // we should create at least 1 test, otherwise the prog will not compile	
		assertTrue(true);
	}
	
	public void initBdd() {
		
		Address address1 = new Address(null, "10 bis av des Anciens Combattants", "34000", "Montpellier");
		Address address2 = new Address(null, "Covent Garden", "WC2", "London");
		
		entityManager.persist(address1);
		idLastAddress = (Long) entityManager.persistAndGetId(address2);
		

		Vehicle veh1 = new Vehicle(null, "RCZ", "Peugeot", new Date(), "AX575NZ");
		Vehicle veh2 = new Vehicle(null, "Sp255", "Infinity", new Date(), "HH855KL");
		Vehicle veh3 = new Vehicle(null, "ZTK555", "Mazda", new Date(), "LH265PP");
		Vehicle veh4 = new Vehicle(null, "TT557", "Lexus", new Date(), "GW544KX");
		Vehicle veh5 = new Vehicle(null, "PM54VGFG", "Toyota", new Date(), "YF982PM");

		entityManager.persist(veh1);
		entityManager.persist(veh2);
		entityManager.persist(veh3);
		entityManager.persist(veh4);
		idLastVehicule = (Long) entityManager.persistAndGetId(veh5);
		

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

		entityManager.persist(garageOwner);
		entityManager.persist(p1);
		entityManager.persist(p2);
		idLastPerson = (Long) entityManager.persistAndGetId(p3);


		veh1.setOwner(p1);
		veh2.setOwner(p2);
		veh3.setOwner(p3);
		veh4.setOwner(p2);
		veh5.setOwner(garageOwner);

		entityManager.persist(veh1);
		entityManager.persist(veh2);
		entityManager.persist(veh3);
		entityManager.persist(veh4);
		entityManager.persist(veh5);
		

		Box box1 = new Box(null, "53DZ", 6);
		Box box2 = new Box(null, "56PL", 15);
		Box box3 = new Box(null, "YT55", 7);
		Box box4 = new Box(null, "AZ777", 19);

		entityManager.persist(box1);
		entityManager.persist(box2);
		entityManager.persist(box3);
		idLastBox  = (long)entityManager.persistAndGetId(box4);
		

		HashSet<Box> boxSet = new HashSet<Box>();
		boxSet.add(box1);
		boxSet.add(box2);
		boxSet.add(box3);
		boxSet.add(box4);

		Garage gar1 = new Garage(null, "Stendford's garage", address1, garageOwner, boxSet);
		idLastGarage = (long)entityManager.persistAndGetId(gar1);
		
		HashSet<Garage> garSet = new HashSet<Garage>();
		garSet.add(gar1);

		garageOwner.setGarages(garSet);
		entityManager.persist(garageOwner);
		
		
		Rental rent1 = new Rental(null, box1, veh1, 150.0f, new Date(), new Date());
		Rental rent2 = new Rental(null, box2, veh2, 40.0f, new Date(), new Date());
		Rental rent3 = new Rental(null, box3, veh3, 50.0f, new Date(), new Date());
		Rental rent4 = new Rental(null, box4, veh5, 70.0f, new Date(), new Date());

		entityManager.persist(rent1);
		entityManager.persist(rent2);
		entityManager.persist(rent3);
		idLastRent = (long)entityManager.persistAndGetId(rent4);
		

		Set<Garage> theGarageSet = new HashSet<>();
		theGarageSet.add(gar1);
		
		gar1.loadRentals((List<Rental>) rentRep.findAll());
		entityManager.persist(gar1);
		
		
		garageOwner.setGarages(theGarageSet);

		
	}

}

