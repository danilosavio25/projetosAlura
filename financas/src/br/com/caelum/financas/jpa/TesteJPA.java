package br.com.caelum.financas.jpa;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

    public static void main(String[] args) {

    	double inicio = System.currentTimeMillis();

    	
        Conta conta = new Conta();
        conta.setTitular("Maria dos Santos");
        conta.setBanco("Caixa");
        conta.setAgencia("043");
        conta.setNumero("54321");

        EntityManager em = new JPAUtil().getEntityManager();
        
        em.getTransaction().begin();

        em.persist(conta);

        em.getTransaction().commit();
        em.close();
        
        double fim = System.currentTimeMillis();
        System.out.println("Executado em: " + (fim - inicio)/1000 + "s");
    }
}