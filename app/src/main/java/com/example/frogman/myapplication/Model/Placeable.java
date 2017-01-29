package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by frogman on 26/01/2017.
 */

public class Placeable {
    protected Point center = new Point();
    protected float radius = 100;
    private boolean visible = true;
    public Placeable()
    {

    }

    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(Color.RED);
        c.drawCircle(center.x, center.y, radius, p);
    }


    public void setPos(int xPosIn, int yPosIn)
    {
        center.set(xPosIn, yPosIn);
    }

    public void setPos(Point posIn)
    {
        center = posIn;
    }

    public Point getCenter()
    {
        return center;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radiusIn)
    {
        radius = radiusIn;
    }

    public boolean isVisible() {return visible;}

    public void setVisible(boolean b) {visible = b;}

}
