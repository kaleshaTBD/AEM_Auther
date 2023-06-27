package com.adobe.aem.guides.wknd.core.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.config.NodeCreationServiceConfig;
import com.adobe.aem.guides.wknd.core.service.NodeCreationService;

import java.util.Collections;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.Session;

@Component(service = NodeCreationService.class, immediate = true)
@Designate(ocd = NodeCreationServiceConfig.class)
public class NodeCreationServiceImpl implements NodeCreationService{

    private static final Logger log = LoggerFactory.getLogger(NodeCreationServiceImpl.class);
    private String parentPath;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;
 
    @Activate
    protected void activate(NodeCreationServiceConfig config) {
        log.info("Inside Active annontation");
        parentPath = config.parentPath();
    }

    @Override
    public String createNode(String nodeName, String nodeType, Map<String, Object> properties){
        ResourceResolver resourceResolver = null;
        try {
            log.info("Parent Path is: "+parentPath);
            log.info(nodeName,nodeType,properties.toString());
            Map<String, Object> authInfo = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "nodeCreateService");
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(authInfo);
            Session session = resourceResolver.adaptTo(Session.class); 
            Node contentNode = session.getNode(parentPath);
            Node afterAdd=contentNode.addNode(nodeName);
            afterAdd.setPrimaryType(nodeType);
            
            for (Map.Entry<String,Object> entry : properties.entrySet()) //using map.entrySet() for iteration  
                {  
                    afterAdd.setProperty( entry.getKey(), String.valueOf(entry.getValue()));
                }   
            
            session.save();
           String aa= afterAdd.getIdentifier();
           if(StringUtils.isEmpty(aa))
           {
                return "Service called but node not created";
           }
           else{
            return aa;
           }
           
        }
        catch(Exception e)
        {
            return e.getMessage();
        }finally {
            if (resourceResolver != null && resourceResolver.isLive()) {
                resourceResolver.close();
            }
        }
    }

    public Object getParentPath() {
        return parentPath;
    }

    @Override
    public void setResourceResolverFactory(ResourceResolverFactory resourceResolverFactory) {
       this.resourceResolverFactory = resourceResolverFactory;
    }

    @Override
    public void setParentPath(String parentPath) {
        this.parentPath=parentPath;
    }
}
