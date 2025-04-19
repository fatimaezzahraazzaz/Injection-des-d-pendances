package net.mini_projet;

import net.mini_projet.framework.AnnotationIoCContainer;
import net.mini_projet.model.ServiceA;
import net.mini_projet.model.ServiceB;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationIoCContainer container = new AnnotationIoCContainer();

        container.loadComponents(List.of(ServiceA.class, ServiceB.class));

        ServiceB serviceB = (ServiceB) container.getBean("serviceB");
        serviceB.doSomething();
    }
}
