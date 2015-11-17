package br.com.caelum.financas.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaFuncaoMax {

	public static void main(String[] args) {
		
		 EntityManager manager = new JPAUtil().getEntityManager();

	        Conta conta = manager.find(Conta.class, 2); //id deve existir
		
	        TypedQuery<BigDecimal> query= manager.createQuery("select max(m.valor) from Movimentacao m where m.conta = :pConta", BigDecimal.class);
		
			query.setParameter("pConta", conta);
		    BigDecimal quantidade =  query.getSingleResult();
		    
		    System.out.println("Max: " + quantidade);
	}

}
