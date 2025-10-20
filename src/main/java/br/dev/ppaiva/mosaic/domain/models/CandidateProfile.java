package br.dev.ppaiva.mosaic.domain.models;

import java.util.ArrayList;
import java.util.List;

import br.dev.ppaiva.mosaic.domain.enums.Seniority;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "CANDIDATE_PROFILE")
public class CandidateProfile extends AbstractEntity {
	private static final long serialVersionUID = 8729409470901609695L;
	
	@Column(name = "headline")
	private String headline;
	
	@Column(name = "location")
	private String location;
	
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "skill", nullable = false)
	private List<String> skills = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "seniority")
	private Seniority seniority;

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public Seniority getSeniority() {
		return seniority;
	}

	public void setSeniority(Seniority seniority) {
		this.seniority = seniority;
	}
}
