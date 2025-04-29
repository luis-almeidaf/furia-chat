package com.project.furia_chat.repositories;

import com.project.furia_chat.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
