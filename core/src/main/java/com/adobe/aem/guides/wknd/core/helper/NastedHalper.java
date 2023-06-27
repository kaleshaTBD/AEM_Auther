package com.adobe.aem.guides.wknd.core.helper;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

public class NastedHalper {

    private String statename;

    private Date stateformationdate;
    
    private int statestrength;

    private String statesummary;

    public NastedHalper(Resource resource){
        if(StringUtils.isNotBlank(resource.getValueMap().get("statename", String.class))) {
            this.statename = resource.getValueMap().get("statename", String.class);
        }
        if(resource.getValueMap().get("statestrength", Integer.class)!=0) {
            this.statestrength = resource.getValueMap().get("statestrength", Integer.class);
        }
        if(resource.getValueMap().get("stateformationdate",Date.class)!=null){
            this.stateformationdate=resource.getValueMap().get("stateformationdate",Date.class);
        }
        if(StringUtils.isNotBlank(resource.getValueMap().get("statesummary", String.class))) {
            this.statesummary = resource.getValueMap().get("statesummary", String.class);
        }
    }

    public String getStatename() {
        return statename;
    }

    public Date getStateformationdate() {
        return stateformationdate;
    }

    public int getStatestrength() {
        return statestrength;
    }

    public String getStatesummary() {
        return statesummary;
    }

    
}
