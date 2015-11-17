package br.com.caelum.leilao.testes;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;

public class LanceTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveReceberValor0(){
		Lance lance = new Lance(new Usuario("Jonas"), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveReceberValorMenor0(){
		Lance lance = new Lance(new Usuario("Jonas"), -1);
	}
}
