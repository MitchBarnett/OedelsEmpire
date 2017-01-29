package com.example.frogman.myapplication.Presenter;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import com.example.frogman.myapplication.Model.BoxCollider;
import com.example.frogman.myapplication.Model.CircleCollider;

/**
 * Created by frogman on 27/01/2017.
 */


public class Collision
{
    public static boolean isColliding(Point point, CircleCollider circle)
    {
        double dist = Math.pow((point.x-circle.center().x), 2) + Math.pow((point.y-circle.center().y), 2);

        return  dist < Math.pow(circle.radius() , 2);
    }

    public static boolean isColliding(Point point, BoxCollider box)
    {
        return  false;
    }

    public static boolean isColliding(BoxCollider boxA, BoxCollider boxB)
    {
        if(boxA.right() < boxB.left() || boxA.left() > boxB.right())
        {
            return false;
        }
        else if (boxA.bottom() < boxB.top() || boxA.top() > boxB.bottom())
        {
            return false;
        }

        return true;
    }

    public static boolean isColliding(BoxCollider box, CircleCollider circle)
    {
        Point circleDistance = new Point();
        circleDistance.x = Math.abs(circle.center().x - box.center().x);
        circleDistance.y = Math.abs(circle.center().y - box.center().y);

        if(circleDistance.x > (box.width()/2 + circle.radius())) {return false;}
        if(circleDistance.y > (box.height()/2 + circle.radius())) {return false;}

        if(circleDistance.x <= (box.width()/2)) {return true;}
        if(circleDistance.y <= (box.height()/2)) {return true;}

        double cornerDistanceSquare = Math.pow((circleDistance.x = box.width()/2),2) +
                Math.pow((circleDistance.y - box.height()/2), 2);
        return  (cornerDistanceSquare <= (Math.pow(circle.radius(),2)));
    }

    public static boolean isColliding(CircleCollider circleA, CircleCollider circleB)
    {
        int distX = Math.abs(circleA.center().x - circleB.center().x);
        int distY = Math.abs(circleA.center().y - circleB.center().y);

        int totalDist = distX * distX + distY * distY;

        float radiusSum = circleA.radius() + circleB.radius();

        return  totalDist <= radiusSum * radiusSum;


    }
}
