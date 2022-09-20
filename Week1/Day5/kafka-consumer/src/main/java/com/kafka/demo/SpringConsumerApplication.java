package com.kafka.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringConsumerApplication {
	
	List<String> messages = new ArrayList<>();
	
	User userFromTopic = null;
	
	@GetMapping("/consumeString")
	public List<String> consumeMessage() {
		return messages;
	}
	@KafkaListener(groupId="groupid-1", topics= "spring-kafka", containerFactory ="kafkaListenerContainerFactory")
	public List<String> getMessageFromTopic(String data) {
		messages.add(data);
		return messages;	
	}
	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMessage() {
		return userFromTopic;
	}
	@KafkaListener(groupId="groupid-2", topics= "spring-kafka", containerFactory ="userKafkaListenerContainerFactory")
	public User getJsonMessageFromTopic(User user) {
		userFromTopic = user;
		return userFromTopic;
		
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringConsumerApplication.class, args);
	}

}
