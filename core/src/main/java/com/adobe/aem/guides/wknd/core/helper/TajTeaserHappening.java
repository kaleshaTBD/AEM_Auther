package com.adobe.aem.guides.wknd.core.helper;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TajTeaserHappening
{
    @ValueMapValue
    private String name;

    @ValueMapValue
    private String location;

    @ValueMapValue
    String pathImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public TajTeaserHappening(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TajTeaserHappening(String name, String location, String pathImage) {
        this.name = name;
        this.location = location;
        this.pathImage = pathImage;
    }

    
    
   

    
}
