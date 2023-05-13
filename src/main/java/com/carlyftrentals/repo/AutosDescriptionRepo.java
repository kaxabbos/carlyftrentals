package com.carlyftrentals.repo;

import com.carlyftrentals.model.AutosDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutosDescriptionRepo extends JpaRepository<AutosDescription, Long> {
}
