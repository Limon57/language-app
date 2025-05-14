package com.example.languageapp.controller;

import com.example.languageapp.dto.portugueseModel.PortugueseModelRequestDTO;
import com.example.languageapp.dto.portugueseModel.PortugueseModelResponseDTO;
import com.example.languageapp.model.PortugueseModel;
import com.example.languageapp.model.Word;
import com.example.languageapp.service.PortugueseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portuguese-model")
public class PortugueseModelController {

    private final PortugueseModelService portugueseModelService;

    @Autowired
    public PortugueseModelController(PortugueseModelService portugueseModelService){
        this.portugueseModelService = portugueseModelService;
    }

    // ✅ Create Portuguese model using DTO
    @PostMapping
    public PortugueseModelResponseDTO createModel(@RequestBody PortugueseModelRequestDTO dto) {
        PortugueseModel model = portugueseModelService.createModelFromDTO(dto);
        return toResponseDTO(model);
    }

    // ✅ Get model by ID as response DTO
    @GetMapping("/{id}")
    public PortugueseModelResponseDTO getModelById(@PathVariable Long id){
        PortugueseModel model = portugueseModelService.findById(id);
        return toResponseDTO(model);
    }

    // Vault
    @GetMapping("/{id}/vault")
    public List<Word> getVault(@PathVariable Long id){
        return portugueseModelService.getUserVault(id);
    }

    // Level endpoints
    @GetMapping("/{id}/level")
    public int getLevel(@PathVariable Long id){
        return portugueseModelService.getLevel(id);
    }

    @PutMapping("/{id}/level/update")
    public void updateLevel(@PathVariable Long id, @RequestParam int newLevel){
        portugueseModelService.updateUserLevel(id, newLevel);
    }

    @PutMapping("/{id}/level/increase")
    public void increaseLevel(@PathVariable Long id){
        portugueseModelService.increaseUserLevel(id);
    }

    // Streak endpoints
    @GetMapping("/{id}/streak")
    public int getStreak(@PathVariable Long id){
        return portugueseModelService.getStreak(id);
    }

    @PutMapping("/{id}/streak/update")
    public void updateStreak(@PathVariable Long id, @RequestParam int newStreak){
        portugueseModelService.updateUserStreak(id, newStreak);
    }

    @PutMapping("/{id}/streak/increase")
    public void increaseStreak(@PathVariable Long id){
        portugueseModelService.increaseUserStreak(id);
    }

    @PutMapping("/{id}/streak/reset")
    public void resetStreak(@PathVariable Long id){
        portugueseModelService.resetUserStreak(id);
    }

    // 🔄 DTO mapping helper
    private PortugueseModelResponseDTO toResponseDTO(PortugueseModel model) {
        PortugueseModelResponseDTO dto = new PortugueseModelResponseDTO();
        dto.setId(model.getId());
        dto.setUserLevel(model.getUserLevel());
        dto.setUserStreak(model.getUserStreak());
        dto.setTotalVaultWords(model.getVault() != null ? model.getVault().size() : 0);
        return dto;
    }
}
