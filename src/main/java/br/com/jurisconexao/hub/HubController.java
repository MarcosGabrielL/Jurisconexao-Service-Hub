package br.com.jurisconexao.hub;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HubController {
	
		
		@RequestMapping("/fallback")
		public Mono<String> fallback() {
			return Mono.just("fallback");
		}
		

}
