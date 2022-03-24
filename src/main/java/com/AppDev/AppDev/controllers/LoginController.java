package com.AppDev.AppDev.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.AppDev.AppDev.models.Administrador;
import com.AppDev.AppDev.Repository.AdministradoresRepo;

public class LoginController {
	
	

	@GetMapping ("/login")
	public String home() {
		return "/login";
	}
	@PostMapping ("/home/index")
	public String home(Model model, Administrador admParam, String lembrar ) {
		Administrador adm = ((AdministradoresRepo) this.repo).Login(admParam.getEmail(),admParam.getSenha());
		
		if(adm != null) {
			return "redirect:/home/index";
		}
		return "home/index";
	}
	
}
