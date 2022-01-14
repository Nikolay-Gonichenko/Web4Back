package com.example.webback.converter;

import com.example.webback.dto.PointRequestDto;
import com.example.webback.dto.PointResponseDto;
import com.example.webback.entity.Point;
import org.springframework.stereotype.Component;

@Component
public class PointConverter {

    public Point pointFromPointRequestDto(PointRequestDto pointRequestDto){
        return new Point(pointRequestDto.getX(), pointRequestDto.getY(), pointRequestDto.getR());
    }
    public PointResponseDto pointResponseDtoFromPoint(Point point){
        return new PointResponseDto(point.getX(), point.getY(),
                point.getR(), point.isResult(), point.getTimeOfSending(), point.getTimeOfExecuting());
    }
}
