package com.adobe.aem.guides.wknd.core.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.*;
import com.adobe.aem.guides.wknd.core.service.NodeCreationService;
//import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = NodeCreationComponent.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class NodeCreationComponent{

    final protected static String RESOURCE_TYPE="aem-guides-wknd/components/nodeCreationComponent";

    
    @ValueMapValue
    private String nodeName;

   
    @ValueMapValue
    private String nodeType;
    
    @OSGiService
    NodeCreationService service;

    @PostConstruct
        public void init() {
            Map<String, Object> properties = new HashMap<>();
            service.createNode(nodeName, nodeType, properties);
        }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    
}
