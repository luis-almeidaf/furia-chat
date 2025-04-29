package com.project.furia_chat.services;

import com.project.furia_chat.dtos.PlayerDto;
import com.project.furia_chat.entities.Player;
import com.project.furia_chat.repositories.PlayerRepository;
import com.project.furia_chat.services.exceptions.DatabaseException;
import com.project.furia_chat.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public PlayerDto findById(Long id) {
        Player player = playerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PlayerDto(player);
    }

    @Transactional(readOnly = true)
    public Page<PlayerDto> findAll(Pageable pageable) {
        Page<Player> result = playerRepository.findAll(pageable);
        return result.map(x -> new PlayerDto(x));
    }

    @Transactional
    public PlayerDto insert(PlayerDto playerDto) {
        Player entitPlayer = new Player();
        copyDtoToEntity(playerDto, entitPlayer);
        entitPlayer = playerRepository.save(entitPlayer);
        return new PlayerDto(entitPlayer);
    }

    @Transactional
    public PlayerDto update(Long id, PlayerDto playerDto) {
        try {
            Player entity = playerRepository.getReferenceById(id);
            copyDtoToEntity(playerDto, entity);
            return new PlayerDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado com id");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado com id");
        }
        try {
            playerRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(PlayerDto playerDto, Player entity) {
        entity.setNickname(playerDto.getNickname());
        entity.setRole(playerDto.getRole());
        entity.setImg(playerDto.getImg());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
    }

}
