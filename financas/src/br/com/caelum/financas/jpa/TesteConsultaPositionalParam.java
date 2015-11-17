package br.com.caelum.financas.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaPositionalParam {

    public static void main(String[] args) {

        EntityManager manager = new JPAUtil().getEntityManager();

        Conta conta = new Conta();
        conta.setId(1);

        Query query = manager
                .createQuery("select m from Movimentacao m where m.conta=?1"
                        + " and m.tipoMovimentacao=?2");
        
        query.setParameter(1, conta);
        query.setParameter(2, TipoMovimentacao.ENTRADA);
        
        List<Movimentacao> movimentacoes = query.getResultList();
        
        for(Movimentacao m: movimentacoes){
        	System.out.println(m.getDescricao());
        	System.out.println(m.getTipoMovimentacao());
        }
        
        manager.close();

    }
}