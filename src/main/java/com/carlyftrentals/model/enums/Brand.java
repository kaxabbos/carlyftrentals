package com.carlyftrentals.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Brand {
    MERCEDES("Mercedes"),
    BMV("BMV"),
    AUDI("Audi"),
    VOLKSWAGEN("Volkswagen"),
    SKODA("Skoda");
    private final String name;
}

