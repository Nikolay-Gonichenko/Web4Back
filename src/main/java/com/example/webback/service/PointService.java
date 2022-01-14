package com.example.webback.service;

import com.example.webback.entity.Point;
import com.example.webback.entity.User;
import com.example.webback.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointService {
    @Autowired
    PointRepository pointRepository;
    @Autowired
    UserService userService;

    public void savePoint(Point point) {
        long startTime = System.nanoTime();
        point.setTimeOfSending(LocalDateTime.now().toString());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        point.setUser(user);
        point.setTimeOfExecuting(String.valueOf((System.nanoTime() - startTime) / 1000000000d));
        pointRepository.save(point);
    }

    public List<Point> getAllForUser(){
        return pointRepository.findAllByUser_Id(userService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()).getId());
    }
}
