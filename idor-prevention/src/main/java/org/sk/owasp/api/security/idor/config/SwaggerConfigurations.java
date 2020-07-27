package org.sk.owasp.api.security.idor.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;


/**
 * Configura Swagger-UI 
 * 
 * @author Satish Sharma
 *
 */
@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("org.sk.owasp.api.security.idor.web.resources"))              
          .paths(PathSelectors.any()) 
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Sample code for IDOR prevention", 
	      "Sample code to demonstrate IDOR prevention. This is PSEUDO code only, not to be used in production ", 
	      "IDOR Prevention", 
	      "IDOR", 
	      new Contact("Satish Sharma", "https://github.com/hellosatish", "https://linkedin.com/in/satish-sharma-49065226"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
	
	@Bean
	  UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder()
	        .deepLinking(true)
	        .displayOperationId(false)
	        .defaultModelsExpandDepth(1)
	        .defaultModelExpandDepth(1)
	        .defaultModelRendering(ModelRendering.EXAMPLE)
	        .displayRequestDuration(false)
	        .docExpansion(DocExpansion.NONE)
	        .filter(false)
	        .maxDisplayedTags(null)
	        .operationsSorter(OperationsSorter.ALPHA)
	        .showExtensions(false)
	        .tagsSorter(TagsSorter.ALPHA)
	        .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
	        .validatorUrl(null)
	        .build();
	  }
}
