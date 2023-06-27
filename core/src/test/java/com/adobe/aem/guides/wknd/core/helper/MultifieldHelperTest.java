package com.adobe.aem.guides.wknd.core.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MultifieldHelperTest {
    
    @Test
    public void testMultifieldHelperWithValidValues() {
    // Mock the Resource object with valid properties
    Resource resource = Mockito.mock(Resource.class);
    ValueMap valueMap = Mockito.mock(ValueMap.class);
    Mockito.when(resource.getValueMap()).thenReturn(valueMap);
    Mockito.when(valueMap.get("countryname", String.class)).thenReturn("United States");
    Mockito.when(valueMap.get("countryimage", String.class)).thenReturn("/images/us.jpg");
    Mockito.when(valueMap.get("countrycode", String.class)).thenReturn("US");
    Mockito.when(valueMap.get("countrysummary", String.class)).thenReturn("US");

    // Create MultifieldHelper object
    MultifieldHelper helper = new MultifieldHelper(resource);

    // Assertions
    assertEquals("United States", helper.getCountryname());
    assertEquals("/images/us.jpg", helper.getCountryimage());
    assertEquals("US", helper.getCountrycode());
    assertEquals("US", helper.getCountrysummary());
    assertNull(helper.getStates()); // Assuming states are not set in this test case
}

@Test
public void testMultifieldHelperInitializationWithEmptyValues() {
    // Mock the Resource object with empty or null properties
    Resource resource = Mockito.mock(Resource.class);
    ValueMap valueMap = Mockito.mock(ValueMap.class);
    Mockito.when(resource.getValueMap()).thenReturn(valueMap);
    Mockito.when(valueMap.get("countryname", String.class)).thenReturn("");
    Mockito.when(valueMap.get("countrysummary", String.class)).thenReturn("");
    Mockito.when(valueMap.get("countryimage", String.class)).thenReturn(null);
    Mockito.when(valueMap.get("countrycode", String.class)).thenReturn(null);

    // Create MultifieldHelper object
    MultifieldHelper helper = new MultifieldHelper(resource);

    // Assertions
    assertNull(helper.getCountryname());
    assertNull(helper.getCountryimage());
    assertNull(helper.getCountrycode());
    assertNull(helper.getCountrysummary());
    assertNull(helper.getStates()); // Assuming states are not set in this test case
}

@Test
public void testSetAndGetStates() {
    
    Resource resource = Mockito.mock(Resource.class);
    ValueMap valueMap = Mockito.mock(ValueMap.class);
    List<NastedHalper> states = new ArrayList<>();
    Mockito.when(resource.getValueMap()).thenReturn(valueMap);
    Mockito.when(valueMap.get("statename", String.class)).thenReturn("United States");
    Mockito.when(valueMap.get("statesummary", String.class)).thenReturn("United States");
    Mockito.when(valueMap.get("stateformationdate", Date.class)).thenReturn(new Date());
    Mockito.when(valueMap.get("statestrength", Integer.class)).thenReturn(100);

    states.add(new NastedHalper(resource));
    states.add(new NastedHalper(resource));
    
    // Create MultifieldHelper object
    MultifieldHelper helper = new MultifieldHelper(resource);

    // Set the states
    helper.setStates(states);

    // Get the states
    List<NastedHalper> retrievedStates = helper.getStates();

    // Assertions
    assertEquals(states, retrievedStates);
}

}
