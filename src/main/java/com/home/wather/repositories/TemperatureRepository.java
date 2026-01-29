package com.home.wather.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.home.wather.models.TemperatureDocument;

import java.util.Optional;

@Repository
public interface TemperatureRepository extends MongoRepository<TemperatureDocument, String> {
    
    Optional<TemperatureDocument> findByKey(String key);
    
    @Query(sort = "{ 'timestamp' : -1 }")
    TemperatureDocument findTopByOrderByTimestampDesc();
}
