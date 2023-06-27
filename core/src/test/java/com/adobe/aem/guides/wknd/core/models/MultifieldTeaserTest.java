package com.adobe.aem.guides.wknd.core.models;

import org.junit.jupiter.api.Test;

import com.adobe.aem.guides.wknd.core.helper.TajTeaser;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MultifieldTeaserTest {

    private MultifieldTeaser component;

    @BeforeEach
    public void setUp() {
        component = new MultifieldTeaser();
    }

    @Test
    public void testSetGetTeaser() {
        List<TajTeaser> tajTeasers = new ArrayList<>();
        tajTeasers.add(new TajTeaser("name","pathImage"));
    
        component.setTeaser(tajTeasers);

        assertEquals(tajTeasers, component.getTeaser());
    }

}

