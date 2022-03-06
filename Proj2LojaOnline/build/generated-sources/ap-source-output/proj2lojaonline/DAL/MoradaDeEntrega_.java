package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.EncomCliente;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(MoradaDeEntrega.class)
public class MoradaDeEntrega_ { 

    public static volatile SingularAttribute<MoradaDeEntrega, Codpostal> cpostal;
    public static volatile SingularAttribute<MoradaDeEntrega, String> nome;
    public static volatile SingularAttribute<MoradaDeEntrega, Integer> codmoradaentr;
    public static volatile ListAttribute<MoradaDeEntrega, EncomCliente> encomClienteList;
    public static volatile SingularAttribute<MoradaDeEntrega, String> morada;

}