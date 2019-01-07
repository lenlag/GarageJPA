package fr.formation.afpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Vehicle;

@Component
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
