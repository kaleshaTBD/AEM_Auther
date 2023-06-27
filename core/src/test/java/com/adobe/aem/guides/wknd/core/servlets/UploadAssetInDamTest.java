package com.adobe.aem.guides.wknd.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
public class UploadAssetInDamTest {

    AemContext context=new AemContext();
    private UploadAssetInDam underTest=new UploadAssetInDam();

    @Mock
    private SlingHttpServletRequest request;
    
   
    @Mock
    private Part filePart;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoPost() throws ServletException, IOException {

        MockSlingHttpServletResponse response = context.response();
        
        String mimeType = "image/jpeg";
        String fileName = "example.jpg";
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        when(request.getPart("myfile")).thenReturn(filePart);
        when(filePart.getContentType()).thenReturn(mimeType);
        when(filePart.getSubmittedFileName()).thenReturn(fileName);
        when(filePart.getInputStream()).thenReturn(inputStream);
        
        // Mock adaptTo() and createAsset() methods
        ResourceResolver resourceResolver = mock(ResourceResolver.class);
        AssetManager assetManager = mock(AssetManager.class);
        Asset imageAsset = mock(Asset.class);
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(AssetManager.class)).thenReturn(assetManager);
        when(assetManager.createAsset("/content/dam/wknd/" + fileName, inputStream, mimeType, true)).thenReturn(imageAsset);
        
        underTest.doPost(request, response);
        
       assertEquals("Servlet called but asset not stored", response.getOutputAsString());
    }
}
