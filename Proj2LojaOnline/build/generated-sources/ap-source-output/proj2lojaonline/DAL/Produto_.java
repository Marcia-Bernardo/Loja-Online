package proj2lojaonline.DAL;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Cor;
import proj2lojaonline.DAL.Genero;
import proj2lojaonline.DAL.LinhaEnccliente;
import proj2lojaonline.DAL.LinhaEncforn;
import proj2lojaonline.DAL.Prodprom;
import proj2lojaonline.DAL.Tamanho;
import proj2lojaonline.DAL.Tipo;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile ListAttribute<Produto, LinhaEnccliente> linhaEncclienteList;
    public static volatile SingularAttribute<Produto, Short> alarmestock;
    public static volatile SingularAttribute<Produto, Short> ativo;
    public static volatile SingularAttribute<Produto, Genero> codgenero;
    public static volatile SingularAttribute<Produto, Tipo> codtipo;
    public static volatile SingularAttribute<Produto, Integer> codprod;
    public static volatile SingularAttribute<Produto, String> nome;
    public static volatile ListAttribute<Produto, Prodprom> prodpromList;
    public static volatile SingularAttribute<Produto, String> descricao;
    public static volatile SingularAttribute<Produto, Integer> qtdstock;
    public static volatile SingularAttribute<Produto, Tamanho> codtamanho;
    public static volatile SingularAttribute<Produto, Cor> codcor;
    public static volatile SingularAttribute<Produto, BigDecimal> precoatualvenda;
    public static volatile ListAttribute<Produto, LinhaEncforn> linhaEncfornList;
    public static volatile SingularAttribute<Produto, BigDecimal> precovenda;

}