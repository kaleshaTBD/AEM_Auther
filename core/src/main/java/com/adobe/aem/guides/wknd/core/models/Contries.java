package com.adobe.aem.guides.wknd.core.models;



import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Contries {

    //private static final Logger log = LoggerFactory.getLogger(Contries.class);
    
    @ValueMapValue
    private String name;

    @ValueMapValue
    private String code;

    @ValueMapValue
    String pathImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Contries(){}
    
    public Contries(String name, String code, String pathImage) {
        this.name = name;
        this.code = code;
        this.pathImage = pathImage;
    }

    
    
    
}
