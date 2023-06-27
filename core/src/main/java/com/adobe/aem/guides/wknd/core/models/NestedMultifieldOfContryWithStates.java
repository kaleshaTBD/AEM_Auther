package com.adobe.aem.guides.wknd.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.helper.MultifieldHelper;
import com.adobe.aem.guides.wknd.core.helper.NastedHalper;

import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NestedMultifieldOfContryWithStates {
 
    private static final Logger LOG = LoggerFactory.getLogger(NestedMultifieldOfContryWithStates.class);
    
    @Inject
    Resource componentResource;
    
    public List<MultifieldHelper> getCountryDetailsWithNastedMultifield() {
        
        List<MultifieldHelper> countryDetailsNasted=new ArrayList<>();
        try {
            Resource countryDetailNasted=componentResource.getChild("countrydetailswithnastedmultifield"); //1
            if(countryDetailNasted!=null){
                for (Resource countryNasted : countryDetailNasted.getChildren()) { //1 - 2
                    MultifieldHelper multifieldHelper=new MultifieldHelper(countryNasted);
                    if(countryNasted.hasChildren()){
                        List<NastedHalper> countryNastedList=new ArrayList<>();
                        Resource nastedResource=countryNasted.getChild("states"); //2 - 3
                        for(Resource nasted : nastedResource.getChildren()){ //3 - 4
                            countryNastedList.add(new NastedHalper(nasted));
                        }
                        multifieldHelper.setStates(countryNastedList);
                    }
                    countryDetailsNasted.add(multifieldHelper);
                }
            }
        }catch (NullPointerException e){
            LOG.info("\n ERROR while getting Contry with state Details With Nasted Multifield {} ",e.getMessage());
        }
        LOG.info("\n SIZE Multifield {} ",countryDetailsNasted.size());
        return countryDetailsNasted;
    }

}
