package com.guairaca.tec.soccerteams;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeamRepository 
	extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {
	public Page<Team> findAllByNameLike(String name, Pageable pageable);
}
