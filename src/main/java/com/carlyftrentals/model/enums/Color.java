package com.carlyftrentals.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    WITH_LOGO("С логотипом"),
    NO_LOGO("Без логотипа");
    private final String name;
}

