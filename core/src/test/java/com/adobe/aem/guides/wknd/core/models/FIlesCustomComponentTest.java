package com.adobe.aem.guides.wknd.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import org.apache.sling.api.resource.Resource;

import org.junit.jupiter.api.Test;

public class FIlesCustomComponentTest {
    @Test
    void testGetLinks() {

        FIlesCustomComponent component = new FIlesCustomComponent();

        // Create a list of resource links for testing
        List<Resource> links = new ArrayList<>();
        links.add(mock(Resource.class));
        links.add(mock(Resource.class));
        links.add(mock(Resource.class));

        // Set the links in the component
        component.setLinks(links);

        // Verify that the getLinks() method returns the same list of links
        assertEquals(links, component.getLinks());
        
    }
}
