package com.home.wather.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.home.wather.interfaces.WarmestDataStructureInterface;
import com.home.wather.models.TemperatureDocument;
import com.home.wather.repositories.TemperatureRepository;

@Service("mongoWarmestService")
public class MongoWarmestDataStructureService implements WarmestDataStructureInterface {

    private final TemperatureRepository temperatureRepository;

    public MongoWarmestDataStructureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public Integer put(String key, int value) {
        Optional<TemperatureDocument> existing = temperatureRepository.findByKey(key);
        
        Integer previousValue = null;
        Long currentTimestamp = System.currentTimeMillis();
        
        if (existing.isPresent()) {
            TemperatureDocument doc = existing.get();
            previousValue = doc.getValue();
            doc.setValue(value);
            doc.setTimestamp(currentTimestamp);
            temperatureRepository.save(doc);
        } else {
            TemperatureDocument newDoc = new TemperatureDocument(key, value, currentTimestamp);
            temperatureRepository.save(newDoc);
        }
        
        return previousValue;
    }

    @Override
    public Integer remove(String key) {
        Optional<TemperatureDocument> existing = temperatureRepository.findByKey(key);
        
        if (!existing.isPresent()) {
            return null;
        }
        
        TemperatureDocument doc = existing.get();
        Integer value = doc.getValue();
        temperatureRepository.deleteById(key);
        return value;
    }

    @Override
    public Integer get(String key) {
        Optional<TemperatureDocument> existing = temperatureRepository.findByKey(key);
        
        if (!existing.isPresent()) {
            return null;
        }
        
        TemperatureDocument doc = existing.get();
        // Update timestamp to mark as most recently accessed
        doc.setTimestamp(System.currentTimeMillis());
        temperatureRepository.save(doc);
        
        return doc.getValue();
    }

    @Override
    public String getWarmest() {
        TemperatureDocument doc = temperatureRepository.findTopByOrderByTimestampDesc();
        return doc != null ? doc.getKey() : null;
    }
}
