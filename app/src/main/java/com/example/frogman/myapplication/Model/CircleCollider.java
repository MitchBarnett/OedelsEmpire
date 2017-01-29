package com.example.frogman.myapplication.Model;

import android.graphics.Point;

/**
 * Created by frogman on 31/01/2017.
 */

public class CircleCollider extends Collider{
    private Point center;
    private Float radius;

    public CircleCollider(Point center, float radius)
    {
        this.center = center;
        this.radius = radius;
    }


    public Point center() {return center;}

    public Float radius() {return radius;}


    public void setCenter(Point center) {
        this.center = center;
    }

}
