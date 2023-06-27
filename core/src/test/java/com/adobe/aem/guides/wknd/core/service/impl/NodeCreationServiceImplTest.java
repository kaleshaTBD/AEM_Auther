package com.adobe.aem.guides.wknd.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.adobe.aem.guides.wknd.core.config.NodeCreationServiceConfig;
import com.adobe.aem.guides.wknd.core.service.NodeCreationService;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class NodeCreationServiceImplTest {

    private NodeCreationService nodeCreationService;
    private ResourceResolverFactory resourceResolverFactory;
    private ResourceResolver resourceResolver;
    private Session session;
    private Node contentNode;
    private Node afterAdd;

    @BeforeEach
    public void setUp() throws LoginException, PathNotFoundException, RepositoryException {
        nodeCreationService = new NodeCreationServiceImpl();
        resourceResolverFactory = mock(ResourceResolverFactory.class);
        resourceResolver = mock(ResourceResolver.class);
        session = mock(Session.class);
        contentNode = mock(Node.class);
        afterAdd = mock(Node.class);

        when(resourceResolverFactory.getServiceResourceResolver(anyMap())).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
        when(session.getNode(anyString())).thenReturn(contentNode);
        when(contentNode.addNode(anyString())).thenReturn(afterAdd);
    }

    @AfterEach
    public void tearDown() {
        nodeCreationService = null;
        resourceResolverFactory = null;
        //resourceResolver = null;
        session = null;
        contentNode = null;
        afterAdd=null;
 }

    @Test
    public void testActivate() {
        // Prepare test data
        NodeCreationServiceConfig config = Mockito.mock(NodeCreationServiceConfig.class);
        String parentPath = "/content";

        // Set up the mock behavior
        when(config.parentPath()).thenReturn(parentPath);

        // Create an instance of the NodeCreationService and call the activate method
        NodeCreationServiceImpl nodeCreationService = new NodeCreationServiceImpl();
        nodeCreationService.activate(config);

        // Verify the behavior and assertions
        verify(config, times(1)).parentPath(); // Verify that the parentPath() method was called once
        assertEquals(parentPath, nodeCreationService.getParentPath()); // Assert that the parentPath was set correctly
    }
    
    @Test
    public void testCreateNode_SuccessfulCreation() throws Exception {
        // Prepare test data
        String parentPath = "/content";
        String nodeName = "testNode";
        String nodeType = "cq:PageContent";
        Map<String, Object> properties = new HashMap<>();
        properties.put("property1", "value1");
        properties.put("property2", 123);

        // Set up the necessary dependencies
        nodeCreationService.setResourceResolverFactory(resourceResolverFactory);
        nodeCreationService.setParentPath(parentPath);

        // Call the method under test
        String result = nodeCreationService.createNode(nodeName, nodeType, properties);

        // Assert the result
        assertNotNull(result); // Check if the result is not null
       
        // Additional assertions (optional)
        verify(resourceResolverFactory, times(1)).getServiceResourceResolver(anyMap()); // Verify that the getServiceResourceResolver method was called once
        verify(session, times(1)).getNode(parentPath); // Verify that the getNode method was called once
        verify(contentNode, times(1)).addNode(nodeName); // Verify that the addNode method was called once
        verify(session, times(1)).save(); // Verify that the save method was called once
  }

    @Test
    public void testCreateNode_ResourceResolverException() throws Exception {
        // Prepare test data
        String parentPath = "/content";
        String nodeName = "testNode";
        String nodeType = "cq:PageContent";
        Map<String, Object> properties = new HashMap<>();
        properties.put("property1", "value1");
        properties.put("property2", 123);

        // Set up the necessary dependencies
        nodeCreationService.setResourceResolverFactory(resourceResolverFactory);
        nodeCreationService.setParentPath(parentPath);

        // Mock the behavior of resourceResolverFactory to throw an exception
        when(resourceResolverFactory.getServiceResourceResolver(anyMap())).thenThrow(new NullPointerException("Resource resolver exception"));

        // Call the method under test
        String result = nodeCreationService.createNode(nodeName, nodeType, properties);

        // Assert the result
        assertNotNull(result); // Check if the result is not null
        assertEquals("Resource resolver exception", result); // Verify the returned error message

        // Additional assertions (optional)
        verify(resourceResolverFactory, times(1)).getServiceResourceResolver(anyMap()); // Verify that the getServiceResourceResolver method was called once
    }

}
