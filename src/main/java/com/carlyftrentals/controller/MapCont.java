package com.carlyftrentals.controller;

import com.carlyftrentals.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/map")
public class MapCont extends Attributes {

    @GetMapping
    public String Map(Model model) {
        AddAttributes(model);
        return "map";
    }
}
