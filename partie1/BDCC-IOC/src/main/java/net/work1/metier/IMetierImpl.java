package net.work1.metier;

import net.work1.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("metier")
public class IMetierImpl implements IMetier {
   // @Autowired
   // @Qualifier("d2")
    private IDao dao;

    public IMetierImpl( @Qualifier("d") IDao dao) {
        this.dao = dao;
    }

//    public IMetierImpl() {
//    }

    @Override
    public double calcul() {
        double t = dao.getData();
        double res =t*12*Math.PI/2*Math.cos(t);
        return res;
    }
    //pour injecter dans l'attribue Dao un objet d'une class qui implemente l'interface IDao apres instantiation
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
