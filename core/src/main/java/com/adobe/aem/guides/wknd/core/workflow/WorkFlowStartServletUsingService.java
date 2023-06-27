package com.adobe.aem.guides.wknd.core.workflow;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.model.WorkflowNode;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@Component(service = Servlet.class, 
property={
    "sling.servlet.methods=" + HttpConstants.METHOD_POST,
    "sling.servlet.paths=" + "/bin/startVersionWorkflow"})
@ServiceDescription("Servlet can Start Vesrion Workflow")
public class WorkFlowStartServletUsingService extends SlingAllMethodsServlet{
    
   // private static final Logger log = LoggerFactory.getLogger(WorkFlowStartServletUsingService.class);
   
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException
    {
        try {
        final ResourceResolver resourceResolver=request.getResourceResolver();
        WorkflowSession session=resourceResolver.adaptTo(WorkflowSession.class);
       
        WorkflowModel model=session.createNewModel("ust-workflow-model");
        model.setTitle("ust-workflow-model");

        WorkflowNode versionNode = model.createNode();
        versionNode.setTitle("Create Page Version");
        versionNode.setType("PROCESS");
        versionNode.setDescription("A process to create a Page version for either a page or an asset.");
       
        WorkflowData data=session.newWorkflowData("JCR_PATH", "/content/wknd/us/en");
       
        Workflow aa= session.startWorkflow(model, data);
        String status="";
        if(aa==null)
        {
            status="Servlet called but workflow not yet started";
        }
        else{
            status=aa.getState();
        }    

        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(status);
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
    }
    
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        final ResourceResolver resourceResolver=request.getResourceResolver();
        try{
        String modelPath = request.getParameter("modelPath");
        String pagePath = request.getParameter("pagePath");
       
        WorkflowSession session=resourceResolver.adaptTo(WorkflowSession.class);
        WorkflowModel mode=session.getModel(modelPath);
        WorkflowData data=session.newWorkflowData("JCR_PATH", pagePath);
        
        Workflow aa= session.startWorkflow(mode, data);
        String status="";
        if(aa==null)
        {
            status="Servlet called but workflow not yet started";
        }
        else{
            status=aa.getState();
        }        
            
        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(status);
    } catch (WorkflowException e) {
        e.printStackTrace();
    }
     }
}
