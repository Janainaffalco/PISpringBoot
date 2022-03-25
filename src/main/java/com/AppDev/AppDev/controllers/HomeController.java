package com.AppDev.AppDev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
    public class HomeController {
    
        
        @GetMapping("/")
        public String index(Model model, String nome) {
        model.addAttribute("nome", nome);
        return "home/index";
        }
}
