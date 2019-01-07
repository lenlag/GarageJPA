package fr.formation.afpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Garage;

@Component
public interface GarageRepository extends CrudRepository<Garage, Long> {

}
