package com.example.languageapp.controller;

import com.example.languageapp.dto.portuguesePractice.PortuguesePracticeRequestDTO;
import com.example.languageapp.dto.portuguesePractice.PortuguesePracticeResponseDTO;
import com.example.languageapp.model.PortuguesePractice;
import com.example.languageapp.service.PortuguesePracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/practice")
public class PortuguesePracticeController {

    private final PortuguesePracticeService portuguesePracticeService;

    @Autowired
    public PortuguesePracticeController(PortuguesePracticeService portuguesePracticeService) {
        this.portuguesePracticeService = portuguesePracticeService;
    }

    @GetMapping("/{id}")
    public PortuguesePracticeResponseDTO getPracticeById(@PathVariable Long id) {
        PortuguesePractice practice = portuguesePracticeService.findById(id);
        return toResponseDTO(practice);
    }

    @PostMapping
    public PortuguesePracticeResponseDTO savePractice(@RequestBody PortuguesePracticeRequestDTO dto) {
        PortuguesePractice practice = portuguesePracticeService.saveFromDTO(dto);
        return toResponseDTO(practice);
    }

    @DeleteMapping("/{id}")
    public void deletePracticeById(@PathVariable Long id) {
        portuguesePracticeService.deleteById(id);
    }

    @GetMapping("/score-range")
    public List<PortuguesePracticeResponseDTO> getByScoreRange(@RequestParam double min, @RequestParam double max) {
        return portuguesePracticeService.findAllByScoreRange(min, max)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/date-range")
    public List<PortuguesePracticeResponseDTO> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime min,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime max) {
        return portuguesePracticeService.findAllByDateRange(min, max)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/mistake-range")
    public List<PortuguesePracticeResponseDTO> getByMistakeRange(@RequestParam int min, @RequestParam int max) {
        return portuguesePracticeService.findAllByMistakeRange(min, max)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/duration-range")
    public List<PortuguesePracticeResponseDTO> getByDurationRange(@RequestParam int min, @RequestParam int max) {
        return portuguesePracticeService.findAllByDurationRange(min, max)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/words-range")
    public List<PortuguesePracticeResponseDTO> getByWordsRange(@RequestParam int min, @RequestParam int max) {
        return portuguesePracticeService.findAllByWordsPracticedRange(min, max)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/type")
    public List<PortuguesePracticeResponseDTO> getByPracticeType(@RequestParam String type) {
        return portuguesePracticeService.findAllByPracticeType(type)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    // Mapping helper
    private PortuguesePracticeResponseDTO toResponseDTO(PortuguesePractice practice) {
        PortuguesePracticeResponseDTO dto = new PortuguesePracticeResponseDTO();
        dto.setId(practice.getId());
        dto.setScore(practice.getScore());
        dto.setDate(practice.getDate());
        dto.setMistakes(practice.getMistakes());
        dto.setDuration(practice.getDuration());
        dto.setWordsPracticed(practice.getWordsPracticed());
        dto.setPracticeType(practice.getPracticeType());
        return dto;
    }
}
