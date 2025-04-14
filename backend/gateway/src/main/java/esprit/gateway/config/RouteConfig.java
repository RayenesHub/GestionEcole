
package esprit.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("student-service-route", r -> r.path("/api/students/**")
                        .uri("lb://STUDENT-SERVICE"))
                .route("course-service-route", r -> r.path("/api/courses/**")
                        .uri("lb://COURSE-SERVICE"))
                .route("teacher-service-route", r -> r.path("/api/teachers/**")
                        .uri("lb://TEACHER-SERVICE"))
                .route("auth-route", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8020"))
                .build();
    }
}