package com.adobe.aem.guides.wknd.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Named;
//import java.util.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.*;
import com.adobe.aem.guides.wknd.core.models.CustomComponentFirst;
//import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class,
        resourceType = CustomComponentFirst.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class CustomComponentFirst {

    final protected static String RESOURCE_TYPE="aem-guides-wknd/components/customComponentFirst";

    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;

    @PostConstruct
    protected void init() {
        resourceResolver.adaptTo(PageManager.class);
   //   String currentPagePath = Optional.ofNullable(pageManager).map(pm -> pm.getContainingPage(currentResource)).map(Page::getPath).orElse("");
    }


    @ValueMapValue
    @Default(values = "Kalesha")
    private String fname;

    @ValueMapValue
    @Default(values = "Shaik")
    private String lname;

    @ValueMapValue
    private boolean professor;

    @ValueMapValue
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;

    @ValueMapValue
    @Via("resource")
    @Named("jcr:lastModified")
    String modified;

    @ValueMapValue
    private String password;
    
    public String getFirstName() {
        return fname.toUpperCase();
    }

    public String getLastName() {
        return lname.toUpperCase();
    }

    public boolean getIsProfessor() {
        return professor;
    }

    public String getLastModifiedBy(){
        return modifiedBy;
    }

    public String getLastModified(){
        return modified;
    }

    public String getPassword() {
        if(professor)
        {
            return password;
        }
        else{
            return "*****************";
        }
     }

    public String getImagePath() {
      return currentResource.getPath()+"/file";
    }


    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
