package net.work1.pres;

import net.work1.dao.IDao;
import net.work1.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws Exception{
       // FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
        Scanner sc = new Scanner(new File("config.txt"));
        String daoClassName =sc.nextLine();
        Class cDoa =Class.forName(daoClassName);
        IDao d = (IDao)cDoa.newInstance();
        //System.out.println(d.getData());

        String metierClassName =sc.nextLine();
        Class cMetier =Class.forName(metierClassName);
        IMetier metier =(IMetier) cMetier.getConstructor(IDao.class).newInstance(d);
        //IMetier metier =(IMetier) cMetier.getConstructor().newInstance();
        //Method  setDao =cMetier.getDeclaredMethod("setDao", IDao.class);
        //setDao.invoke(metier,d);
        System.out.println("Res="+metier.calcul());

    }
}
