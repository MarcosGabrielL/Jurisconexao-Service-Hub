package br.com.jurisconexao.hub;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HubController {
	
	@RequestMapping("/")
	public Mono<String> host() {
		return Mono.just("Hub Jurisconexão");
	}
	
	@RequestMapping("")
	public Mono<String> host1() {
		return Mono.just("Hub Jurisconexão");
	}
	
		
		@RequestMapping("/fallback")
		public Mono<String> fallback() {
			return Mono.just("fallback");
		}
		

}
