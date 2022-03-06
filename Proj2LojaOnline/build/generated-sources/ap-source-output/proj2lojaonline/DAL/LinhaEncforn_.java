package proj2lojaonline.DAL;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.EncomForn;
import proj2lojaonline.DAL.LinhaEncfornPK;
import proj2lojaonline.DAL.Produto;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(LinhaEncforn.class)
public class LinhaEncforn_ { 

    public static volatile SingularAttribute<LinhaEncforn, Integer> qtd;
    public static volatile SingularAttribute<LinhaEncforn, Produto> produto;
    public static volatile SingularAttribute<LinhaEncforn, EncomForn> encomForn;
    public static volatile SingularAttribute<LinhaEncforn, LinhaEncfornPK> linhaEncfornPK;
    public static volatile SingularAttribute<LinhaEncforn, BigDecimal> precounit;

}