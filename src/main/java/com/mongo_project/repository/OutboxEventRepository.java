package com.mongo_project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo_project.entity.OutBoxEvent;

public interface OutboxEventRepository extends MongoRepository<OutBoxEvent, String>{
    List<OutBoxEvent> findBySentFalse();
}
