package springcloud.microservices.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// Habilitar zuul importante
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class MicroservicesZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run( MicroservicesZuulServerApplication.class, args );
	}

}
