package io.pivotal.demo.wlsjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication(exclude = JmxAutoConfiguration.class)
public class WlsjmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsjmsApplication.class, args);
	}

}
