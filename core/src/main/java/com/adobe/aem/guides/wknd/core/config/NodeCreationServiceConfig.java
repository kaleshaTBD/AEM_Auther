package com.adobe.aem.guides.wknd.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Node Creation Service Configuration", description = "Configuration for Node Creation Service")
public @interface NodeCreationServiceConfig {
    @AttributeDefinition(name = "Parent Path", description = "Path to the parent node where new nodes will be created")
    String parentPath() default "/content/wknd/us/en/demoForServlet/jcr:content"; 
}
