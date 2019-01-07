package fr.formation.afpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Address;



@Component
public interface AddressRepository extends CrudRepository<Address, Long> {

}
