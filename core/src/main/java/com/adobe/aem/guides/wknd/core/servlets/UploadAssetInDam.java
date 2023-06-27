package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import org.apache.sling.api.servlets.HttpConstants;
import java.io.IOException;
import java.io.InputStream;

@Component(service = Servlet.class, property = {
    "sling.servlet.methods=" + HttpConstants.METHOD_POST,
    "sling.servlet.paths=" + "/bin/uploadAsset"
})
@ServiceDescription("Uploading Assets into DAM Servlet")
public class UploadAssetInDam extends SlingAllMethodsServlet {
   
   // private static final Logger log = LoggerFactory.getLogger(UploadAssetInDam.class);
   
    private InputStream is = null;
	private	String mimeType = "";
    
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
       
        Part filePart = request.getPart("myfile");
        mimeType = filePart.getContentType();
        String fileName = filePart.getSubmittedFileName();
        is = filePart.getInputStream();
        
        AssetManager assetManager = request.getResourceResolver().adaptTo(AssetManager.class);
        Asset imageAsset = assetManager.createAsset("/content/dam/wknd/"+fileName, is, mimeType , true);
        response.setStatus(200);
        response.setContentType("application/json");
        if(StringUtils.isEmpty(imageAsset.getName()))
        {
            response.getWriter().write("Servlet called but asset not stored");
        }
        else{
            response.getWriter().write(imageAsset.getName());
        }
        
        
    
     }
}

