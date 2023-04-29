package br.com.jurisconexao.hub;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@EnableConfigurationProperties(Configuration.class)
public class HttpsUri {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, Configuration uriConfiguration) {
		String httpUri = uriConfiguration.getHttpbin();
		return builder.routes()
			.route(p -> p
				.path("/get")
				.filters(f -> f.addRequestHeader("Hello", "World"))
				.uri(httpUri))
			.route(p -> p
				.host("*.circuitbreaker.com")
				.filters(f -> f
					.circuitBreaker(config -> config
						.setName("mycmd")
						.setFallbackUri("forward:/fallback")))
				.uri(httpUri))
			.build();
	}
}
