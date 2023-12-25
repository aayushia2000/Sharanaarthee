package com.hms.gateway.ApiGateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> Endpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> Endpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
