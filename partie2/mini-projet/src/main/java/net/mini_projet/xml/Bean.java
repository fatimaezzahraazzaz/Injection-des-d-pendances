package net.mini_projet.xml;



import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Bean {

    @XmlAttribute
    public String id;

    @XmlAttribute(name = "class")
    public String className;

    @XmlElement(name = "property")
    public Property[] properties;
}


