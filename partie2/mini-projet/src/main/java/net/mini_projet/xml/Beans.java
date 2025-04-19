package net.mini_projet.xml;


import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "beans")
@XmlAccessorType(XmlAccessType.FIELD)
public class Beans {

    @XmlElement(name = "bean")
    public List<Bean> beans;
}

