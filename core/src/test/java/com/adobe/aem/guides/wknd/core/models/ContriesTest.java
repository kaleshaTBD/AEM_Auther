package com.adobe.aem.guides.wknd.core.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContriesTest {

    @Test
    public void testGetSetName() {
        // Create an instance of the Contries class
        Contries contry = new Contries();

        // Set the name
        String name = "Test Country";
        contry.setName(name);

        // Verify that the getName() method returns the same name
        assertEquals(name, contry.getName());
    }

    @Test
    public void testGetSetCode() {
        // Create an instance of the Contries class
        Contries contry = new Contries();

        // Set the code
        String code = "TC";
        contry.setCode(code);

        // Verify that the getCode() method returns the same code
        assertEquals(code, contry.getCode());
    }

    @Test
    public void testGetSetPathImage() {
        // Create an instance of the Contries class
        Contries contry = new Contries();

        // Set the pathImage
        String pathImage = "/path/to/image.jpg";
        contry.setPathImage(pathImage);

        // Verify that the getPathImage() method returns the same pathImage
        assertEquals(pathImage, contry.getPathImage());
    }
}

