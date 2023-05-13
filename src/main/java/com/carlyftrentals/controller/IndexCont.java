package com.carlyftrentals.controller;

import com.carlyftrentals.controller.main.Attributes;
import com.carlyftrentals.model.enums.Brand;
import com.carlyftrentals.model.enums.Classes;
import com.carlyftrentals.model.enums.Color;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam Brand brand,  @RequestParam Color color) {
        AddAttributesCatalogSearch(model, brand, color);
        return "catalog";
    }
}
