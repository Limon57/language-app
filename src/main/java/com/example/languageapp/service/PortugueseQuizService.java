package com.example.languageapp.service;

import com.example.languageapp.dto.portugueseQuiz.PortugueseQuizRequestDTO;
import com.example.languageapp.model.PortugueseModel;
import com.example.languageapp.model.PortugueseQuiz;
import com.example.languageapp.model.Word;
import com.example.languageapp.repository.PortugueseModelRepository;
import com.example.languageapp.repository.PortugueseQuizRepository;
import com.example.languageapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PortugueseQuizService {

    private final PortugueseQuizRepository portugueseQuizRepository;
    private final PortugueseModelRepository portugueseModelRepository;
    private final WordRepository wordRepository;

    @Autowired
    public PortugueseQuizService(PortugueseQuizRepository portugueseQuizRepository,
                                 PortugueseModelRepository portugueseModelRepository,
                                 WordRepository wordRepository) {
        this.portugueseQuizRepository = portugueseQuizRepository;
        this.portugueseModelRepository = portugueseModelRepository;
        this.wordRepository = wordRepository;
    }

    public PortugueseQuiz saveFromDTO(PortugueseQuizRequestDTO dto) {
        PortugueseQuiz quiz = new PortugueseQuiz();
        quiz.setScore(dto.getScore());
        quiz.setLevel(dto.getLevel());
        quiz.setDate(LocalDateTime.now());
        quiz.setMistakes(dto.getMistakes());
        quiz.setDuration(dto.getDuration());
        quiz.setPass(dto.isPass());

        PortugueseModel model = portugueseModelRepository.findById(dto.getPortugueseModelId())
                .orElseThrow(() -> new RuntimeException("Portuguese model not found"));
        quiz.setPortugueseModel(model);

        List<Word> words = wordRepository.findAllById(dto.getWordIds());
        quiz.setWords(words);

        return portugueseQuizRepository.save(quiz);
    }

    public PortugueseQuiz findById(Long id) {
        return portugueseQuizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PortugueseQuiz not found"));
    }

    public void deleteById(Long id) {
        portugueseQuizRepository.deleteById(id);
    }

    public List<PortugueseQuiz> findAllByScoreRange(double min, double max) {
        return portugueseQuizRepository.findAllByScoreBetween(min, max);
    }

    public List<PortugueseQuiz> findAllByDateRange(LocalDateTime start, LocalDateTime end) {
        return portugueseQuizRepository.findAllByDateBetween(start, end);
    }

    public List<PortugueseQuiz> findAllByMistakeRange(int min, int max) {
        return portugueseQuizRepository.findAllByMistakesBetween(min, max);
    }

    public List<PortugueseQuiz> findAllByDurationRange(int min, int max) {
        return portugueseQuizRepository.findAllByDurationBetween(min, max);
    }

    public List<PortugueseQuiz> findAllByLevel(int level) {
        return portugueseQuizRepository.findAllByLevel(level);
    }

    public List<PortugueseQuiz> findAllByPassStatus(boolean pass) {
        return portugueseQuizRepository.findAllByPass(pass);
    }
}
