package br.com.jurisconexao.hub;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.jurisconexao.hub.filters.AuthenticationFilter;
import br.com.jurisconexao.hub.types.Rota;
import  br.com.jurisconexao.hub.types.Uri;
import br.com.jurisconexao.hub.types.circuitBreaker;

@Configuration
public class HttpsUri {


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {


		return 
				builder.routes()

				.route(Rota.isLogged.toString(), r -> r 
						.path(Uri.path_auth_islogged.toString())
						.uri(Uri.uri_auth.toString()))
				.route(Rota.authenticate.toString(), r -> r 
						.path(Uri.path_auth_authenticate.toString())
						.uri(Uri.uri_auth.toString()))
				.route(Rota.message_send.toString(), r -> r
						.path(Uri.path_auth_authenticate.toString())
						.filters(f -> f
								.filter(new AuthenticationFilter(getwebClient( Uri.uri_auth))))
						.uri(Uri.path_chat_public.toString()))
				.route(Rota.process_register.toString(), r -> r
						.path(Uri.path_process_register) 
						.filters(f -> f
								.circuitBreaker(config -> config
									.setName(circuitBreaker.circuit_process_register.toString())
									.setFallbackUri("forward:/fallback"))
								.filter(new AuthenticationFilter(getwebClient( Uri.uri_auth)))
								)
						.uri(Uri.uri_auth.toString()))
				.build();
	}

	public WebClient getwebClient(String uri) {
		WebClient webClient = WebClient.builder()
				.baseUrl(uri) 
				.clientConnector(new ReactorClientHttpConnector()) 
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		return webClient;
	}

}
