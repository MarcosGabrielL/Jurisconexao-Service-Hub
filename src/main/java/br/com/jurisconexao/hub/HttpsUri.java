package br.com.jurisconexao.hub;


import java.net.URI;
import java.nio.charset.Charset;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import br.com.jurisconexao.hub.filters.AuthenticationFilter;
import br.com.jurisconexao.hub.types.Rota;
import  br.com.jurisconexao.hub.types.Uri;
import br.com.jurisconexao.hub.types.circuitBreaker;
import reactor.core.publisher.Mono;

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
								.circuitBreaker(config -> config
										.setName(circuitBreaker.circuit_process_register.toString())
										.setFallbackUri("forward:/fallback"))
								.filter(new AuthenticationFilter(getwebClient( Uri.uri_auth))))
						.uri(Uri.path_chat_public.toString()))
				.route(Rota.process_register.toString(), r -> r
						.path(Uri.path_auth_process_register) 
						.filters(f -> f
								.circuitBreaker(config -> config
									.setName(circuitBreaker.circuit_process_register.toString())
									.setFallbackUri("forward:/fallback"))
						 )
						.uri(Uri.uri_auth.toString()))
				.route(Rota.pay_plan_1.toString(), r -> r
						.path(Uri.path_pago_plan1.toString())
						.filters(f -> f
								.circuitBreaker(config -> config
										.setName(circuitBreaker.circuit_process_pago_plan1.toString())
										.setFallbackUri("forward:/fallback"))
								.filter(new AuthenticationFilter(getwebClient( Uri.uri_auth)))
								.filter((exchange, chain) -> {
				                    return chain.filter(exchange)
				                        .doOnSuccess(response -> {
				                                    exchange.getResponse()
				                                        .getHeaders()
				                                        .setLocation(URI.create(Rota.pay_nfe.toString()));
				                                    exchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
				                        });
				                })   		
						)
						.uri(Uri.path_chat_public.toString()))
				.route(Rota.pay_nfe.toString(), r -> r 
						.path(Uri.path_nfe_plan1.toString())
						.uri(Uri.uri_nfe.toString()))
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
