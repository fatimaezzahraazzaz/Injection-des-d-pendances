package net.mini_projet.model;



import net.mini_projet.annotations.Component;

@Component(id = "serviceA")
public class ServiceA {
    public void execute() {
        System.out.println("ServiceA is running.");
    }
}

