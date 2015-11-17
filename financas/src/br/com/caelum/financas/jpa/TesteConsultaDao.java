package br.com.caelum.financas.jpa;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaDao {

	public static void main(String[] args) {
		

		 EntityManager manager = new JPAUtil().getEntityManager();
		    Conta conta = manager.find(Conta.class, 2);//id 2 deve existir no banco
		
		 MovimentacaoDao dao = new MovimentacaoDao(manager);
		    Double media = dao.mediaDaConta(conta);
		    
		    System.out.println(media);
	}

}
