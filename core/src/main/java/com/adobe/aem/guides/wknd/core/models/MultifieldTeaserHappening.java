package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import com.adobe.aem.guides.wknd.core.helper.TajTeaserHappening;

import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldTeaserHappening {

    @Inject
    List<TajTeaserHappening> teaserHappenings;

    public List<TajTeaserHappening> getTeaserHappenings() {
        return teaserHappenings;
    }

    public void setTeaserHappenings(List<TajTeaserHappening> teaserHappenings) {
        this.teaserHappenings = teaserHappenings;
    }

    
    
}


