package com.rahulit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.rahulit.dto.Customer;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;
    
    public void sendMessageToTopic1(String message){
    	template.send("javatechie-demo", message);
    }

    public void sendMessageToTopic(String message){
    	ListenableFuture<SendResult<String, Object>> listenableFuture = template.send("demo-04-topic", message);
    	CompletableFuture<SendResult<String, Object>> future = toCompletableFuture(listenableFuture);

        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }
    
    public static <T> CompletableFuture<T> toCompletableFuture(ListenableFuture<T> listenableFuture) {
        CompletableFuture<T> completable = new CompletableFuture<>();
        listenableFuture.addCallback(completable::complete, completable::completeExceptionally);
        return completable;
    }

    public void sendEventsToTopic(Customer customer) {
        try {
        	ListenableFuture<SendResult<String, Object>> listenableFuture = template.send("demo-04-topic", customer);
        	CompletableFuture<SendResult<String, Object>> future = toCompletableFuture(listenableFuture);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + customer.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            customer.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }
    
    
}
