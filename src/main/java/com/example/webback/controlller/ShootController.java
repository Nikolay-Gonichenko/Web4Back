package com.example.webback.controlller;


import com.example.webback.converter.PointConverter;
import com.example.webback.dto.PointRequestDto;
import com.example.webback.dto.PointResponseDto;
import com.example.webback.entity.Point;
import com.example.webback.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShootController {
    @Autowired
    private PointConverter pointConverter;
    @Autowired
    private PointService pointService;

    @GetMapping("/user/start")
    public ResponseEntity<?> getAllPointsForUser() {
        List<Point> points = pointService.getAllForUser();
        List<PointResponseDto> pointsOut = new ArrayList<>();
        for (Point p:points) {
            pointsOut.add(pointConverter.pointResponseDtoFromPoint(p));
        }
        return new ResponseEntity<>(pointsOut, HttpStatus.OK);
    }

    @PostMapping("/user/shoot")
    public ResponseEntity<?> shoot(@RequestBody PointRequestDto pointRequestDto) {
        Point point = pointConverter.pointFromPointRequestDto(pointRequestDto);
        pointService.savePoint(point);
        PointResponseDto pointOut = pointConverter.pointResponseDtoFromPoint(point);
        return new ResponseEntity<>(pointOut, HttpStatus.OK);
    }
}
