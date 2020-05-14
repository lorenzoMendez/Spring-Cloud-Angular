package springcloud.microservices.answer.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MicroservicesAnswerServiceApplicationTests {

	@Test
	void contextLoads() {
		Long a = 10L;
		Long b = 10L;
		
		if( a == b ) {
			System.out.println( "Son Iguales" );
		} else {
			System.out.println( "No Son Iguales" );
		}
	}

}
