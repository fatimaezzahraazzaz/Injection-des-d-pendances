package net.work1.pres;

import net.work1.dao.DaoImpl;
import net.work1.metier.IMetier;
import net.work1.metier.IMetierImpl;
import net.work1.ext.DaoImplV2;

public class Pres1 {

    public static void main(String[] args) {
        DaoImplV2 d =new DaoImplV2();
        IMetierImpl metier = new IMetierImpl(d);

        //metier.setDao(d);//Injection des dépendances via le setter
        System.out.println("Res" +metier.calcul());
    }
}
