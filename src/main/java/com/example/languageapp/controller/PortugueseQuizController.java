package com.example.languageapp.controller;

import com.example.languageapp.dto.portugueseQuiz.PortugueseQuizRequestDTO;
import com.example.languageapp.dto.portugueseQuiz.PortugueseQuizResponseDTO;
import com.example.languageapp.model.PortugueseQuiz;
import com.example.languageapp.service.PortugueseQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class PortugueseQuizController {

    private final PortugueseQuizService portugueseQuizService;

    @Autowired
    public PortugueseQuizController(PortugueseQuizService portugueseQuizService) {
        this.portugueseQuizService = portugueseQuizService;
    }

    @GetMapping("/{id}")
    public PortugueseQuizResponseDTO getQuizById(@PathVariable Long id) {
        return toResponseDTO(portugueseQuizService.findById(id));
    }

    @PostMapping
    public PortugueseQuizResponseDTO saveQuiz(@RequestBody PortugueseQuizRequestDTO dto) {
        PortugueseQuiz saved = portugueseQuizService.saveFromDTO(dto);
        return toResponseDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        portugueseQuizService.deleteById(id);
    }

    @GetMapping("/score-range")
    public List<PortugueseQuizResponseDTO> getQuizzesByScore(@RequestParam double min, @RequestParam double max) {
        return portugueseQuizService.findAllByScoreRange(min, max).stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/date-range")
    public List<PortugueseQuizResponseDTO> getQuizzesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return portugueseQuizService.findAllByDateRange(start, end).stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/mistake-range")
    public List<PortugueseQuizResponseDTO> getQuizzesByMistakeRange(@RequestParam int min, @RequestParam int max) {
        return portugueseQuizService.findAllByMistakeRange(min, max).stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/duration-range")
    public List<PortugueseQuizResponseDTO> getQuizzesByDurationRange(@RequestParam int min, @RequestParam int max) {
        return portugueseQuizService.findAllByDurationRange(min, max).stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/level")
    public List<PortugueseQuizResponseDTO> getQuizzesByLevel(@RequestParam int level) {
        return portugueseQuizService.findAllByLevel(level).stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/pass")
    public List<PortugueseQuizResponseDTO> getQuizzesByPassStatus(@RequestParam boolean pass) {
        return portugueseQuizService.findAllByPassStatus(pass).stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    private PortugueseQuizResponseDTO toResponseDTO(PortugueseQuiz quiz) {
        PortugueseQuizResponseDTO dto = new PortugueseQuizResponseDTO();
        dto.setId(quiz.getId());
        dto.setScore(quiz.getScore());
        dto.setLevel(quiz.getLevel());
        dto.setDate(quiz.getDate());
        dto.setPass(quiz.isPass());
        return dto;
    }
}
