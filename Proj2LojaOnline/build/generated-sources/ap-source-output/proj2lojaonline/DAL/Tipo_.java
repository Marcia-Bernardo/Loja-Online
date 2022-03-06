package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Produto;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Tipo.class)
public class Tipo_ { 

    public static volatile SingularAttribute<Tipo, Integer> codtipo;
    public static volatile ListAttribute<Tipo, Produto> produtoList;
    public static volatile SingularAttribute<Tipo, String> nome;

}