package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.service.NodeCreationService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import org.apache.sling.api.servlets.HttpConstants;
import java.util.Map;

@Component(service = Servlet.class, property = {
    "sling.servlet.methods=" + HttpConstants.METHOD_POST,
    "sling.servlet.paths=" + "/bin/createNode"
})
@ServiceDescription("Node Creation Servlet")
public class CreateNodeServlet extends SlingAllMethodsServlet {
   
    private static final Logger log = LoggerFactory.getLogger(CreateNodeServlet.class);
   
    @Reference 
    NodeCreationService nodeCreationService;

    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
       
        String nodeName = request.getParameter("nodeName");
        String nodeType = request.getParameter("nodeType");

        Map<String, Object> properties = new HashMap<>();
        Enumeration<String> keys = request.getParameterNames();
		   while (keys.hasMoreElements() )
		   {
              String key = (String)keys.nextElement();
              if(!key.equals("nodeName") && !key.equals("nodeType"))
              {
                String value = request.getParameter(key);
                properties.put(key, value);
              }
		   }
        
        log.info(nodeName);
        log.info(nodeType);
        
        String responseOfNode=nodeCreationService.createNode(nodeName, nodeType, properties);
        
        response.setStatus(200);
        response.setContentType("application/json");
        if(StringUtils.isEmpty(responseOfNode))
        {
          response.getWriter().write("Servlet called but node is not created");
        }
        else{
          response.getWriter().write(responseOfNode);
        }
        
       
     }
}

