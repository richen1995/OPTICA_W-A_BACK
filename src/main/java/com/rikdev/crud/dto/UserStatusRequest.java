package com.rikdev.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserStatusRequest {
    @JsonProperty("is_active")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }
}
