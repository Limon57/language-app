package com.example.languageapp.dto.word;

import lombok.Data;
@Data
public class WordDetailDTO {
    private Long id;
    private String category;
    private String translation;
    private String pronunciation;
    private String occurrence;
    private double rightCounter;
    private double wrongCounter;
    private double successRate;
}
