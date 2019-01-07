package fr.formation.afpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Person;

@Component
public interface PersonRepository extends CrudRepository<Person, Long> {

}
