package com.adobe.aem.guides.wknd.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.guides.wknd.core.service.NodeCreationService;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class NodeCreationComponentTest {

    @InjectMocks
    private NodeCreationComponent nodeCreationComponent;

    @Mock
    private NodeCreationService nodeCreationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInit() {
        // Set up test data
        String nodeName = "testNode";
        String nodeType = "nt:unstructured";
        Map<String, Object> properties = new HashMap<>();

        // Set the property values in the component
        nodeCreationComponent.setNodeName(nodeName);
        nodeCreationComponent.setNodeType(nodeType);
        nodeCreationComponent.init();
        // Verify that the createNode method was called with the correct arguments
        verify(nodeCreationService, times(1)).createNode(nodeName, nodeType, properties);
    }

    @Test
    public void testGetNodeName() {
        // Set up test data
        String nodeName = "testNode";

        // Set the property value in the component
        nodeCreationComponent.setNodeName(nodeName);

        // Call the getNodeName() method and assert the result
        assertEquals(nodeName, nodeCreationComponent.getNodeName());
    }

    @Test
    public void testGetNodeType() {
        // Set up test data
        String nodeType = "nt:unstructured";

        // Set the property value in the component
        nodeCreationComponent.setNodeType(nodeType);

        // Call the getNodeType() method and assert the result
        assertEquals(nodeType, nodeCreationComponent.getNodeType());
    }
}
