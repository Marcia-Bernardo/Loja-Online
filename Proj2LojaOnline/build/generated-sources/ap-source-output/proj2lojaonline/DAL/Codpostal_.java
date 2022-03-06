package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Cliente;
import proj2lojaonline.DAL.Fornecedor;
import proj2lojaonline.DAL.MoradaDeEntrega;
import proj2lojaonline.DAL.Transportadora;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T22:35:02")
@StaticMetamodel(Codpostal.class)
public class Codpostal_ { 

    public static volatile ListAttribute<Codpostal, Cliente> clienteList;
    public static volatile ListAttribute<Codpostal, Transportadora> transportadoraList;
    public static volatile SingularAttribute<Codpostal, String> localizacao;
    public static volatile SingularAttribute<Codpostal, String> cpostal;
    public static volatile ListAttribute<Codpostal, Fornecedor> fornecedorList;
    public static volatile ListAttribute<Codpostal, MoradaDeEntrega> moradaDeEntregaList;

}