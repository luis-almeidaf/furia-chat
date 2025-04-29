package com.project.furia_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.furia_chat.entities.Match;

public interface MatchRepository  extends JpaRepository<Match, Long>{
}
