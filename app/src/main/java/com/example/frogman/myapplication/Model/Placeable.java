package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by frogman on 26/01/2017.
 */

public class Placeable {
    private Rect shape;
    private int xPos;
    private int yPos;
    private int colour;

    public Placeable(Rect shapeIn, int xPosIn, int yPosIn, int colourIn)
    {
        shape = shapeIn;
        xPos = xPosIn;
        yPos = yPosIn;
        colour = colourIn;
    }

    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(colour);
        c.drawRect(shape, p);
    }
}
