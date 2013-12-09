package com.github.mateuszwenus.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.mateuszwenus.entity.Satellite;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
	
	Satellite findByName(String name);
	Collection<Satellite> findByNameEndingWith(String string);
	Collection<Satellite> findByPlanetName(String name);
}
