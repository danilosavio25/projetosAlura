package br.com.caelum.estoque.cliente;

import br.com.caelum.estoque.ws.EstoqueWS;
import br.com.caelum.estoque.ws.EstoqueWSService;
import br.com.caelum.estoque.ws.Filtro;
import br.com.caelum.estoque.ws.Filtros;
import br.com.caelum.estoque.ws.ListaItens;
import br.com.caelum.estoque.ws.TipoItem;

public class TesteClienteSoap {

	public static void main(String[] args) {
			
		EstoqueWS cliente = new EstoqueWSService().getEstoqueWSPort();

		Filtro filtro = new Filtro();
		filtro.setNome("IPhone");
		filtro.setTipo(TipoItem.CELULAR);;

		Filtros filtros = new Filtros();
		filtros.getFiltro().add(filtro);

		ListaItens lista = cliente.todosOsItens(filtros);

		System.out.println("Resposta do serviço: " + lista);
	}

}
