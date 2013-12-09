package com.github.mateuszwenus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.mateuszwenus.entity.Satellite;
import com.github.mateuszwenus.repository.SatelliteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@Transactional
public class SatelliteQueryTest extends TestSupport {

	@Autowired
	private SatelliteRepository satelliteRepository;

	@Test
	public void shouldFindSatelliteByPropertyEq() {
		// when
		Satellite satellite = satelliteRepository.findByName(MOON);
		// then
		assertThat(satellite, is(notNullValue()));
		assertThat(satellite.getName(), is(MOON));
	}

	@Test
	public void shouldFindSatellitesByPropertyEndsWith() {
		// when
		Collection<Satellite> satellites = satelliteRepository.findByNameEndingWith("%os");
		// then
		assertThat(satellites, is(notNullValue()));
		assertThat(satellites, containsInAnyOrder(satellite(PHOBOS), satellite(DEIMOS)));
	}

	@Test
	public void shouldFindSatellitesByPlanetProperty() {
		// when
		Collection<Satellite> satellites = satelliteRepository.findByPlanetName(MARS);
		// then
		assertThat(satellites, is(notNullValue()));
		assertThat(satellites, containsInAnyOrder(satellite(PHOBOS), satellite(DEIMOS)));
	}
}
