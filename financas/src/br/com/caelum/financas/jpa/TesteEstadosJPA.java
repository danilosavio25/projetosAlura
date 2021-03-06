package br.com.caelum.financas.jpa;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteEstadosJPA {

    public static void main(String[] args) {

        EntityManager manager = new JPAUtil().getEntityManager();        

        manager.getTransaction().begin();
        
        Conta conta = manager.find(Conta.class, 1);
        
        System.out.println(conta.getTitular());
        
        manager.getTransaction().commit();
        
        // Alteração do titular da conta
        conta.setTitular("Caelum Ensino e Inovação");

        System.out.println(conta.getTitular());
        
        manager.getTransaction().begin();
        manager.merge(conta);
        manager.getTransaction().commit();
    }
}