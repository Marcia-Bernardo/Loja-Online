package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.EncomCliente;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Transportadora.class)
public class Transportadora_ { 

    public static volatile SingularAttribute<Transportadora, String> telefone;
    public static volatile SingularAttribute<Transportadora, Short> ativo;
    public static volatile SingularAttribute<Transportadora, Codpostal> cpostal;
    public static volatile SingularAttribute<Transportadora, String> nome;
    public static volatile SingularAttribute<Transportadora, String> nipc;
    public static volatile SingularAttribute<Transportadora, Integer> codtransp;
    public static volatile SingularAttribute<Transportadora, String> email;
    public static volatile ListAttribute<Transportadora, EncomCliente> encomClienteList;
    public static volatile SingularAttribute<Transportadora, String> morada;

}