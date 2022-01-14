package com.example.webback.repository;

import com.example.webback.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point,Integer> {
    List<Point> findAllByUser_Id(Long userId);
}
