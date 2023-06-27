package com.adobe.aem.guides.wknd.core.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NastedHalperTest {
    
    @Test
    public void testNestedHelperWithValues() {
    
    Resource resource = Mockito.mock(Resource.class);
    ValueMap valueMap = Mockito.mock(ValueMap.class);
    
    Mockito.when(resource.getValueMap()).thenReturn(valueMap);
    Mockito.when(valueMap.get("statename", String.class)).thenReturn("Texas");
    Mockito.when(valueMap.get("statesummary", String.class)).thenReturn("Texas");
    Mockito.when(valueMap.get("stateformationdate", Date.class)).thenReturn(new Date(2323223232L));
    Mockito.when(valueMap.get("statestrength", Integer.class)).thenReturn(100);

    NastedHalper helper = new NastedHalper(resource);

    assertEquals("Texas", helper.getStatename());
    assertEquals("Texas", helper.getStatesummary());
    assertEquals("Wed Jan 28 02:50:23 IST 1970", helper.getStateformationdate().toString());
    assertEquals(100, helper.getStatestrength());
}

@Test
public void testNestedHelperWithEmptyValues() {

Resource resource = Mockito.mock(Resource.class);
ValueMap valueMap = Mockito.mock(ValueMap.class);

Mockito.when(resource.getValueMap()).thenReturn(valueMap);
Mockito.when(valueMap.get("statename", String.class)).thenReturn("");
Mockito.when(valueMap.get("statesummary", String.class)).thenReturn("");
Mockito.when(valueMap.get("stateformationdate", Date.class)).thenReturn(null);
Mockito.when(valueMap.get("statestrength", Integer.class)).thenReturn(0);

NastedHalper helper = new NastedHalper(resource);

assertNull(helper.getStatename());
assertNull(helper.getStatesummary());
assertNull(helper.getStateformationdate());
assertNotNull(helper.getStatestrength());
}
}
