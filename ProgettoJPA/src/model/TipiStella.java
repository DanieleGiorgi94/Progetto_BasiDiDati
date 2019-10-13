package model;

import java.util.Collection;

import javax.persistence.*;

import model.BandaTipiStella;

@Entity
public class TipiStella {
	
	@Id
	private String name;
	
	@OneToMany(mappedBy="typeStars")
	private Collection<Stella> stars;
	
	@OneToMany(mappedBy="typeStars")
	private Collection<BandaTipiStella> bandstypestars;
	
	public Collection<BandaTipiStella> getBandstypestars() {
		return bandstypestars;
	}

	public void setBandstypestars(Collection<BandaTipiStella> bandstypestars) {
		this.bandstypestars = bandstypestars;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Stella> getStars() {
		return stars;
	}

	public void setStars(Collection<Stella> stars) {
		this.stars = stars;
	}
	
}