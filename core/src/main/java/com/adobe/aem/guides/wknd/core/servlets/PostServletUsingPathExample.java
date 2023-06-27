package com.adobe.aem.guides.wknd.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, 
property={
    "sling.servlet.methods=" + HttpConstants.METHOD_POST,
    "sling.servlet.paths=" + "/bin/post/servlet"})
@ServiceDescription("Servlet can Print Input Body JSON in Log file")
public class PostServletUsingPathExample extends SlingAllMethodsServlet{

    private static final Logger log = LoggerFactory.getLogger(PostServletUsingPathExample.class);

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
