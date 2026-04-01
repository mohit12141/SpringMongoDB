package com.mongo_project.helper;

import java.time.Instant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongo_project.dto.CommentEvent;
import com.mongo_project.kafka.producer.CommentEventProducer;

@Component
public class KafkaTestRunner implements CommandLineRunner{
    private final CommentEventProducer producer;

    public KafkaTestRunner(CommentEventProducer producer) {
        this.producer = producer;
    }

    @Override
    public void run(String... args) throws Exception {

        CommentEvent event = new CommentEvent(
                "c1",
                "m1",
                "test@gmail.com",
                Instant.now()
        );

        producer.sendCommentEvent(event);
    }
}
