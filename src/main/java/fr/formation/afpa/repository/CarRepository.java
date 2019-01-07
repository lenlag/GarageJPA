package fr.formation.afpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Car;

@Component
public interface CarRepository extends CrudRepository<Car, Long> {

}
