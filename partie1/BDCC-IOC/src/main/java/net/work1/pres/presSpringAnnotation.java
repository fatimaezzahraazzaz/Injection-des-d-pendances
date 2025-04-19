package net.work1.pres;

import net.work1.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class presSpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("net.work1");
        IMetier metier = (IMetier) applicationContext.getBean(IMetier.class);
        System.out.println("Res"+metier.calcul());
    }
}
