package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;

	@Autowired
	public ContaController(ContaDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/form")
	public String form(){
		return "conta/formulario";
	}
	
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result){
		
		if(result.hasFieldErrors("descricao")){
			return "conta/formulario";
		}
		
		System.out.println("Conta adicionada é: " + conta.getDescricao());
		dao.adiciona(conta);
		return "conta/conta-adicionada";
	}
	
	@RequestMapping("/removeConta")
	public String removeConta(Conta conta){
		dao.remove(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		List<Conta> contas = dao.lista();
	
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", contas);
	
		return mv;
	}
	
	@RequestMapping("/listaContas2")
	public String lista(Model mv) {
	  List<Conta> contas = dao.lista();

	  mv.addAttribute("contas", contas);
	  return "conta/lista";
	}
	
	@RequestMapping("/pagaConta")
	public void finaliza(Long id, HttpServletResponse response) {
	  dao.paga(id);
	  response.setStatus(200);
	}
	
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model model) {
	  model.addAttribute("conta", dao.buscaPorId(id));
	  return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
	  dao.altera(conta);
	  return "redirect:listaContas";
	}
	
}