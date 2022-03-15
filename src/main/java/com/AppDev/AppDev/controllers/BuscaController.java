package com.AppDev.AppDev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppDev.AppDev.Repository.ClienteRepository;
import com.AppDev.AppDev.Repository.EmpresaRepository;
import com.AppDev.AppDev.Repository.PacoteRepository;


@Controller
public class BuscaController {
	

	@Autowired
	private PacoteRepository pr;
	
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private EmpresaRepository er;
	
	//GET
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	//POST
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
					
		if(nome.equals("nomecliente")) {
			mv.addObject("cliente", cr.findByNomeCliente(buscar));
					
			
		}else if(nome.equals("titulopacote")) {
			mv.addObject("pacotes", pr.findByNome(buscar)); //estava findByNomesVaga
			
	
			
		}else {
			mv.addObject("clientes",cr.findByNomeCliente(buscar));
			mv.addObject("pacotes", pr.findByNome(buscar));//estava findByNomesVaga
			mv.addObject("empresa", er.findByNome(buscar));//estava findByNomeEmpresa
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;
	}

}
