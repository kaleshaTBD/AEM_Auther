package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.adobe.aem.guides.wknd.core.testcontext.AppAemContext;
import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
public class CustomComponentFirstTest {

    private final AemContext context = AppAemContext.newAemContext();
    private CustomComponentFirst customComponentFirst;

    @Mock
    private Page page;
    @Mock
    private Resource resource;

    @BeforeEach
    public void setUp() {
       
        page = context.create().page("/content/customComponentFirst");
        resource = context.create().resource(page, "customComponentFirst",
            "sling:resourceType", "wknd/components/customComponentFirst");

      customComponentFirst = resource.adaptTo(CustomComponentFirst.class);
      customComponentFirst.setFname("ust");
      customComponentFirst.setLname("hyd");
      customComponentFirst.setProfessor(true);
      customComponentFirst.setModified("2023-06-18");
      customComponentFirst.setModifiedBy("admin");
    }

    @Test
    public void testGetFirstName() {
       assertEquals("UST", customComponentFirst.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("HYD", customComponentFirst.getLastName());
    }

    @Test
    public void testGetIsProfessor() {
        assertEquals(true, customComponentFirst.getIsProfessor());
    }

    @Test
    public void testGetLastModifiedBy() {
        assertEquals("admin", customComponentFirst.getLastModifiedBy());
    }

    @Test
    public void testGetLastModified() {
        assertEquals("2023-06-18", customComponentFirst.getLastModified());
    }

    @Test
    public void testGetPasswordFalse() {
        customComponentFirst.setProfessor(false);
        customComponentFirst.setPassword("123456");

        String password = customComponentFirst.getPassword();

        assertEquals("*****************", password);
    }

    @Test
    public void testGetPassword() {
        customComponentFirst.setProfessor(true);
        customComponentFirst.setPassword("123456");

        String password = customComponentFirst.getPassword();

        assertEquals("123456", password);
    }

    @Test
    public void testGetImagePath() {
        assertEquals("/content/customComponentFirst/jcr:content/customComponentFirst/file", customComponentFirst.getImagePath());
    }
}

