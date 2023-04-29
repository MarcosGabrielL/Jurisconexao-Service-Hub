package br.com.jurisconexao.hub;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.jurisconexao.hub.models.UriEntity;
import br.com.jurisconexao.hub.repositories.UriRepository;

@Configuration
public class HttpsUri {
	
	@Autowired
	private UriRepository uriRepository;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		
		 List<UriEntity> uris = uriRepository.findAll();
		 
		String httpUri_auth = "http://localhost:8081";
		String httpUri2 = "http://localhost:8082";
		String  PostMapping_authenticate = "/authenticate";
		
	        return builder.routes()
	                .route(r -> r.path("/auth/**")
	                        .uri(httpUri_auth+PostMapping_authenticate))
	                .route(r -> r.path("/consumer/**")
	                		.filters(f -> f
	            					.circuitBreaker(config -> config
	            						.setName("mycmd")
	            						.setFallbackUri("forward:/fallback")))
	                        .uri(httpUri2))
	                .build();
	    }
	
}
