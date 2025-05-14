package com.example.languageapp.dto.portugueseModel;

import lombok.Data;

@Data
public class PortugueseModelResponseDTO {
    private Long id;
    private int userLevel;
    private int userStreak;
    private int totalPractices;
    private int totalQuizzes;
    private int totalVaultWords;
}
