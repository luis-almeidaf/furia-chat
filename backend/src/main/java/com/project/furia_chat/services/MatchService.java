package com.project.furia_chat.services;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.furia_chat.dtos.MatchDto;
import com.project.furia_chat.entities.Match;
import com.project.furia_chat.repositories.MatchRepository;
import com.project.furia_chat.services.exceptions.DatabaseException;
import com.project.furia_chat.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;



@Service
public class MatchService {

    private MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    public MatchDto findById(Long id) {
        Match match = matchRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new MatchDto(match);
    }

    @Transactional(readOnly = true)
    public Page<MatchDto> findAll(Pageable pageable) {
        Page<Match> result = matchRepository.findAll(pageable);
        return result.map(x -> new MatchDto(x));
    }

    @Transactional
    public MatchDto insert(MatchDto matchDto) {
        Match entityMatch = new Match();
        copyDtoToEntity(matchDto, entityMatch);
        entityMatch = matchRepository.save(entityMatch);
        return new MatchDto(entityMatch);
    }

    @Transactional
    public MatchDto update(Long id, MatchDto matchDto) {
        try {
            Match entity = matchRepository.getReferenceById(id);
            copyDtoToEntity(matchDto, entity);
            return new MatchDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            matchRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyDtoToEntity(MatchDto matchDto, Match entity) {
        entity.setOpponent(matchDto.getOpponent());
        entity.setGameDate(matchDto.getGameDate());
        entity.setResult(matchDto.getResult());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
