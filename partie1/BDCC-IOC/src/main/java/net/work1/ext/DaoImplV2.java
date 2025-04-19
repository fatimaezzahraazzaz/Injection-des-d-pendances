package net.work1.ext;

import net.work1.dao.IDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("d2")
public class DaoImplV2 implements IDao {
    @Override
    public double getData(){
        System.out.println("Version capteur");
        double t=12;
        return t;

    }
}
