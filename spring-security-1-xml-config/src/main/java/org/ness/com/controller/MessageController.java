/**
 * 
 */
package org.ness.com.controller;

import java.util.List;

import javax.servlet.Filter;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author localadmin
 *
 */
@Controller
public class MessageController {

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@GetMapping("/")
	public String showHome() {
		
		System.out.println("Inside Home...");
		
		XmlWebApplicationContext webApplicationContext= (XmlWebApplicationContext) httpServletRequest.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		System.out.println("webApplicationContext: " + webApplicationContext);
		
		FilterChainProxy filterChainProxy = webApplicationContext.getBean("springSecurityFilterChain", FilterChainProxy.class);
		System.out.println("filterChainProxy: " + filterChainProxy);
		
		List<SecurityFilterChain> securityFilterChainsList = filterChainProxy.getFilterChains();
		System.out.println("Size: " + securityFilterChainsList.size());
		
		securityFilterChainsList.forEach(item -> {
			
			List<Filter> filters = item.getFilters();
			
			filters.forEach(items -> {
				System.out.println("Filter: " + items);
			});
		});
		
		return "home";
	}
}
