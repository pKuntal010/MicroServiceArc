package com.prac.filter;

import java.awt.image.DataBuffer;
import java.util.List;
import java.util.Set;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AppFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("filter method called");
		ServerHttpRequest request = exchange.getRequest();
		
		org.springframework.http.HttpHeaders headers = request.getHeaders();
		Flux<org.springframework.core.io.buffer.DataBuffer> body = request.getBody();
		
		Set<String> keySet = headers.keySet();
		keySet.forEach(k -> {
			List<String> value = headers.get(k);
			System.out.println(k);
			System.out.println(value);

			System.out.println("++++++++++++++++++++++++++++++");
		});
		return chain.filter(exchange);
	}

}
