package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.EncomCliente;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Integer> codcliente;
    public static volatile SingularAttribute<Cliente, String> telefone;
    public static volatile SingularAttribute<Cliente, Short> ativo;
    public static volatile SingularAttribute<Cliente, Codpostal> cpostal;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, String> nif;
    public static volatile SingularAttribute<Cliente, String> email;
    public static volatile ListAttribute<Cliente, EncomCliente> encomClienteList;
    public static volatile SingularAttribute<Cliente, String> morada;

}