package com.carlyftrentals.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Classes {
    STANDARD("Стандарт"),
    PREMIUM("Премиум"),
    VIP("ВИП");
    private final String name;
}

