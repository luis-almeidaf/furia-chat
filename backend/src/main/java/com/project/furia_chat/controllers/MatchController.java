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

import com.project.furia_chat.dtos.MatchDto;
import com.project.furia_chat.services.MatchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "matches")
public class MatchController {
    
    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MatchDto> findById(@PathVariable Long id) {
        MatchDto dto = matchService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MatchDto>> findAll(Pageable pageable) {
        Page<MatchDto> matches = matchService.findAll(pageable);
        return ResponseEntity.ok(matches);
    }

    @PostMapping
    public ResponseEntity<MatchDto> insert(@Valid @RequestBody MatchDto matchDto) {
        try {
            matchDto = matchService.insert(matchDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(matchDto.getId()).toUri();
            return ResponseEntity.created(uri).body(matchDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MatchDto> update(@PathVariable Long id, @Valid @RequestBody MatchDto dto) {
        dto = matchService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
        
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
