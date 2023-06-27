package com.adobe.aem.guides.wknd.core.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.model.WorkflowNode;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class WorkFlowStartServletUsingServiceTest {
    
    AemContext context=new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
   
    private WorkFlowStartServletUsingService servlet = new WorkFlowStartServletUsingService();

    
    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private ResourceResolver resourceResolver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
   
    @Test
    void testDoPost() throws ServletException, IOException, WorkflowException {

        MockSlingHttpServletResponse response = context.response();
       
        when(request.getParameter("modelPath")).thenReturn("/var/workflow/models/kalesha-workflow-model");
        when(request.getParameter("pagePath")).thenReturn("/content/wknd/us/en");

        // Set up the resource resolver and workflow session
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        WorkflowSession workflowSession = mock(WorkflowSession.class);
        when(resourceResolver.adaptTo(WorkflowSession.class)).thenReturn(workflowSession);

        // Set up the workflow model and data
        WorkflowModel workflowModel = mock(WorkflowModel.class);
        when(workflowSession.getModel(request.getParameter("modelPath"))).thenReturn(workflowModel);
        WorkflowData workflowData = mock(WorkflowData.class);
        when(workflowSession.newWorkflowData("JCR_PATH", request.getParameter("pagePath"))).thenReturn(workflowData);
 
        // Create an instance of YourClass and invoke the doPost method
        servlet.doPost(request, response);

       assertEquals("Servlet called but workflow not yet started", response.getOutputAsString());
    }


    @Test
    public void testDoGet() throws IOException, WorkflowException {
        MockSlingHttpServletResponse response = context.response();

        // Set up the resource resolver and workflow session
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        WorkflowSession workflowSession = mock(WorkflowSession.class);
        when(resourceResolver.adaptTo(WorkflowSession.class)).thenReturn(workflowSession);

        // Set up the workflow model and data
        WorkflowModel workflowModel = mock(WorkflowModel.class);
        when(workflowSession.createNewModel("ust-workflow-model")).thenReturn(workflowModel);
       
        // Set up the workflow node
        WorkflowNode workflowNode = mock(WorkflowNode.class);
        when(workflowModel.createNode()).thenReturn(workflowNode);
       
        // Set up the workflow data
        WorkflowData workflowData = mock(WorkflowData.class);
        when(workflowSession.newWorkflowData("JCR_PATH", "/content/wknd/us/en")).thenReturn(workflowData);

       
        servlet.doGet(request, response);

        // Verify that the workflow session methods were called with the correct parameters
        verify(workflowSession).createNewModel("ust-workflow-model");
        verify(workflowModel).setTitle("ust-workflow-model");
        verify(workflowModel).createNode();
        verify(workflowNode).setTitle("Create Page Version");
        verify(workflowNode).setType("PROCESS");
        verify(workflowNode).setDescription("A process to create a Page version for either a page or an asset.");
        verify(workflowSession).newWorkflowData("JCR_PATH", "/content/wknd/us/en");
        verify(workflowSession).startWorkflow(workflowModel, workflowData);

        assertEquals("Servlet called but workflow not yet started", response.getOutputAsString());

    }

    @Test
    public void testDoPostWithWorkflowException() throws ServletException, IOException, WorkflowException {
       
        MockSlingHttpServletResponse response = context.response();
        WorkflowException workflowException = mock(WorkflowException.class);

        when(request.getParameter("modelPath")).thenReturn("/var/workflow/models/kalesha-workflow-model");
        when(request.getParameter("pagePath")).thenReturn("/content/wknd/us/en");

        // Set up the resource resolver and workflow session
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        WorkflowSession workflowSession = mock(WorkflowSession.class);
        when(resourceResolver.adaptTo(WorkflowSession.class)).thenReturn(workflowSession);

        // Set up the workflow model and data
        WorkflowModel workflowModel = mock(WorkflowModel.class);
        when(workflowSession.getModel(request.getParameter("modelPath"))).thenReturn(workflowModel);
        WorkflowData workflowData = mock(WorkflowData.class);
        when(workflowSession.newWorkflowData("JCR_PATH", request.getParameter("pagePath"))).thenReturn(workflowData);
 

        doThrow(workflowException).when(workflowSession).startWorkflow(any(), any());

        servlet.doPost(request, response);

        assertEquals("", response.getOutputAsString());
    }

    @Test
    public void testDoGetWithWorkflowException() throws ServletException, IOException, WorkflowException {
       
        MockSlingHttpServletResponse response = context.response();
        WorkflowException workflowException = mock(WorkflowException.class);

        when(request.getResourceResolver()).thenReturn(resourceResolver);
        WorkflowSession workflowSession = mock(WorkflowSession.class);
        when(resourceResolver.adaptTo(WorkflowSession.class)).thenReturn(workflowSession);

        // Set up the workflow model and data
        WorkflowModel workflowModel = mock(WorkflowModel.class);
        when(workflowSession.createNewModel("ust-workflow-model")).thenReturn(workflowModel);
       
        // Set up the workflow node
        WorkflowNode workflowNode = mock(WorkflowNode.class);
        when(workflowModel.createNode()).thenReturn(workflowNode);
       
        // Set up the workflow data
        WorkflowData workflowData = mock(WorkflowData.class);
        when(workflowSession.newWorkflowData("JCR_PATH", "/content/wknd/us/en")).thenReturn(workflowData);


        doThrow(workflowException).when(workflowSession).startWorkflow(any(), any());

        servlet.doGet(request, response);
        
        assertEquals("", response.getOutputAsString());
    }
}
