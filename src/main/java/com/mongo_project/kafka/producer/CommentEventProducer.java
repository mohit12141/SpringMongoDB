package com.mongo_project.kafka.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.mongo_project.dto.CommentEvent;

@Service
public class CommentEventProducer {
    private KafkaTemplate<String, CommentEvent> kafkaTemplate;
    
    @Value("${kafka.topic.comment-events}")
    private String topic;

    public CommentEventProducer(KafkaTemplate<String, CommentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCommentEvent(CommentEvent commentEvent){

        CompletableFuture<SendResult<String, CommentEvent>> future = kafkaTemplate.send(topic, commentEvent.getMovieId() ,commentEvent);
        future.whenComplete((result, ex) -> {
            if(ex == null){
                System.out.println("Message sent successfully");

                System.out.println("Topic: " + result.getRecordMetadata().topic());
                System.out.println("Partition: " + result.getRecordMetadata().partition());
                System.out.println("Offset: " + result.getRecordMetadata().offset());
            } else {
                System.out.println("Failed to send message: " + ex.getMessage());
            }
        });
    }
}
