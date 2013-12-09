package com.github.mateuszwenus;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
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
		Assert.assertNotNull(satellite);
		Assert.assertEquals(MOON, satellite.getName());
	}
	
	@Test
	public void shouldFindSatellitesByPropertyEndsWith() {
		// when
		Collection<Satellite> satellites = satelliteRepository.findByNameEndingWith("%os");
		// then
		assertContains(satellites, PHOBOS, DEIMOS);
	}
	
	@Test
	public void shouldFindSatellitesByPlanetProperty() {
		// when
		Collection<Satellite> satellites = satelliteRepository.findByPlanetName(MARS);
		// then
		assertContains(satellites, PHOBOS, DEIMOS);
	}

	private void assertContains(Collection<Satellite> actualSatellites, String... expectedSatellites) {
		Set<String> actualSattelitesNames = new HashSet<String>();
		for (Satellite satellite : actualSatellites) {
			actualSattelitesNames.add(satellite.getName());
		}
		Set<String> expectedSattelitesNames = new HashSet<String>(Arrays.asList(expectedSatellites));
		Assert.assertEquals(expectedSattelitesNames, actualSattelitesNames);
	}
}
