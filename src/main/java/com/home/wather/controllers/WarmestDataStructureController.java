package com.home.wather.controllers;

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
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping(value = "/api/warmest", headers = "X-API-Version=1")
public class WarmestDataStructureController {

    private final WarmestDataStructureInterface warmestService;

    public WarmestDataStructureController(@Qualifier("warmestDataStructureService") WarmestDataStructureInterface warmestService) {
        this.warmestService = warmestService;
    }

    @PostMapping(value = "/put")
    public ResponseEntity<?> put(
            @RequestParam String key,
            @RequestParam int value) {
        Integer previousValue = warmestService.put(key, value);
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.STORED, key, value, previousValue));
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> get(@RequestParam String key) {
        Integer value = warmestService.get(key);
        
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.FOUND, key, value, null));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> remove(@RequestParam String key) {
        Integer removedValue = warmestService.remove(key);
        
        if (removedValue == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.REMOVED, key, removedValue, null));
    }

    @GetMapping(value = "/warmest")
    public ResponseEntity<?> getWarmest() {
        String warmestKey = warmestService.getWarmest();
        
        if (warmestKey == null) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(new WarmestResponse(ResponseStatus.WARMEST, warmestKey, null, null));
    }
}

