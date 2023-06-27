package com.adobe.aem.guides.wknd.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class PostServletUsingPathExampleTest {

    AemContext context=new AemContext();
    private PostServletUsingPathExample fixture = new PostServletUsingPathExample();

    @Test
    void testDoPost() throws ServletException, IOException{

        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        String jsonInputString = "{\"name\":\"kalesha\"}";
        byte[] input = jsonInputString.getBytes("utf-8");
        request.setContent(input);
        request.setContentType("application/json");
        fixture.doPost(request, response);

        assertEquals(jsonInputString+"\n", response.getOutputAsString());
    }
}
