package com.barbas.www.controller;

import com.barbas.www.model.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Config config;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("logo", config.getLogo());
        model.addAttribute("copyright", config.getCopyright());
        model.addAttribute("pageCSS", config.getPageCSS());
        model.addAttribute("pageJS", config.getPageJS());
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", config.getName());
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", config.getName() + " - Sobre");
        return "about";
    }
}