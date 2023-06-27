package com.adobe.aem.guides.wknd.core.servlets;
/* */
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

@Component(service = Servlet.class, property = {
    "sling.servlet.methods=" + HttpConstants.METHOD_GET,
    "sling.servlet.paths=" + "/bin/example"
})
@ServiceDescription("Simple Path Based GET Servlet")
public class SimplePathServletExample extends SlingSafeMethodsServlet{
    
    private static final Logger log = LoggerFactory.getLogger(SimplePathServletExample.class);

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JsonObject jo = new JsonObject();
        jo.addProperty("property1", "value1");
        log.info("*******Path Servlet Example******"+jo.toString());
        response.getWriter().write(jo.toString());
        response.setStatus(SlingHttpServletResponse.SC_OK);
    }
}
