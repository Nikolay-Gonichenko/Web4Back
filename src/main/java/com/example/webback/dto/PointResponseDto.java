package com.example.webback.dto;

import lombok.Data;

@Data
public class PointResponseDto {
    private double x;

    private double y;

    private double r;

    private boolean result;

    private String timeOfSending;

    private String timeOfExecuting;

    public PointResponseDto(double x, double y, double r, boolean result, String timeOfSending, String timeOfExecuting) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.timeOfSending = timeOfSending;
        this.timeOfExecuting = timeOfExecuting;
    }
}
