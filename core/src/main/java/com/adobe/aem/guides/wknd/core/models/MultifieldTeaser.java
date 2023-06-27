package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import com.adobe.aem.guides.wknd.core.helper.TajTeaser;

import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldTeaser {

    @Inject
    List<TajTeaser> teaser;

    public List<TajTeaser> getTeaser() {
        return teaser;
    }

    public void setTeaser(List<TajTeaser> teaser) {
        this.teaser = teaser;
    }

    
    
}


