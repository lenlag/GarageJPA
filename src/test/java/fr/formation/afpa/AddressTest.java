package fr.formation.afpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import fr.formation.afpa.entity.Address;
import fr.formation.afpa.repository.AddressRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class AddressTest extends TestFather {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AddressRepository addrRep;

	@Test
	public void List() {
		try {
			List<Address> list = (List<Address>) addrRep.findAll();
			assertNotNull(list);
			assertEquals(list.size(), 2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void findById() {
		try {
			String street = "Covent Garden";
			Address addr1 = addrRep.findById(idLastAddress).get();
			
			assertNotNull(addr1);
			assertEquals(addr1.getStreetAddress(), street);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void add() {
		try {
			Address newAddr = new Address(null, "Baker St.", "NW1" , "London");
			addrRep.save(newAddr);
			
			assertNotNull(newAddr);
			assertEquals(addrRep.count(), 3);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void update() {
		try {
			assertNotNull(idLastAddress);
			
			String newCity = "New York";
			Address addr1 = addrRep.findById(idLastAddress).get();
			
			assertNotEquals(addr1.getCity(), newCity);
			
			addr1.setCity(newCity); //we modify the object
			addrRep.save(addr1); // and we should save the object
			// but the test will work w/o saving the object, as well! weird, should be related to the TEST h2 database specificity, as in the real database it would fail
			
			
			
			Address addrFromDB = addrRep.findById(idLastAddress).get();
			assertEquals(addrFromDB.getCity(), newCity);
			
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void delete() {
		Address addr1 = addrRep.findById(idLastAddress).get();
		assertNotNull(addr1);
		
		addrRep.delete(addr1);
		assertEquals(addrRep.count(), 1);
		
	
		assertFalse(addrRep.findById(idLastAddress).isPresent());
		
	}
	
}
