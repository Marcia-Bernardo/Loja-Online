package proj2lojaonline.DAL;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Codpostal;
import proj2lojaonline.DAL.EncomForn;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, String> telefone;
    public static volatile SingularAttribute<Fornecedor, Short> ativo;
    public static volatile SingularAttribute<Fornecedor, Codpostal> cpostal;
    public static volatile ListAttribute<Fornecedor, EncomForn> encomFornList;
    public static volatile SingularAttribute<Fornecedor, Integer> codforn;
    public static volatile SingularAttribute<Fornecedor, String> nome;
    public static volatile SingularAttribute<Fornecedor, String> nipc;
    public static volatile SingularAttribute<Fornecedor, String> email;
    public static volatile SingularAttribute<Fornecedor, String> morada;

}