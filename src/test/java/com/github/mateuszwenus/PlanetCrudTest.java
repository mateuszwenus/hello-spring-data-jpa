package com.github.mateuszwenus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.mateuszwenus.entity.Planet;
import com.github.mateuszwenus.repository.PlanetRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@Transactional
public class PlanetCrudTest extends TestSupport {

	@Autowired
	private PlanetRepository planetRepository;

	@Test
	public void shouldFindPlanetById() {
		// when
		Planet planet = planetRepository.findOne(MERCURY_ID);
		// then
		Assert.assertNotNull(planet);
		Assert.assertEquals(MERCURY, planet.getName());
	}

	@Test
	public void shouldFindAllPlanets() {
		// when
		Iterable<Planet> planets = planetRepository.findAll();
		// then
		Assert.assertNotNull(planets);
		assertContains(planets, "Mercury", "Venus", "Earth", "Mars");
	}

	@Test
	public void shouldSaveNewPlanet() {
		// given
		Planet planet = new Planet(JUPITER_ID, JUPITER);
		// when
		planetRepository.save(planet);
		planetRepository.flush();
		// then
		Assert.assertEquals(1, numberOfPlanetsWithName(JUPITER));
	}

	@Test
	public void shouldUpdateExistingPlanet() {
		// given
		Planet planet = new Planet(MERCURY_ID, MERCURY);
		String oldName = planet.getName();
		String newName = oldName + oldName;
		planet.setName(newName);
		// when
		planetRepository.save(planet);
		planetRepository.flush();
		// then
		Assert.assertEquals(0, numberOfPlanetsWithName(oldName));
		Assert.assertEquals(1, numberOfPlanetsWithName(newName));
	}
	
	@Test
	public void shouldDeleteExistingPlanet() {
		// when
		planetRepository.delete(MERCURY_ID);
		planetRepository.flush();
		// then
		Assert.assertEquals(0, numberOfPlanetsWithName(MERCURY));
	}
	
	@Test
	public void shouldDeleteAllPlanets() {
		// when
		planetRepository.deleteAll();
		planetRepository.flush();
		// then
		Assert.assertEquals(0, countRowsInTable("planets"));
	}
	
	private int numberOfPlanetsWithName(String name) {
		return countRowsInTableWhere("planets", "name = '" + name + "'");
	}

	private void assertContains(Iterable<Planet> actualPlanets, String... expectedPlanets) {
		Set<String> actualPlanetNames = new HashSet<String>();
		for (Planet p : actualPlanets) {
			actualPlanetNames.add(p.getName());
		}
		Set<String> expectedPlanetNames = new HashSet<String>(Arrays.asList(expectedPlanets));
		Assert.assertEquals(expectedPlanetNames, actualPlanetNames);
	}
}
