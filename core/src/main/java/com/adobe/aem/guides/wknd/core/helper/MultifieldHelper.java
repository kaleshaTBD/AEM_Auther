package com.adobe.aem.guides.wknd.core.helper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;


public class MultifieldHelper {
    
    private String countryname;
    private String countryimage;
    private String countrycode;
    private String countrysummary;
    private List<NastedHalper> states;
    public MultifieldHelper(Resource resource){
    
            if(StringUtils.isNotBlank(resource.getValueMap().get("countryname", String.class))) {
                this.countryname = resource.getValueMap().get("countryname", String.class);
            }
            if(StringUtils.isNotBlank(resource.getValueMap().get("countryimage", String.class))) {
                this.countryimage=resource.getValueMap().get("countryimage",String.class);
            }
            if(StringUtils.isNotBlank(resource.getValueMap().get("countrysummary", String.class))) {
                this.countrysummary=resource.getValueMap().get("countrysummary",String.class);
            }
            if(resource.getValueMap().get("countrycode",String.class)!=null) {
                this.countrycode=resource.getValueMap().get("countrycode",String.class);
            }
           
    }

    public String getCountryname() {
        return countryname;
    }

    public String getCountryimage() {
        return countryimage;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setStates(List<NastedHalper> states) {
        this.states = states;
    }

    public List<NastedHalper> getStates() {
        return states;
    }

    public String getCountrysummary() {
        return countrysummary;
    }
   
}
