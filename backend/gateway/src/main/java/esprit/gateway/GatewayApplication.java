package esprit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean

	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route("Student", r -> r.path("/student/**")
						.uri("lb://Student"))
<<<<<<< HEAD
				.route("Event", r -> r.path("/event/**")
						.uri("lb://Event"))
=======
				.route("mailing",m->m.path("/mailing/**")
				.uri("lb://Mailing"))
>>>>>>> d3e8d7a6c616f29f989c772e702da43806ddbc46
				.build();

	}


}