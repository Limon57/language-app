package com.example.languageapp.dto.portugueseQuiz;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PortugueseQuizResponseDTO {
    private Long id;
    private double score;
    private int level;
    private LocalDateTime date;
    private boolean pass;
}
