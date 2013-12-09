package com.github.mateuszwenus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.github.mateuszwenus.dto.PlanetDto;
import com.github.mateuszwenus.entity.Planet;

@Transactional(readOnly = true)
public interface PlanetRepository extends JpaRepository<Planet, Long> {

	@Query("select new com.github.mateuszwenus.dto.PlanetDto(p.name, p.satellites.size) from Planet p where p.name = :name")
	PlanetDto findPlanetDtoByName(@Param("name") String name);

	List<Planet> findLighterThanEarth();
}
