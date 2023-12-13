package thespeace.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBasicApplication {

	/**
	 * 	스프링은 톰캣과 웹서버를 내장하고 있기 때문에 자체적으로 띄우면서 스프링 부트도 같이 실행된다.
	 *  + 번외 : 인텔리제이를 사용할때 default build가 gradle을 통해서 실행하게 되어 있기 때문에, 설정에서 build 설정을 인텔리제이로 설정하여 직접 자바를 실행하는 것으로 바꿔주면 속도가 훨씬 빠르다.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBasicApplication.class, args);
	}
}
