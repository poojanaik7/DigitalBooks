package com.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringPublisherApplication {
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "spring-kafka";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hi " + name+" and welcome to Spring, Angular Training" );
		return "Data has been published";
	}
	
	
	@GetMapping("/publishjson")
	public String publishMessage() {
		User user = new User(2342, "pnaik",new  String[] {"Bangalore", "BTM", "15th cross"});
		template.send(topic, user);
		return " JSON Data has been published";
	}
	

	public static void main(String[] args) {
		SpringApplication.run(SpringPublisherApplication.class, args);
	}

}
