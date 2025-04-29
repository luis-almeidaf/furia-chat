package com.project.furia_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.furia_chat.entities.Player;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
