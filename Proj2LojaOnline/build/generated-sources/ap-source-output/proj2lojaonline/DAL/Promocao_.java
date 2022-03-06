package proj2lojaonline.DAL;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import proj2lojaonline.DAL.Prodprom;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-20T20:25:26")
@StaticMetamodel(Promocao.class)
public class Promocao_ { 

    public static volatile SingularAttribute<Promocao, Date> datafim;
    public static volatile SingularAttribute<Promocao, Integer> codpromocao;
    public static volatile ListAttribute<Promocao, Prodprom> prodpromList;
    public static volatile SingularAttribute<Promocao, String> descricao;
    public static volatile SingularAttribute<Promocao, Date> dataini;

}