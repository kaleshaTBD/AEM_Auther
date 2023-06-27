package com.adobe.aem.guides.wknd.core.helper;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TajTeaser
{
    @ValueMapValue
    private String name;

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

    public TajTeaser(){}
    
    public TajTeaser(String name, String pathImage) {
        this.name = name;
        this.pathImage = pathImage;
    }

    
}
