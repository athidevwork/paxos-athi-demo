/**
 * 
 */
package com.paxos.paxosathidemo;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 * @author Athi
 *
 */
@Configuration
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
	  public JerseyConfig() {
		  
	  }
	 
	  @PostConstruct
	  public void setUp() {
	    register(DemoResource.class);
	    //(GenericExceptionMapper.class);
	    register(JacksonJaxbJsonProvider.class);
	  }
}
