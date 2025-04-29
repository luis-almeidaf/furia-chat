package com.project.furia_chat.dto;

import java.time.LocalDateTime;

import com.project.furia_chat.entity.Player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlayerDto {

    private Long id;

    @NotBlank(message = "Nickname é obrigatório")
    @Size(max = 100, message = "Nickname deve ter no máximo 100 caracteres")
    private String nickname;

    @NotBlank(message = "Role é obrigatória")
    @Size(max = 30, message = "Role deve ter no máximo 30 caracteres")
    private String role;

    @Size(max = 255, message = "URL da imagem deve ter no máximo 255 caracteres")
    private String img;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    
    public PlayerDto() {
    }

    public PlayerDto(Long id, String nickname, String role, String img, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nickname = nickname;
        this.role = role;
        this.img = img;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PlayerDto(Player entity) {
        this.id = entity.getId();
        this.nickname = entity.getNickname();
        this.role = entity.getRole();
        this.img = entity.getImg();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRole() {
        return role;
    }

    public String getImg() {
        return img;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
