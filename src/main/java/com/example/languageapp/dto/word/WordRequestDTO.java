package com.example.languageapp.dto.word;

import lombok.Data;
@Data
public class WordRequestDTO {
    private String category;
    private String translation;
    private String pronunciation;
    private String occurrence;
}

