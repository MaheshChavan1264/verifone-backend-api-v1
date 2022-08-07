package com.example.verifone.repositories;

import com.example.verifone.entities.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimCardRepository extends JpaRepository<SimCard, Long> {
}
