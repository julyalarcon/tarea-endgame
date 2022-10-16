 package com.protalento.settings;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfig implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.setConfigLocation("com.protalento.settings.WebApp");

		Dynamic dynamic = servletContext.addServlet("appServlet",
				new DispatcherServlet(annotationConfigWebApplicationContext));
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("*.html");
		
		
	}

}
