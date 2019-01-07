package fr.formation.afpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Rental;

@Component
public interface RentalRepository extends CrudRepository<Rental, Long> {

}
