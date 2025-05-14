package com.example.languageapp.repository;

import com.example.languageapp.model.PortugueseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PortugueseModelRepository extends JpaRepository<PortugueseModel, Long> {


}
