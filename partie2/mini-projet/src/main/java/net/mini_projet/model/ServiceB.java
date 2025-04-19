package net.mini_projet.model;

import net.mini_projet.annotations.Component;
import net.mini_projet.annotations.Inject;


@Component(id = "serviceB")
public class ServiceB {

    @Inject
    private ServiceA serviceA;

    // Injection par setter
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
    public void doSomething() {
        serviceA.execute();
    }
}
