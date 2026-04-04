package com.mongo_project.kafka.producer;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongo_project.entity.OutBoxEvent;
import com.mongo_project.repository.OutboxEventRepository;

@Component
public class OutBoxPublisher {
    
    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutBoxPublisher(OutboxEventRepository outboxEventRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxEventRepository = outboxEventRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000) // Run every 5 seconds
    public void publishEvents() {
        
        List<OutBoxEvent> events = outboxEventRepository.findBySentFalse();
        
        for(OutBoxEvent event : events) {
            try {
                kafkaTemplate.send(event.getTopic(), event.getPayload());
                event.setSent(true);
                outboxEventRepository.save(event);
            } catch (Exception e) {
                System.out.println("Kafka still down, will retry later. Error: " + e.getMessage());
            }
        }
    }
}
