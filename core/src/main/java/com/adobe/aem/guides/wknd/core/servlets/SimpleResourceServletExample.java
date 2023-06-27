package com.adobe.aem.guides.wknd.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonObject;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="wknd/components/search",
        methods={HttpConstants.METHOD_GET, HttpConstants.METHOD_POST},
        selectors = "data",
        extensions = "html")
@ServiceDescription("Simple Resource Based Servlet")
public class SimpleResourceServletExample extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SimpleResourceServletExample.class);

    @Override
    public void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse response) throws ServletException, IOException {
                response.setContentType("application/json");
                JsonObject jo = new JsonObject();
                jo.addProperty("property1", "value1");
                log.info("##### Resource Servlet Example #####"+jo.toString());
                response.getWriter().write(jo.toString());
                response.setStatus(SlingHttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        
        StringBuffer sb=new StringBuffer();
        BufferedReader rb=request.getReader();
           
            String line;
            while((line=rb.readLine()) != null)
            {
                sb.append(line).append("\n");
            }
        
        log.info("Post Servlet "+ sb.toString());
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(sb.toString());
    }
}

