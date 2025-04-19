package net.mini_projet;

import net.mini_projet.framework.XmlIoCContainer;
import net.mini_projet.model.ServiceB;
import net.mini_projet.model.ServiceC;


public class MainXml {
    public static void main(String[] args) throws Exception {
        XmlIoCContainer container = new XmlIoCContainer();
        container.loadConfig("beans.xml");

        // Tester l'injection par setter
        ServiceB serviceB = (ServiceB) container.getBean("serviceB");
        serviceB.doSomething();

        // Tester l'injection par constructeur
        ServiceC serviceC = (ServiceC) container.getBean("serviceC");
        serviceC.doSomething();
    }
}


