package com.home.wather.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarmestResponse {
    private ResponseStatus status;
    private String key;
    private Integer value;
    private Integer previousValue;
}
