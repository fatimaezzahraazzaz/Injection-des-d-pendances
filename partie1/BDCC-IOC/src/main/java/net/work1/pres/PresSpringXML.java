package net.work1.pres;

import net.work1.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class PresSpringXML {
    public static void main(String[] args) {
        ApplicationContext springContext =
                new ClassPathXmlApplicationContext("config.xml");
        IMetier metier =  springContext.getBean(IMetier.class);
        System.out.println("Res="+metier.calcul());
    }
}
