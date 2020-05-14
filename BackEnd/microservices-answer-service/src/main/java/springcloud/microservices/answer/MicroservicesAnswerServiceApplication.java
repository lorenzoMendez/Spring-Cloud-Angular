package springcloud.microservices.answer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Desabhilitarlo porque no se trabaja con JPA, los commons estan como JPA por eso se deshabilita
@EnableAutoConfiguration( exclude = { DataSourceAutoConfiguration.class } )
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
// @EntityScan( { "springcloud.microservices.answer.model", "springcloud.microservices.student.common.model", "springcloud.microservices.exam.common.model" } )
public class MicroservicesAnswerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAnswerServiceApplication.class, args);
	}

}
