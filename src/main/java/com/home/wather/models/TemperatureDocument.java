package com.home.wather.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "temperatures")
public class TemperatureDocument {
    
    @Id
    private String id;
    
    private String key;
    
    private Integer value;
    
    private Long timestamp;

    public TemperatureDocument(String key, Integer value, Long timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.id = key;
    }
}
