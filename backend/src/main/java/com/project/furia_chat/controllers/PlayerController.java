package com.project.furia_chat.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.furia_chat.dto.PlayerDto;
import com.project.furia_chat.services.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlayerDto> findById(@PathVariable Long id) {
        PlayerDto dto = playerService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PlayerDto>> findAll(Pageable pageable) {
        Page<PlayerDto> players = playerService.findAll(pageable);
        return ResponseEntity.ok(players);
    }

    @PostMapping
    public ResponseEntity<PlayerDto> insert(@Valid @RequestBody PlayerDto playerDto) {
        try {
            playerDto = playerService.insert(playerDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(playerDto.getId()).toUri();
            return ResponseEntity.created(uri).body(playerDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PlayerDto> update(@PathVariable Long id, @Valid @RequestBody PlayerDto dto) {
        dto = playerService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
