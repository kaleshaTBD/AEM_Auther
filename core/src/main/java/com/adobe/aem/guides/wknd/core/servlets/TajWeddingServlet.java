package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


@Component(service = Servlet.class, property = {
    "sling.servlet.methods=" + HttpConstants.METHOD_GET,
    "sling.servlet.paths=" + "/bin/taj-weddings"
})
@ServiceDescription("Taj Our Hotels Servlet")
public class TajWeddingServlet extends SlingSafeMethodsServlet{
    
 //   private static final Logger log = LoggerFactory.getLogger(TajHolidayServlet.class);

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/content/wknd/us/taj-wedding.html");
    }
}

