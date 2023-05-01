package br.com.jurisconexao.hub.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import br.com.jurisconexao.hub.types.Uri;
import reactor.core.publisher.Mono;

public class AuthenticationFilter implements GatewayFilter {

    private final WebClient webClient;

    public AuthenticationFilter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Obter o token de acesso enviado pelo cliente
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            // Remover o prefixo "Bearer " do token
            token = token.substring(7);
            // Fazer uma chamada à rota Rota.isLogged para validar o token
            return webClient.get()
                    .uri( Uri.path_auth_islogged.toString() + "?token=" + token)
                    .exchange()
                    .flatMap(response -> {
                        if (response.statusCode().is2xxSuccessful()) {
                            // Token válido, permitir o acesso à rota
                            return chain.filter(exchange);
                        } else {
                            // Token inválido, negar o acesso à rota
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        }
                    });
        } else {
            // Token não fornecido, negar o acesso à rota
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
