package proj2lojaonline.DAL;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.LinhaEnccliente;
import proj2lojaonline.DAL.MoradaDeEntrega;
import proj2lojaonline.DAL.Transportadora;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(EncomCliente.class)
public class EncomCliente_ { 

    public static volatile ListAttribute<EncomCliente, LinhaEnccliente> linhaEncclienteList;
    public static volatile SingularAttribute<EncomCliente, Integer> codenccliente;
    public static volatile SingularAttribute<EncomCliente, BigDecimal> valortot;
    public static volatile SingularAttribute<EncomCliente, Cliente> codcliente;
    public static volatile SingularAttribute<EncomCliente, Short> estado;
    public static volatile SingularAttribute<EncomCliente, BigDecimal> valorencom;
    public static volatile SingularAttribute<EncomCliente, Date> datacompra;
    public static volatile SingularAttribute<EncomCliente, MoradaDeEntrega> codmoradaentr;
    public static volatile SingularAttribute<EncomCliente, Date> dataentr;
    public static volatile SingularAttribute<EncomCliente, BigDecimal> valortransporte;
    public static volatile SingularAttribute<EncomCliente, Short> pago;
    public static volatile SingularAttribute<EncomCliente, Transportadora> codtransp;

}