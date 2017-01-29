package com.example.frogman.myapplication.Model;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by frogman on 31/01/2017.
 */

public class BoxCollider extends Collider {
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int width;
    private int height;
    private Point center;

    public  BoxCollider(Rect r)
    {
        this.left = r.left;
        this.right = r.right;
        this.top = r.top;
        this.bottom = r.bottom;
        this.width = calcWidth();
        this.height = calcHeight();
        this.center = new Point(r.centerX(), r.centerY());

    }
    public BoxCollider(int left, int right, int top, int bottom)
    {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.width = calcWidth();
        this.height = calcHeight();
        this.center = calcCenter();
    }

    private Point calcCenter()
    {
        return new Point((this.right - this.left)/2 , (this.bottom - this.top)/2 );
    }
    private int calcWidth(){return right - left;}
    private int calcHeight(){return bottom - top;}
    public int left() {return left;}
    public int right() {return right;}
    public int top() {return top;}
    public int bottom() {return bottom;}
    public int width() {return width;}
    public int height() {return height;}
    public Point center() {return center;}

    public void setLeft(int left) {
        this.left = left;
        this.center = calcCenter();
        this.width = calcWidth();
        this.height = calcHeight();
    }
    public void setRight(int right) {
        this.right = right;
        this.center = calcCenter();
        this.width = calcWidth();
        this.height = calcHeight();
    }
    public void setTop(int top) {
        this.top = top;
        this.center = calcCenter();
        this.width = calcWidth();
        this.height = calcHeight();
    }
    public void setBottom(int bottom){
        this.bottom = bottom;
        this.center = calcCenter();
        this.width = calcWidth();
        this.height = calcHeight();
    }

    public void setCenter(Point center) {
        this.center = center;
    }
}
