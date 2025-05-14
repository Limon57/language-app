package com.example.languageapp.repository;

import com.example.languageapp.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findByCategory(String category);
    List<Word> findByOccurrence(String occurrence);
    List<Word> findByTranslation(String Translation);
    List<Word> findBySuccessRateBetween(Double min, Double max);
}
