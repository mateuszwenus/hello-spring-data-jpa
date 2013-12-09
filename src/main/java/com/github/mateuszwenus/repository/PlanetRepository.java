package com.github.mateuszwenus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.mateuszwenus.entity.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long>{
}
