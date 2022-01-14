package com.example.webback.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "mypoints")
@Data
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double x;
    private double y;
    private double r;
    private boolean result;
    @Column(name = "timeofsending")
    private String timeOfSending;
    @Column(name = "timeofexecuting")
    private String timeOfExecuting;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;



    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        checkPoint();
    }

    public void checkPoint() {
        double x = this.x;
        double y = this.y;
        double r = this.r;
        this.result = (x >= -r && x <= 0 && y <= 0 && y >= -r) || //rect
                (x * x + y * y <= r * r && x <= 0 && y >= 0) || //arc
                (y >= 0.5*x - r/2 && y <= 0 && y >= -r/2 && x >= 0 && x <= r); //triangle
    }

}
