package com.carlyftrentals.model;

import com.carlyftrentals.model.enums.Brand;
import com.carlyftrentals.model.enums.Classes;
import com.carlyftrentals.model.enums.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutosDescription {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private Classes classes;
    @Enumerated(EnumType.STRING)
    private Color color;
    private int average;
    private String description;

    public AutosDescription(Brand brand, Classes classes, Color color, String description) {
        this.brand = brand;
        this.classes = classes;
        this.color = color;
        this.average = 0;
        this.description = description;
    }
}
