package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by frogman on 26/01/2017.
 */

public class GameObject  implements Collidable{
    protected Point center = new Point();
    protected float radius = 100;
    private boolean visible = true;
    public int colour = Color.RED;
    private CircleCollider collider;
    private boolean updateable = false;
    private boolean collidable = false;

    public GameObject()
    {
        collider = new CircleCollider(center, radius);
    }

    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(colour);
        c.drawCircle(center.x, center.y, radius, p);
    }

    public void update(){}


    public void setPos(int xPosIn, int yPosIn)
    {
        center.set(xPosIn, yPosIn);
    }

    public void setPos(Point posIn)
    {
        this.center = posIn;
        collider.setCenter(this.center);
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
        this.radius = radiusIn;
    }

    public boolean isVisible() {return visible;}

    public void setVisible(boolean b) {this.visible = b;}

    public boolean isUpdateable() {return updateable;}

    public void setCollidable(boolean collidable) {this.collidable = collidable;}

    public void setUpdateable(boolean updateable) {this.updateable = updateable;}

    public boolean isCollidable() {return collidable;}

    public CircleCollider  getCollider() {
        return this.collider;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
}
