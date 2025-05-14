package com.example.languageapp.dto.portugueseQuiz;

import java.util.List;
import lombok.Data;

@Data
public class PortugueseQuizRequestDTO {
    private double score;
    private int level;
    private int mistakes;
    private int duration;
    private boolean pass;
    private List<Long> wordIds;
    private Long portugueseModelId;
}