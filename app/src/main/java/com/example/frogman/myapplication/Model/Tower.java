package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by frogman on 26/01/2017.
 */


public class Tower extends Placeable {

    private  int range = 250;
    int rangeColour = Color.argb(175, 75, 75, 75);
    private  boolean showRange = true;

    public Tower()
    {

    }

    public void showRange(boolean b) {showRange = b;}

    public void draw(Canvas c)
    {
        Paint p = new Paint();

        if(showRange)
        {
            p.setColor(rangeColour);
            c.drawCircle(center.x, center.y, range, p);
        }
        p.setColor(Color.RED);
        c.drawCircle(center.x, center.y, radius, p);
    }

    public void setRangeColour(int colourIn)
    {
        rangeColour = colourIn;
    }
}
