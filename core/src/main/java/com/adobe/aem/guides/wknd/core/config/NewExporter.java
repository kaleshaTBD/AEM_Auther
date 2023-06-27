/* 
package com.adobe.aem.guides.wknd.core.config;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.sling.models.export.spi.ModelExporter;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;

@Component(service = ModelExporter.class)
public class NewExporter implements ModelExporter{

    public <T> T export(Object model, Class<T> clazz,
                       Map<String, String> options)
           throws org.apache.sling.models.factory.ExportException {
           StringWriter sw = new StringWriter();
       try {
           JAXBContext jaxbContext =
                   JAXBContext.newInstance(model.getClass());
           Marshaller marshaller = jaxbContext.createMarshaller();
           marshaller.marshal(model, sw);
       } catch (JAXBException e) {
           e.printStackTrace();
       }
       return (T) sw.toString();
   }

   public String getName() {
       return "jaxb";
   }

   public boolean isSupported(Class Model1) {
       return true;
   }
}*/