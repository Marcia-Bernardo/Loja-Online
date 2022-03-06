package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Produto;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Tamanho.class)
public class Tamanho_ { 

    public static volatile SingularAttribute<Tamanho, Integer> codtam;
    public static volatile ListAttribute<Tamanho, Produto> produtoList;
    public static volatile SingularAttribute<Tamanho, String> nome;

}