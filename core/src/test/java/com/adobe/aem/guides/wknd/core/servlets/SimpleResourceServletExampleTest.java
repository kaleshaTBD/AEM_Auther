package com.adobe.aem.guides.wknd.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.servlethelpers.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.gson.JsonObject;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class SimpleResourceServletExampleTest {
    
    AemContext context=new AemContext();
    private SimpleResourceServletExample fixture = new SimpleResourceServletExample();

    @Test
    void testDoGet() throws ServletException, IOException{

        JsonObject mock= new JsonObject();
        mock.addProperty("property1", "value1");
        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();
        fixture.doGet(request, response);

        assertEquals(mock.toString(), response.getOutputAsString());
    }

    @Test
    void testDoPost() throws ServletException, IOException{

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        String jsonInputString = "{\"name\":\"kalesha\"}\n";
        byte[] input = jsonInputString.getBytes("utf-8");
        request.setContent(input);
        request.setContentType("application/json");
        fixture.doPost(request, response);

        assertEquals(jsonInputString, response.getOutputAsString());
    }

    @Test
    void testDoPostNullData() throws ServletException, IOException{

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        request.setContent(null);
        request.setContentType("application/json");
        fixture.doPost(request, response);

        assertEquals("", response.getOutputAsString());
    }

    
}
