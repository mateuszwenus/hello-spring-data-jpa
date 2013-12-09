package com.github.mateuszwenus.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "planets")
@NamedQuery(name = "Planet.findLighterThanEarth", query = "select p from Planet p where p.massRelativeToEarth < 1")
public class Planet {

	@Id
	private Long id;
	private String name;
	@Column(name = "mass_rel_to_earth")
	private BigDecimal massRelativeToEarth;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planet")
	private Set<Satellite> satellites;

	public Planet() {
	}

	public Planet(String name) {
		this.name = name;
	}

	public Planet(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMassRelativeToEarth() {
		return massRelativeToEarth;
	}

	public void setMassRelativeToEarth(BigDecimal massRelativeToEarth) {
		this.massRelativeToEarth = massRelativeToEarth;
	}

	public Set<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(Set<Satellite> satellites) {
		this.satellites = satellites;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Planet [name=" + name + "]";
	}
}
