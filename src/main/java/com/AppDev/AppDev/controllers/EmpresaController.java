package com.AppDev.AppDev.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.AppDev.AppDev.models.Empresa;
import com.AppDev.AppDev.Repository.EmpresaRepository;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaRepository er;

	// GET que chama o FORM que cadastra empresa
	@RequestMapping("/cadastrarEmpresa")
	public String form() {
		return "empresa/form-empresa";
	}

	// POST que cadastra a empresa
	@RequestMapping(value = "/cadastrarEmpresa", method = RequestMethod.POST)
	public String form(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarEmpresa";
		}

		er.save(empresa);
		attributes.addFlashAttribute("mensagem", "Empresa cadastrada com sucesso!");
		return "redirect:/cadastrarEmpresa";
	}

	// GET que lista empresas
	@RequestMapping("/empresas")
	public ModelAndView listaEmpresas() {
		ModelAndView mv = new ModelAndView("empresa/lista-empresa");
		Iterable<Empresa> empresas = er.findAll();
		mv.addObject("empresas", empresas);
		return mv;
	}
	
	// GET que lista empresa
		@RequestMapping("/detalhes-empresa/{id}")
		public ModelAndView detalhesEmpresa(@PathVariable("id") long id) {
			Empresa empresa = er.findById(id);
			ModelAndView mv = new ModelAndView("empresa/detalhes-empresa");
			mv.addObject("empresas", empresa);
			
			return mv;
		}

	// GET que deleta empresa
	@RequestMapping("/deletarEmpresa")
	public String deletarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		er.delete(empresa);
		return "redirect:/empresas";

	}


	// Métodos que atualizam empresa
	// GET que chama o FORM de edição de empresa
	@RequestMapping("/editarEmpresa")
	public ModelAndView editarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		ModelAndView mv = new ModelAndView("empresa/update-empresa");
		mv.addObject("empresa", empresa);
		return mv;
	}
	
	// POST que atualiza a empresa
	@RequestMapping(value = "/editarEmpresa", method = RequestMethod.POST)
	public String updateFuncionario(@Valid Empresa empresa,  BindingResult result, RedirectAttributes attributes){
		
		er.save(empresa);
		attributes.addFlashAttribute("success", "Empresa alterado com sucesso!");
		return "redirect:/empresas";
		
	}
}

