package com.carlyftrentals.controller.main;

import com.carlyftrentals.model.Stats;
import com.carlyftrentals.model.enums.Brand;
import com.carlyftrentals.model.enums.Classes;
import com.carlyftrentals.model.enums.Role;
import com.carlyftrentals.model.enums.Color;
import org.springframework.ui.Model;

import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("brands", Brand.values());
        model.addAttribute("classess", Classes.values());
        model.addAttribute("colors", Color.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesAuto(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("auto", autosRepo.getReferenceById(id));
    }

    protected void AddAttributesAutosMy(Model model) {
        AddAttributes(model);
        model.addAttribute("autos", getUser().getAutos());
    }

    protected void AddAttributesAutoAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesAutoEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("auto", autosRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("autos", autosRepo.findAllByOrderByFreeDesc());
    }

    protected void AddAttributesCatalogSearch(Model model, Brand brand,  Color color) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("bSelect", brand);
        model.addAttribute("tSelect", color);
        model.addAttribute("autos", autosRepo.findAllByDescription_BrandAndDescription_ColorOrderByFreeDesc(brand,  color));
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        List<Stats> stats = statsRepo.findAll();
        int income = stats.stream().reduce(0, (i, s) -> i + (s.getDays() * s.getAuto().getPrice()), Integer::sum);
        model.addAttribute("stats", stats);
        model.addAttribute("income", income);
    }
}
