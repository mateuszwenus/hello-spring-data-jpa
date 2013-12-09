package com.github.mateuszwenus.dto;

public class PlanetDto {

	private String name;
	private int numberOfSatellites;

	public PlanetDto(String name, int numberOfSatellites) {
		this.name = name;
		this.numberOfSatellites = numberOfSatellites;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfSatellites() {
		return numberOfSatellites;
	}
}
