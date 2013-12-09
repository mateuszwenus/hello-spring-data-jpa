package com.github.mateuszwenus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.mateuszwenus.entity.Planet;
import com.github.mateuszwenus.repository.PlanetRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@Transactional
public class PlanetQueryTest extends TestSupport {

	@Autowired
	private PlanetRepository planetRepository;
	
	@Test
	public void shouldReturnFirstPage() {
		// given
		Pageable pageable = new PageRequest(0, 2, new Sort(Direction.DESC, "massRelativeToEarth"));
		// when
		Page<Planet> planets = planetRepository.findAll(pageable);
		// then
		assertThat(planets.getContent(), contains(planet(EARTH), planet(VENUS)));
	}
	
	@Test
	public void shouldReturnSecondPage() {
		// given
		Pageable pageable = new PageRequest(1, 2, new Sort(Direction.DESC, "massRelativeToEarth"));
		// when
		Page<Planet> planets = planetRepository.findAll(pageable);
		// then
		assertThat(planets.getContent(), contains(planet(MARS), planet(MERCURY)));
	}
}
