package com.github.mateuszwenus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
		assertThat(planet, is(notNullValue()));
		assertThat(planet.getName(), is(MERCURY));
	}

	@Test
	public void shouldFindAllPlanets() {
		// when
		Iterable<Planet> planets = planetRepository.findAll();
		// then
		assertThat(planets, is(notNullValue()));
		assertThat(planets, containsInAnyOrder(planet(MERCURY), planet(VENUS), planet(EARTH), planet(MARS)));
	}

	@Test
	public void shouldSaveNewPlanet() {
		// given
		Planet planet = new Planet(JUPITER_ID, JUPITER);
		// when
		planetRepository.save(planet);
		planetRepository.flush();
		// then
		assertThat(numberOfPlanetsWithName(JUPITER), is(1));
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
		assertThat(numberOfPlanetsWithName(oldName), is(0));
		assertThat(numberOfPlanetsWithName(newName), is(1));
	}
	
	@Test
	public void shouldDeleteExistingPlanet() {
		// when
		planetRepository.delete(MERCURY_ID);
		planetRepository.flush();
		// then
		assertThat(numberOfPlanetsWithName(MERCURY), is(0));
	}
	
	@Test
	public void shouldDeleteAllPlanets() {
		// when
		planetRepository.deleteAll();
		planetRepository.flush();
		// then
		assertThat(countRowsInTable("planets"), is(0));
	}
	
	private int numberOfPlanetsWithName(String name) {
		return countRowsInTableWhere("planets", "name = '" + name + "'");
	}
}
