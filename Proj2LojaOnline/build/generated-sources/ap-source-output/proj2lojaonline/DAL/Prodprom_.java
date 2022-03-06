package proj2lojaonline.DAL;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.ProdpromPK;
import proj2lojaonline.DAL.Produto;
import proj2lojaonline.DAL.Promocao;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Prodprom.class)
public class Prodprom_ { 

    public static volatile SingularAttribute<Prodprom, BigDecimal> percdesc;
    public static volatile SingularAttribute<Prodprom, Produto> produto;
    public static volatile SingularAttribute<Prodprom, Promocao> promocao;
    public static volatile SingularAttribute<Prodprom, ProdpromPK> prodpromPK;

}