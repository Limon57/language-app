package com.example.languageapp.dto.portuguesePractice;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class PortuguesePracticeResponseDTO {
    private Long id;
    private double score;
    private LocalDateTime date;
    private int mistakes;
    private int duration;
    private int wordsPracticed;
    private String practiceType;
}
