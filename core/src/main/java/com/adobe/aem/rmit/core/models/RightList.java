package com.adobe.aem.rmit.core.models;
/**
* This pojo Created by Amulya on 12/10/2018.
*/
import java.util.List;
public class RightList {

       private String heading;
       
       private String headingUrl;
       
       private List<Link> links = null;

       public String getHeading() {
              return heading;
       }

       public void setHeading(String heading) {
              this.heading = heading;
       }

       
       public String getHeadingUrl() {
              return headingUrl;
       }

       public void setHeadingUrl(String headingUrl) {
              this.headingUrl = headingUrl;
       }
       
       public List<Link> getLinks() {
              return links;
       }

       public void setLinks(List<Link> links) {
              this.links = links;
       }

}
