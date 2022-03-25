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

import com.AppDev.AppDev.Repository.ClienteRepository;
import com.AppDev.AppDev.Repository.PacoteRepository;
import com.AppDev.AppDev.models.Cliente;
import com.AppDev.AppDev.models.Pacote;

@Controller
public class PacoteController {

	@Autowired
	private PacoteRepository pr;
	
	@Autowired
	private ClienteRepository cr;

	// cadastra pacote
	@RequestMapping(value = "/cadastrarPacote", method = RequestMethod.GET)
	public String form() {

		return "pacote/formPacote";

	}

	@RequestMapping(value = "/cadastrarPacote", method = RequestMethod.POST)
	public String form(@Valid Pacote pacote, BindingResult result, RedirectAttributes atributes) {

		if (result.hasErrors()) {
			atributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarPacote";
		}

		pr.save(pacote);
		atributes.addFlashAttribute("mensagem", "Pacote Cadastrado com Sucesso");
		return "redirect:/cadastrarPacote";

	}
	// listar PAcote

	@RequestMapping("/pacotes")
	public ModelAndView listaPacote() {
		ModelAndView mv = new ModelAndView("pacote/listapacote");
		Iterable<Pacote> pacotes = pr.findAll();
		mv.addObject("pacotes", pacotes);
		return mv;
	}
	// LISTAR CLIENTES

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesPacote(@PathVariable("codigo") long codigo) {
		Pacote pacote = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("pacote/detalhesPacote");
		mv.addObject("pacote", pacote);

		Iterable<Cliente> clientes = cr.findByPacote(pacote);
		mv.addObject("clientes", clientes);

		return mv;
	}
	// deleta Pacote

	@RequestMapping("/deletarPacote")
	public String deletarPacote(long codigo) {
		Pacote pacote = pr.findByCodigo(codigo);
		pr.delete(pacote);
		return "redirect:/pacotes";
	}

	public String detalhesPacotePost(@PathVariable("codigo") long codigo, @Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/{codigo}";
		}
		
		//Teste de concistência CPF duplicado
		if(cr.findByCpf(cliente.getCpf())!=null) {
			attributes.addFlashAttribute("mensagem erro", "CPF Duplicado");
			return "redirect:/{codigo}";
		}
		
		Pacote pacote = pr.findByCodigo(codigo);
		cliente.setPacote(pacote);
		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente Adicionado com Sucesso!");
		return "redirect:/{codigo}";
	}
	
	//deletar cliente pelo CPF
	
	@RequestMapping("/deletarCliente")
	public String deletarCliente(String cpf) {
		Cliente cliente = cr.findByCpf(cpf);
		Pacote pacote = cliente.getPacote();
		String codigo = "" + pacote.getCodigo();
		
		cr.delete(cliente);
		
		return "redirect:/" + codigo; 
		
	}
	
	//método de atualizar  pacote
	//formulário de edição de pacote
	
	@RequestMapping(value = "/editar-pacote", method = RequestMethod.GET)
	public ModelAndView editarPacote(long codigo) {
		Pacote pacote = pr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("pacote/update-pacote");
		mv.addObject("pacote", pacote);
		return mv;
	}
	
	//update do pacote
	
	@RequestMapping(value = "/editar-pacote", method = RequestMethod.POST)
	public String updatePacote (@Valid Pacote pacote, BindingResult result, RedirectAttributes atributes) {
		pr.save(pacote);
		atributes.addFlashAttribute("Sucess", "Pacote alterado com Sucesso!");

		long codigoLong = pacote.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
		
	}
	
	
	
}
