package com.carlyftrentals.repo;

import com.carlyftrentals.model.Autos;
import com.carlyftrentals.model.enums.Brand;
import com.carlyftrentals.model.enums.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutosRepo extends JpaRepository<Autos, Long> {
    List<Autos> findAllByDescription_BrandAndDescription_ColorOrderByFreeDesc(Brand brand,  Color color);
    List<Autos> findAllByOrderByFreeDesc();
}
