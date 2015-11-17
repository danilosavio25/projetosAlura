package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao){
		
		if(leilao.getLances().size() == 0){
			throw new RuntimeException("Não há nenhum lance para esse leilão.");
		}
		
		
		List<Lance> lances = leilao.getLances();
		for(Lance lance: lances){
			if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
		}
		
		pegaOsMaioresNo(leilao);
	}
	
	public double media(Leilao leilao){
		List<Lance> lances = leilao.getLances();
		double soma = 0.0;
		for(Lance lance: lances){
			soma += lance.getValor();
		}
		
		if(lances.size() == 0.0){
			return 0.0;
		}
		
		return soma/lances.size();
		
	}
	
	public void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return 1;
                if(o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    public List<Lance> getTresMaiores() {
        return this.maiores;
    }
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}


	public void setMaiorDeTodos(double maiorDeTodos) {
		this.maiorDeTodos = maiorDeTodos;
	}


	public double getMenorLance() {
		return menorDeTodos;
	}


	public void setMenorDeTodos(double menorDeTodos) {
		this.menorDeTodos = menorDeTodos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(maiorDeTodos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((maiores == null) ? 0 : maiores.hashCode());
		temp = Double.doubleToLongBits(menorDeTodos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliador other = (Avaliador) obj;
		if (Double.doubleToLongBits(maiorDeTodos) != Double.doubleToLongBits(other.maiorDeTodos))
			return false;
		if (maiores == null) {
			if (other.maiores != null)
				return false;
		} else if (!maiores.equals(other.maiores))
			return false;
		if (Double.doubleToLongBits(menorDeTodos) != Double.doubleToLongBits(other.menorDeTodos))
			return false;
		return true;
	}

	
}
