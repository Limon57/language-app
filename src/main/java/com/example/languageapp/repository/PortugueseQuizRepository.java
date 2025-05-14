package com.example.languageapp.repository;

import com.example.languageapp.model.PortuguesePractice;
import com.example.languageapp.model.PortugueseQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PortugueseQuizRepository extends JpaRepository<PortugueseQuiz, Long> {

    List<PortugueseQuiz> findAllByScoreBetween(double minScore, double maxScore);
    List<PortugueseQuiz> findAllByDateBetween(LocalDateTime minDate, LocalDateTime maxDate);
    List<PortugueseQuiz> findAllByMistakesBetween(int minMistakes, int maxMistakes);
    List<PortugueseQuiz> findAllByDurationBetween(int minDuration, int maxDuration);
    List<PortugueseQuiz> findAllByLevel(int level);
    List<PortugueseQuiz> findAllByPass(boolean pass);

}
