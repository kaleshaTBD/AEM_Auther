package com.adobe.aem.guides.wknd.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.guides.wknd.core.service.NodeCreationService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class CreateNodeServletTest {

    AemContext context=new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
    
    @Mock
    private NodeCreationService nodeCreationService;

    @Mock
    private SlingHttpServletRequest request;

    @InjectMocks
    private CreateNodeServlet underTest = new CreateNodeServlet();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest.nodeCreationService = nodeCreationService;
    }
    
    @Test
    void testDoPost() throws ServletException, IOException {

        String nodeName = "testNode";
        String nodeType = "testType";
        Map<String, Object> properties = new HashMap<>();
        properties.put("key1", "value1");
        properties.put("key2", "value2");
        
        nodeCreationService=mock(NodeCreationService.class);
       // MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        when(request.getParameter("nodeName")).thenReturn(nodeName);
        when(request.getParameter("nodeType")).thenReturn(nodeType);
        
         when(request.getParameterNames()).thenReturn(new Enumeration<String>() {
            private boolean hasMoreElements = true;

            @Override
            public boolean hasMoreElements() {
                return hasMoreElements;
            }

            @Override
            public String nextElement() {
                hasMoreElements = false; // Finish the loop after one iteration
                return "key1"; // Return a test parameter name
            }
        });
        when(request.getParameter("key1")).thenReturn("value1");
        String responseOfNode = "success";
        when(nodeCreationService.createNode(nodeName, nodeType, properties)).thenReturn(responseOfNode);

        underTest.doPost(request, response);

        assertEquals("Servlet called but node is not created", response.getOutputAsString());
    }
}
