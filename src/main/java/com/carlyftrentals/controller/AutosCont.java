package com.carlyftrentals.controller;

import com.carlyftrentals.controller.main.Attributes;
import com.carlyftrentals.model.Autos;
import com.carlyftrentals.model.AutosDescription;
import com.carlyftrentals.model.Stats;
import com.carlyftrentals.model.Users;
import com.carlyftrentals.model.enums.Brand;
import com.carlyftrentals.model.enums.Classes;
import com.carlyftrentals.model.enums.Color;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/autos")
public class AutosCont extends Attributes {

    @GetMapping("/{id}")
    public String Auto(Model model, @PathVariable Long id) {
        AddAttributesAuto(model, id);
        return "auto";
    }

    @GetMapping("/add")
    public String AutoAdd(Model model) {
        AddAttributesAutoAdd(model);
        return "auto_add";
    }

    @GetMapping("/edit/{id}")
    public String AutoEdit(Model model, @PathVariable Long id) {
        AddAttributesAutoEdit(model, id);
        return "auto_edit";
    }

    @GetMapping("/delete/{id}")
    public String AutoDelete(@PathVariable Long id) {
        autosRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/my")
    public String AutoMy(Model model) {
        AddAttributesAutosMy(model);
        return "my_autos";
    }

    @GetMapping("/return/{id}")
    public String AutoReturn(@PathVariable Long id) {
        Users user = getUser();
        Autos auto = autosRepo.getReferenceById(id);
        auto.setFree(true);
        user.removeAuto(auto);
        usersRepo.save(user);
        return "redirect:/autos/my";
    }

    @PostMapping("/rent/{id}")
    public String AutoRent(@PathVariable Long id, @RequestParam int days) {
        Autos auto = autosRepo.getReferenceById(id);

        Users user = getUser();
        user.addAuto(auto);
        usersRepo.save(user);

        auto.setFree(false);
        auto.getStats().setDays(auto.getStats().getDays() + days);

        autosRepo.save(auto);

        return "redirect:/autos/{id}";
    }

    @PostMapping("/add")
    public String AutoAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam Brand brand, @RequestParam String description, @RequestParam Classes classes, @RequestParam Color color) {
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String[] result_photos;
                String result_screenshot;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_screenshot = "autos/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_screenshot));
                    result_photos[i] = result_screenshot;
                }
                Autos auto = new Autos(name, price, true, result_photos);
                auto.setStats(new Stats(auto));
                auto.setDescription(new AutosDescription(brand, classes, color, description));
                autosRepo.save(auto);
            } catch (Exception e) {
                AddAttributesAutoAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "auto_add";
            }
        } else {
            AddAttributesAutoAdd(model);
            model.addAttribute("message", "Ошибка, некорректный данные!");
            return "auto_add";
        }
        return "redirect:/autos/add";
    }

    @PostMapping("/edit/{id}")
    public String AutoEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam Brand brand, @RequestParam String description, @RequestParam Classes classes, @RequestParam Color color, @PathVariable Long id) {
        Autos auto = autosRepo.getReferenceById(id);
        String[] result_photos;
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String result_photo;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_photo = "autos/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_photo));
                    result_photos[i] = result_photo;
                }
                auto.setPhotos(result_photos);
            } catch (Exception e) {
                AddAttributesAutoAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "auto_edit";
            }
        }

        auto.setName(name);
        auto.setPrice(price);
        autosRepo.save(auto);

        AutosDescription autosDescription = auto.getDescription();
        autosDescription.setDescription(description);
        autosDescription.setClasses(classes);
        autosDescription.setBrand(brand);
        autosDescription.setColor(color);
        autosDescriptionRepo.save(autosDescription);

        return "redirect:/";
    }
}
