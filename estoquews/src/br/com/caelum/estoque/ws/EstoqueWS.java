package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import br.com.caelum.estoque.modelo.item.AutorizacaoException;
import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    
/*    @WebMethod(operationName="todosOsItens")
    @ResponseWrapper(localName="itens")
    @WebResult(name="item")
    @RequestWrapper(localName="listaItens")
    public List<Item> getItens(@WebParam(name="filtros") Filtros filtros) {
    	System.out.println("Chamando getItens() at all");
    	return dao.todosItens();
    }*/
    
 
    @WebMethod(operationName="todosOsItens")
    @WebResult(name="itens")
    public ListaItens getItens(@WebParam(name="filtros") Filtros filtros) {
    	   System.out.println("Chamando getItens() at all");
    	   List<Filtro> lista = filtros.getLista();
           List<Item> itensResultado = dao.todosItens(lista);
           return new ListaItens(itensResultado);
    }
    
    @WebMethod(operationName="CadastrarItem") 
    public Item cadastrarItem(@WebParam(name="tokenUsuario", header=true)TokenUsuario token, @WebParam(name="item") Item item) throws AutorizacaoException {
        System.out.println("Cadastrando " + item);
        
        if(!new TokenDao().ehValido(token)) {
            throw new AutorizacaoException("Autorizacao falhou");
        }
        
        new ItemValidador(item).validate();
        
        this.dao.cadastrar(item);
        return item;
    }
    
    // OneWay não espera resposta do WS
    @WebMethod(operationName="oneWayMethod")
    @Oneway
    public void oneWayMethod() { 
        //nao vai fazer parte do WSDL
    }
    
    
    @WebMethod(exclude=true)
    public void outroMetodo() { 
        //nao vai fazer parte do WSDL
    }
}
