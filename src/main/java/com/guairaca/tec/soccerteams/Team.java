package com.guairaca.tec.soccerteams;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
    private int wins;
    private int defeats;
    private int draws;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return this.wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDefeats() {
		return this.defeats;
	}
	
	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}

	public int getDraws() {
		return this.draws;
	}
	
	public void setDraws(int draws) {
		this.draws = draws;
	}
}
