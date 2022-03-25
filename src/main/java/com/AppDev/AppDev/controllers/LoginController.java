package com.AppDev.AppDev.controllers;


import com.AppDev.AppDev.Repository.AdministradoresRepo;
import com.AppDev.AppDev.models.Administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class LoginController {

    
    @Autowired
    private AdministradoresRepo repo;

    @GetMapping("/login") // esse direcionamento vai retornar a rota principal
    public String index (){
        return "login/index";
    }

@PostMapping ("/logar")
    public String logar(Model model, Administrador admParam, String lembrar){
        Administrador adm = this.repo.Login(admParam.getEmail(), admParam.getSenha());

        // aqui verifica se tiver cadastrado vai para index da home
        if(adm!= null){
            return "redirect:/";

        }   
        //aqui se não tiver cadastro página da erro e volta para login
        model.addAttribute("erro", "Usuário ou Senha inválidos");
        return "login/index";

    }
}
