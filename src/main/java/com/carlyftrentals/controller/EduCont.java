package com.carlyftrentals.controller;

import com.carlyftrentals.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edu")
public class EduCont extends Attributes {

    @GetMapping
    public String Edu(Model model) {
        AddAttributes(model);
        return "edu";
    }
}
