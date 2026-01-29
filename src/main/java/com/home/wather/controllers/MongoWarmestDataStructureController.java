package com.home.wather.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.wather.interfaces.WarmestDataStructureInterface;
import com.home.wather.responses.ResponseStatus;
import com.home.wather.responses.WarmestResponse;

@RestController
@RequestMapping(value = "/api/warmest-db", headers = "X-API-Version=1")
public class MongoWarmestDataStructureController {

    private final WarmestDataStructureInterface mongoWarmestService;

    public MongoWarmestDataStructureController(@Qualifier("mongoWarmestService") WarmestDataStructureInterface mongoWarmestService) {
        this.mongoWarmestService = mongoWarmestService;
    }

    @PostMapping(value = "/put")
    public ResponseEntity<?> put(
            @RequestParam String key,
            @RequestParam int value) {
        Integer previousValue = mongoWarmestService.put(key, value);
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.STORED, key, value, previousValue));
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> get(@RequestParam String key) {
        Integer value = mongoWarmestService.get(key);
        
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.FOUND, key, value, null));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> remove(@RequestParam String key) {
        Integer removedValue = mongoWarmestService.remove(key);
        
        if (removedValue == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.REMOVED, key, removedValue, null));
    }

    @GetMapping(value = "/warmest")
    public ResponseEntity<?> getWarmest() {
        String warmestKey = mongoWarmestService.getWarmest();
        
        if (warmestKey == null) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.WARMEST, warmestKey, null, null));
    }
}
