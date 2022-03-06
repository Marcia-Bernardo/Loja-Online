package proj2lojaonline.DAL;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Fornecedor;
import proj2lojaonline.DAL.LinhaEncforn;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(EncomForn.class)
public class EncomForn_ { 

    public static volatile SingularAttribute<EncomForn, Integer> codencforn;
    public static volatile SingularAttribute<EncomForn, BigDecimal> valortot;
    public static volatile SingularAttribute<EncomForn, Short> estado;
    public static volatile SingularAttribute<EncomForn, Fornecedor> codforn;
    public static volatile SingularAttribute<EncomForn, Date> datacompra;
    public static volatile SingularAttribute<EncomForn, Date> dataentr;
    public static volatile ListAttribute<EncomForn, LinhaEncforn> linhaEncfornList;
    public static volatile SingularAttribute<EncomForn, Short> pago;

}