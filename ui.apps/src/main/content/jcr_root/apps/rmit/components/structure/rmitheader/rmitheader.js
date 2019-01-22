"use strict";

use(function () {

    var ctaURL = granite.resource.properties["ctaURL"] || 'CTA';
	var newWindow = granite.resource.properties["newWindow"] || '_self';
    if(((ctaURL)!=null) && (ctaURL).startsWith("/content"))
    {
		ctaURL = ctaURL + ".html";
	 }


    if(newWindow=='true')
    {
		target='_blank';
    }

      return {

        ctaURL :  ctaURL,
        newWindow : newWindow
    }
});