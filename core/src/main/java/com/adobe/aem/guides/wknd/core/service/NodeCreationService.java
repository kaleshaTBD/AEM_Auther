package com.adobe.aem.guides.wknd.core.service;

import java.util.Map;

import org.apache.sling.api.resource.ResourceResolverFactory;

public interface NodeCreationService {
    
  public String createNode(String nodeName, String nodeType, Map<String, Object> properties);

public void setResourceResolverFactory(ResourceResolverFactory resourceResolverFactory);

public void setParentPath(String parentPath);
}
