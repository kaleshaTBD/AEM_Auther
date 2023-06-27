package com.adobe.aem.guides.wknd.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.guides.wknd.core.helper.MultifieldHelper;
import com.adobe.aem.guides.wknd.core.helper.NastedHalper;

public class NestedMultifieldOfContryWithStatesTest {
   
    @Mock
    private Resource componentResource;

    @Mock
    private Resource countryDetailNasted;

    @Mock
    private Resource countryNasted;

    @Mock
    private Resource nastedResource;

    @Mock
    private Resource nasted;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
  
    @Test
    void testCountryDetailsNasted() {
        List<MultifieldHelper> expectedCountryDetailsNasted = new ArrayList<>();
        List<NastedHalper> expectedCountryNastedList = new ArrayList<>();
        // Mocking the getChild() method of componentResource
        when(componentResource.getChild("countrydetailswithnastedmultifield"))
                .thenReturn(countryDetailNasted); //1

        if (countryDetailNasted != null) {
            // Mocking the getChildren() method of countryDetailNasted
            when(countryDetailNasted.getChildren()).thenReturn(List.of(countryNasted)); // 1- 2
            
            ValueMap valueMap = Mockito.mock(ValueMap.class);
            Mockito.when(countryNasted.getValueMap()).thenReturn(valueMap);
            Mockito.when(valueMap.get("countryname", String.class)).thenReturn("United States");
            Mockito.when(valueMap.get("countryimage", String.class)).thenReturn("/images/us.jpg");
            Mockito.when(valueMap.get("countrycode", String.class)).thenReturn("US");
            Mockito.when(valueMap.get("countrysummary", String.class)).thenReturn("US");
            MultifieldHelper expectedMultifieldHelper = new MultifieldHelper(countryNasted);

            // Mocking the hasChildren() method of countryNasted
            when(countryNasted.hasChildren()).thenReturn(true);

            // Mocking the getChild() method of countryNasted
            when(countryNasted.getChild("states")).thenReturn(nastedResource);

            when(nastedResource.getChildren()).thenReturn(List.of(nasted));

            ValueMap valueMap1 = Mockito.mock(ValueMap.class);
            Mockito.when(nasted.getValueMap()).thenReturn(valueMap1);
            Mockito.when(valueMap1.get("statename", String.class)).thenReturn("Texas");
            Mockito.when(valueMap1.get("statesummary", String.class)).thenReturn("Texas");
            Mockito.when(valueMap1.get("stateformationdate", Date.class)).thenReturn(new Date(2323223232L));
            Mockito.when(valueMap1.get("statestrength", Integer.class)).thenReturn(100);

            expectedCountryNastedList.add(new NastedHalper(nasted));

            // Mocking the getChildren() method of nastedResource
           // when(nastedResource.getChildren()).thenReturn(List.of());

            expectedMultifieldHelper.setStates(expectedCountryNastedList);
            expectedCountryDetailsNasted.add(expectedMultifieldHelper);
        }

        NestedMultifieldOfContryWithStates nestedMultifield = new NestedMultifieldOfContryWithStates();
        nestedMultifield.componentResource = componentResource;
        List<MultifieldHelper> aa=  nestedMultifield.getCountryDetailsWithNastedMultifield();

        assertEquals(expectedCountryDetailsNasted.size(), aa.size());
        assertEquals(expectedCountryNastedList.size(), aa.get(0).getStates().size());

    }
    
@Test
public void testGetCountryDetailsWithNastedMultifieldNoChildren() {
    // Mock the componentResource to return null for getChild("countrydetailswithnastedmultifield")
    Resource componentResource = Mockito.mock(Resource.class);
    Mockito.when(componentResource.getChild("countrydetailswithnastedmultifield")).thenReturn(null);

    // Create the instance of NestedMultifieldOfContryWithStates
    NestedMultifieldOfContryWithStates nestedMultifield = new NestedMultifieldOfContryWithStates();
    nestedMultifield.componentResource = componentResource;

    // Call the method and get the result
    List<MultifieldHelper> countryDetails = nestedMultifield.getCountryDetailsWithNastedMultifield();

    // Assertions
    assertNotNull(countryDetails);
    assertTrue(countryDetails.isEmpty());
}


@Test
public void testGetCountryDetailsWithNestedMultifieldException() {
    // Mock the componentResource
    Resource componentResource = Mockito.mock(Resource.class);
    Mockito.when(componentResource.getChild("countrydetailswithnastedmultifield")).thenThrow(new NullPointerException("Mocked exception"));

    // Create an instance of NestedMultifieldOfContryWithStates
    NestedMultifieldOfContryWithStates nestedMultifield = new NestedMultifieldOfContryWithStates();
    nestedMultifield.componentResource = componentResource;

    // Invoke the method to get country details with nested multifield
    List<MultifieldHelper> countryDetails = nestedMultifield.getCountryDetailsWithNastedMultifield();

    // Assertions
    assertEquals(0, countryDetails.size());
    // Check the logs or verify the exception handling as per your implementation
}

}
