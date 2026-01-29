package com.home.wather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Temperature {
    public String key;
    public Integer value;
    public Temperature prev;
    public Temperature next;

    public Temperature(String key, Integer value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}
