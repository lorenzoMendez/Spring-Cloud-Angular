package springcloud.microservices.answer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan( { 
	"springcloud.microservices.answer.model",
	"springcloud.microservices.student.common.model",
	"springcloud.microservices.exam.common.model" } )
public class MicroservicesAnswerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAnswerServiceApplication.class, args);
	}

}
