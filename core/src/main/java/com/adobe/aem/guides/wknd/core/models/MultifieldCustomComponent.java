package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json")
public class MultifieldCustomComponent{
    
    @Inject
    List<Contries> contries;

    public List<Contries> getContries() {
        return contries;
    }

    public void setContries(List<Contries> contries) {
        this.contries = contries;
    }

    
}
