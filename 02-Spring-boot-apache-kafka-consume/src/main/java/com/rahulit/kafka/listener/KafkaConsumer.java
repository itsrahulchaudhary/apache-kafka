package com.rahulit.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.rahulit.kafka.model.User;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "myFirstTopic", group = "group_id")
	public void consume(String message) {
		System.out.println("Consumed message: " + message);
	}

//	@KafkaListener(topics = "Kafka_Example_Json", group = "group_json", containerFactory = "userKafkaListenerFactory")
//	public void consumeJson(User user) {
//		System.out.println("Consumed JSON Message: " + user);
//	}
}
