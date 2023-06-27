package com.adobe.aem.guides.wknd.core.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TajTeaserHappeningTest {

    private TajTeaserHappening taj;

     @BeforeEach
    public void setUp() {
        taj = new TajTeaserHappening();
    }
    @Test
    void testSetGetName() {
        taj.setName("Taj");
        assertEquals("Taj", taj.getName());
    }

    @Test
    void testSetGetLocation() {
        taj.setLocation("location");
        assertEquals("location", taj.getLocation());
    }

    @Test
    void testSetGetPathImage() {
        taj.setPathImage("/images/taj-updated.jpg");
        assertEquals("/images/taj-updated.jpg", taj.getPathImage());
    }

}
