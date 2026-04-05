package com.mongo_project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "outbox_events")
public class OutBoxEvent {
    @Id
    private String id;

    private String topic;
    private String key;
    private String payload;
    private boolean sent;
    
    public OutBoxEvent() {
    }

    public OutBoxEvent(String topic, String key, String payload, boolean sent) {
        this.topic = topic;
        this.key = key;
        this.payload = payload;
        this.sent = sent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    
}
