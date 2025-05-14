package com.example.languageapp.service;

import com.example.languageapp.dto.portugueseModel.PortugueseModelRequestDTO;
import com.example.languageapp.model.PortugueseLibrary;
import com.example.languageapp.model.PortugueseModel;
import com.example.languageapp.model.Word;
import com.example.languageapp.repository.PortugueseLibraryRepository;
import com.example.languageapp.repository.PortugueseModelRepository;
import com.example.languageapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortugueseModelService {

    private final PortugueseModelRepository portugueseModelRepository;
    private final PortugueseLibraryRepository portugueseLibraryRepository;
    private final WordRepository wordRepository;

    @Autowired
    public PortugueseModelService(
            PortugueseModelRepository portugueseModelRepository,
            PortugueseLibraryRepository portugueseLibraryRepository,
            WordRepository wordRepository
    ) {
        this.portugueseModelRepository = portugueseModelRepository;
        this.portugueseLibraryRepository = portugueseLibraryRepository;
        this.wordRepository = wordRepository;
    }

    public PortugueseModel findById(Long id) {
        return portugueseModelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PortugueseModel not found"));
    }

    public PortugueseModel saveModel(PortugueseModel model){
        return portugueseModelRepository.save(model);
    }

    // ✅ New: Create model using DTO
    public PortugueseModel createModelFromDTO(PortugueseModelRequestDTO dto) {
        PortugueseLibrary library = portugueseLibraryRepository.findById(dto.getPortugueseLibraryId())
                .orElseThrow(() -> new RuntimeException("Portuguese Library not found"));

        List<Word> words = wordRepository.findAllById(dto.getWordIds());

        PortugueseModel model = new PortugueseModel();
        model.setPortugueseLibrary(library);
        model.setVault(new ArrayList<>(words));
        model.setUserLevel(1);
        model.setUserStreak(0);

        return portugueseModelRepository.save(model);
    }

    public void updateUserLevel(Long id, int newLevel) {
        PortugueseModel model = findById(id);
        model.setUserLevel(newLevel);
        portugueseModelRepository.save(model);
    }

    public void increaseUserLevel(Long id){
        PortugueseModel model = findById(id);
        model.setUserLevel(model.getUserLevel() + 1);
        portugueseModelRepository.save(model);
    }

    public void updateUserStreak(Long id, int newStreak){
        PortugueseModel model = findById(id);
        model.setUserStreak(newStreak);
        portugueseModelRepository.save(model);
    }

    public void increaseUserStreak(Long id){
        PortugueseModel model = findById(id);
        model.setUserStreak(model.getUserStreak() + 1);
        portugueseModelRepository.save(model);
    }

    public void resetUserStreak(Long id){
        PortugueseModel model = findById(id);
        model.setUserStreak(0);
        portugueseModelRepository.save(model);
    }

    public List<Word> getUserVault(Long id){
        PortugueseModel model = findById(id);
        return model.getVault();
    }

    public int getLevel(Long id){
        return findById(id).getUserLevel();
    }

    public int getStreak(Long id){
        return findById(id).getUserStreak();
    }
}
