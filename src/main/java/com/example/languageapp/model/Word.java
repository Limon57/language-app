package com.example.languageapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String translation;
    private String pronunciation;
    private String occurrence; //how often is this word said(common, rare, etc)
    private double rightCounter;
    private double wrongCounter;
    private double successRate; //percent of getting this word right in quizes


}
