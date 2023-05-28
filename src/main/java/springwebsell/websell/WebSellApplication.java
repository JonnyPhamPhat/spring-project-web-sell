package springwebsell.websell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class WebSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSellApplication.class, args);
		System.out.println("Hello world");
	}

}
