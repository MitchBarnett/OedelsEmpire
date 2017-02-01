package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by frogman on 26/01/2017.
 */


public class Tower extends Placeable {

    private  int range = 250;
    int placableColor = Color.argb(175, 75, 75, 75);
    int blockedColor = Color.argb(175, 75,0 ,0);

    public Tower()
    {

    }

    public void draw(Canvas c)
    {
        Paint p = new Paint();

        if(isBeingPlaced)
        {
            if(isColiding){
                p.setColor(blockedColor);}
            else {
                p.setColor(placableColor);}
            c.drawCircle(center.x, center.y, range, p);
        }
        p.setColor(Color.RED);
        c.drawCircle(center.x, center.y, radius, p);
        p.setTextSize(30);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText("Tower",(float) this.center.x, (float) this.center.y, p);
    }

}
