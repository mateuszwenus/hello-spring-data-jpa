package com.github.mateuszwenus;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public abstract class TestSupport extends AbstractTransactionalJUnit4SpringContextTests {

	protected static final Long MERCURY_ID = 1L;
	protected static final String MERCURY = "Mercury";
	protected static final Long VENUS_ID = 2L;
	protected static final String VENUS = "Venus";
	protected static final Long EARTH_ID = 3L;
	protected static final String EARTH = "Earth";
	protected static final Long MARS_ID = 4L;
	protected static final String MARS = "Mars";
	protected static final Long JUPITER_ID = 5L;
	protected static final String JUPITER = "Jupiter";
	
	protected static final String MOON = "Moon";
	protected static final String PHOBOS = "Phobos";
	protected static final String DEIMOS = "Deimos";
}
