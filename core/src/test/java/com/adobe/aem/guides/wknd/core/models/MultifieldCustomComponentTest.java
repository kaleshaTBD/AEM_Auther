package com.adobe.aem.guides.wknd.core.models;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MultifieldCustomComponentTest {

    private MultifieldCustomComponent component;

    @BeforeEach
    public void setUp() {
        component = new MultifieldCustomComponent();
    }

    @Test
    public void testGetContries() {
        // Create a list of countries for testing
        List<Contries> countries = new ArrayList<>();
        countries.add(new Contries("Country 1","1","/test"));
    
        // Set the countries in the component
        component.setContries(countries);

        // Verify that the getContries() method returns the same list of countries
        assertEquals(countries, component.getContries());
    }

    @Test
    public void testSetContries() {
        // Create a list of countries for testing
        List<Contries> countries = new ArrayList<>();
        countries.add(new Contries("Country 1","1","/test"));

        // Set the countries in the component
        component.setContries(countries);

        // Verify that the setContries() method correctly sets the list of countries
        assertEquals(countries, component.getContries());
    }
}

