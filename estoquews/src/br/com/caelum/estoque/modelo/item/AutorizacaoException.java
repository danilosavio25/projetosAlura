package br.com.caelum.estoque.modelo.item;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault", messageName="AutorizacaoFault")
public class AutorizacaoException extends Exception {

	public AutorizacaoException(String msg) {
		super(msg);
	}

	public InfoFault getFaultInfo() {
	    return new InfoFault("Token invalido" , new Date());
	}
	
	
	/* public String getFaultInfo() {
	        return "Token invalido";
	   }*/
	
}
