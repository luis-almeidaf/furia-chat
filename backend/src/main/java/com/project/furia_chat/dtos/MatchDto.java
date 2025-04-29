package com.project.furia_chat.dtos;

import java.time.LocalDateTime;

import com.project.furia_chat.entities.Match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MatchDto {
    
    private Long id;

    @NotBlank(message = "Opponent é obrigatório")
    @Size(max = 30, message = "Opponent deve ter no máximo 30 caracteres")
    private String opponent;

    private LocalDateTime gameDate;

    @Size(max = 20, message = "Opponent deve ter no máximo 20 caracteres")
    private String result;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public MatchDto() {
    }

    public MatchDto(Long id, String opponent, LocalDateTime gameDate, String result, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.opponent = opponent;
        this.gameDate = gameDate;
        this.result = result;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MatchDto(Match entity) {
        this.id = entity.getId();
        this.opponent = entity.getOpponent();
        this.gameDate = entity.getGameDate();
        this.result = entity.getResult();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getOpponent() {
        return opponent;
    }

    public LocalDateTime getGameDate() {
        return gameDate;
    }

    public String getResult() {
        return result;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
}
