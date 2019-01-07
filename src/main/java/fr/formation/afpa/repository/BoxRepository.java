package fr.formation.afpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.formation.afpa.entity.Box;

@Component
public interface BoxRepository extends CrudRepository<Box, Long> {
	
	List <Box> findByParkNumber(String parkNumber);
	
	List <Box> findBySurface(double surface);
	
	
}
