package fr.formation.afpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import fr.formation.afpa.entity.Box;
import fr.formation.afpa.entity.Garage;
import fr.formation.afpa.entity.Rental;
import fr.formation.afpa.repository.BoxRepository;
import fr.formation.afpa.repository.GarageRepository;
import fr.formation.afpa.repository.RentalRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class BoxTest extends TestFather {

	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BoxRepository boxRep;
	@Autowired
	private GarageRepository gRepo;
	@Autowired
	private RentalRepository rREpo;
	
	@Test
	public void List() {
		try {
			List<Box> list = (List<Box>)boxRep.findAll();
			assertNotNull(list);
			assertEquals(list.size(), 4);
		} catch (Exception e) {
			assertTrue(false);
		}
				
	}
	
	@Test
	public void findById() {
		try {
			String parkNumber = "AZ777";
			Box box1 = boxRep.findById(idLastBox).get();
			
			assertNotNull(box1);
			assertEquals(box1.getParkNumber(), parkNumber);
			
			} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void findByParkNumber() {
		String parkNumber = "AZ777";
		List<Box> boxes = boxRep.findByParkNumber(parkNumber);
		assertEquals(boxes.size(), 1);
		assertEquals(boxes.get(0).getParkNumber(), parkNumber);
	}
	
	
	@Test
	public void findBySurface() {
		double surface = 7;
		List<Box> boxes = boxRep.findBySurface(surface);
		
		assertNotNull(boxes);
		assertEquals(boxes.size(), 1);
		assertEquals(boxes.get(0).getSurface(), surface, 0.001); // assertEquals(double expected, double actual, double delta) => delta is a precision loss
		
	}
	
	@Test
	public void add() {
		try {
			Box box1 = new Box(null, "XYZ", 10);
			boxRep.save(box1);
			
			assertNotNull(box1);
			assertEquals(boxRep.count(), 5);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	
	@Test
	public void update() {
		try {
			assertNotNull(idLastBox);
			
			String newParkNumber = "ABC";
			Box box1 = boxRep.findById(idLastBox).get();
			
			assertNotEquals(box1.getParkNumber(), newParkNumber);
			
			box1.setParkNumber(newParkNumber);
			boxRep.save(box1);
			Box boxFromDB = boxRep.findById(box1.getId()).get();
			assertEquals(boxFromDB.getParkNumber(), newParkNumber);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void delete() {
				
		List<Box> list = (List<Box>)boxRep.findAll();
		assertNotNull(list);
		assertEquals(list.size(), 4);
		
		assertNotNull(idLastBox);
		
		Box b = boxRep.findById(idLastBox).get();
		assertNotNull(b);
		Garage garage = gRepo.findById(idLastGarage).get();
		garage.getBoxes().remove(b);							// FK deletion
		
		List<Rental> rentals = (List<Rental>)rREpo.findAll();
		for (Rental r:rentals) {
			if (r.getBox().getId() == b.getId()) {
				r.setBox(null);									// FK deletion
			}
		}
		
		boxRep.deleteById(idLastBox);
		assertEquals(boxRep.count(), 3);
		
		assertFalse(boxRep.findById(idLastBox).isPresent());
		
		
	}
	
	
}
