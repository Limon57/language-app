package com.example.languageapp.repository;

import com.example.languageapp.model.PortugueseLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortugueseLibraryRepository extends JpaRepository<PortugueseLibrary, Long> {
}
