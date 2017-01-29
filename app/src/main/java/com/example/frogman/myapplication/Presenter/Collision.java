package com.example.frogman.myapplication.Presenter;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by frogman on 27/01/2017.
 */

public class Collision
{
    public static boolean isColliding(Point point, Point center, float radius)
    {
        double dist = Math.pow((point.x-center.x), 2) + Math.pow((point.y-center.y), 2);

        return  dist < Math.pow(radius , 2);
    }

    public static boolean isColliding(Point point, Rect rect)
    {
        return  false;
    }

    public static boolean isColliding(Rect rectA, Rect rectB)
    {
        if(rectA.right < rectB.left || rectA.left > rectB.right)
        {
            return false;
        }
        else if (rectA.bottom < rectB.top || rectA.top > rectB.bottom)
        {
            return false;
        }

        return true;
    }

    public static boolean isColliding(Rect rect, Point center, float radius)
    {
        Point circleDistance = new Point();
        circleDistance.x = Math.abs(center.x - rect.centerX());
        circleDistance.y = Math.abs(center.y - rect.centerY());

        if(circleDistance.x > (rect.width()/2 + radius)) {return false;}
        if(circleDistance.y > (rect.height()/2 + radius)) {return false;}

        if(circleDistance.x <= (rect.width()/2)) {return true;}
        if(circleDistance.y <= (rect.height()/2)) {return true;}

        double cornerDistanceSquare = Math.pow((circleDistance.x = rect.width()/2),2) +
                Math.pow((circleDistance.y - rect.height()/2), 2);
        return  (cornerDistanceSquare <= (Math.pow(radius,2)));
    }

    public static boolean isColliding(Point centerA, float radiusA, Point centerB, float radiusB)
    {
        int distX = Math.abs(centerA.x - centerB.x);
        int distY = Math.abs(centerA.y - centerB.y);

        int totalDist = distX * distX + distY * distY;

        float radiusSum = radiusA + radiusB;

        return  totalDist <= radiusSum * radiusSum;


    }
}
