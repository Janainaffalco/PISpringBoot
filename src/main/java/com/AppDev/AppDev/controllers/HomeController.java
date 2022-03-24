package com.AppDev.AppDev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    @GetMapping("/") // esse direcionamento vai retornar a rota principal
    public String index(Model model){

        model.addAttribute("nome", "Janaina");

        return "home/index";
        
    }
}
