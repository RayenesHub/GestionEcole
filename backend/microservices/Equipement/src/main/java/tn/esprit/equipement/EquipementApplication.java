package tn.esprit.equipement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication

public class EquipementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipementApplication.class, args);
	}

}
