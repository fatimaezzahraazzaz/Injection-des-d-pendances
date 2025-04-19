package net.mini_projet.model;

import net.mini_projet.annotations.Component;

@Component(id = "serviceC")
public class ServiceC {
    private ServiceA serviceA;
    public ServiceC() {}
    // Injection par constructeur
    public ServiceC(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public void doSomething() {
        serviceA.execute();
    }
}

