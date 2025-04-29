package com.project.furia_chat.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opponent", nullable = false, length = 30)
    private String opponent;

    @Column(name= "game_date", nullable = false)
    private LocalDateTime gameDate;

    @Column(length = 20)
    private String result;

    public Match() {
    }

    public Match(Long id, String opponent, LocalDateTime gameDate, String result) {
        this.id = id;
        this.opponent = opponent;
        this.gameDate = gameDate;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public LocalDateTime getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDateTime gameDate) {
        this.gameDate = gameDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", opponent='" + opponent + '\'' +
                ", gameDate=" + gameDate +
                ", result='" + result + '\'' +
                '}';
    }
}
