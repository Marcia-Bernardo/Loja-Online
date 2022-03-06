package proj2lojaonline.DAL;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.EncomCliente;
import proj2lojaonline.DAL.LinhaEncclientePK;
import proj2lojaonline.DAL.Produto;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(LinhaEnccliente.class)
public class LinhaEnccliente_ { 

    public static volatile SingularAttribute<LinhaEnccliente, Integer> qtd;
    public static volatile SingularAttribute<LinhaEnccliente, EncomCliente> encomCliente;
    public static volatile SingularAttribute<LinhaEnccliente, Produto> produto;
    public static volatile SingularAttribute<LinhaEnccliente, LinhaEncclientePK> linhaEncclientePK;
    public static volatile SingularAttribute<LinhaEnccliente, BigDecimal> precounit;

}