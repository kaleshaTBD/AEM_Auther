package com.adobe.aem.guides.wknd.core.models;

import org.junit.jupiter.api.Test;

import com.adobe.aem.guides.wknd.core.helper.TajTeaser;
import com.adobe.aem.guides.wknd.core.helper.TajTeaserHappening;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MultifieldTeaserHappeningTest {

    private MultifieldTeaserHappening component;

    @BeforeEach
    public void setUp() {
        component = new MultifieldTeaserHappening();
    }

    @Test
    public void testSetGetTeaser() {
        List<TajTeaserHappening> tajTeasers = new ArrayList<>();
        tajTeasers.add(new TajTeaserHappening("name","location","pathImage"));
    
        component.setTeaserHappenings(tajTeasers);

        assertEquals(tajTeasers, component.getTeaserHappenings());
    }

}

