package br.com.jurisconexao.hub;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsUri {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String httpUri = "http://localhost:8081";
		String httpUri2 = "http://localhost:8082";
		
	        return builder.routes()
	                .route(r -> r.path("/auth/**")
	                        .uri(httpUri))
	                .route(r -> r.path("/consumer/**")
	                		.filters(f -> f
	            					.circuitBreaker(config -> config
	            						.setName("mycmd")
	            						.setFallbackUri("forward:/fallback")))
	                        .uri(httpUri2))
	                .build();
	    }
	
}
