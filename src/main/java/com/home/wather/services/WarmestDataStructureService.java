package com.home.wather.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.home.wather.interfaces.WarmestDataStructureInterface;
import com.home.wather.models.Temperature;

// Keeps track of temperatures and remembers which one was accessed most recently
// Uses a HashMap for fast lookups and a doubly linked list to track the order
@Service
public class WarmestDataStructureService implements WarmestDataStructureInterface {

    private final Map<String, Temperature> map;
    private Temperature head;  
    private Temperature tail;  

    public WarmestDataStructureService() {
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    @Override
    public Integer put(String key, int value) {
        if (map.containsKey(key)) {
            Temperature tempareture = map.get(key);
            Integer previousValue = tempareture.value;
            tempareture.value = value;
            removeFromList(tempareture);
            addToHead(tempareture);
            return previousValue;
        }
        
        Temperature newTempareture = new Temperature(key, value);
        map.put(key, newTempareture);
        addToHead(newTempareture);
        return null;
    }

    @Override
    public Integer remove(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        
        Temperature tempareture = map.get(key);
        Integer value = tempareture.value;
        removeFromList(tempareture);
        map.remove(key);
        return value;
    }

    @Override
    public Integer get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        
        Temperature tempareture = map.get(key);
        removeFromList(tempareture);
        addToHead(tempareture);
        return tempareture.value;
    }

    @Override
    public String getWarmest() {
        return head != null ? head.key : null;
    }

    // Add to the front of the list
    private void addToHead(Temperature tempareture) {
        tempareture.prev = null;
        tempareture.next = head;
        
        if (head != null) {
            head.prev = tempareture;
        }
        head = tempareture;
        
        // If this is our first item, it's also the last one
        if (tail == null) {
            tail = tempareture;
        }
    }

    // Take it out of the list
    private void removeFromList(Temperature tempareture) {
        if (tempareture.prev != null) {
            tempareture.prev.next = tempareture.next;
        } else {
            // It was at the front
            head = tempareture.next;
        }
        
        if (tempareture.next != null) {
            tempareture.next.prev = tempareture.prev;
        } else {
            // It was at the end
            tail = tempareture.prev;
        }
    }
}
