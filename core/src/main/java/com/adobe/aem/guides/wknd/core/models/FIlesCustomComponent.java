package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Named;
import java.util.List;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FIlesCustomComponent {
   
    @ChildResource
    @Named("links")
    private List<Resource> links;

    public List<Resource> getLinks() {
        return links;
    }

    public void setLinks(List<Resource> links2) {
        this.links = links2;
    }

    
}
