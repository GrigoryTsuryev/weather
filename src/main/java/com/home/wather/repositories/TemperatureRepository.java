package com.home.wather.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.home.wather.models.TemperatureDocument;

@Repository
public interface TemperatureRepository extends MongoRepository<TemperatureDocument, String> {
    
    Optional<TemperatureDocument> findByKey(String key);
    
    @Query(sort = "{ 'timestamp' : -1 }")
    TemperatureDocument findTopByOrderByTimestampDesc();
}
