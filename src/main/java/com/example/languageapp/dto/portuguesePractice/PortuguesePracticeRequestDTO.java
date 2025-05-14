package com.example.languageapp.dto.portuguesePractice;

import lombok.Data;
import java.util.List;

@Data
public class PortuguesePracticeRequestDTO {
    private double score;
    private int mistakes;
    private int duration;
    private int wordsPracticed;
    private String practiceType;
    private List<Long> wordIds;
    private Long portugueseModelId;
}