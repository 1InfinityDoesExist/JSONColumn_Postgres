package com.primaryDI.swaggerConfig;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.primaryDI")).paths(regex("/api/object/.*")).build()
				.apiInfo(metaData());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Address", "Address Crud Operation", "1.0",
				"https://www.github.com/1InfinityDoesExist", "Salam Rocky Bhai", "Apache Lisence Version 2.0",
				"https://www.apache.org/lisences/LISENCE-2.0");
		return apiInfo;
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
