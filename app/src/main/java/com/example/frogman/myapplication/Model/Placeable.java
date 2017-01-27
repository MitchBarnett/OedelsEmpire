package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by frogman on 26/01/2017.
 */

public class Placeable {
    public Rect shape;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int colour;

    public Placeable(int xPosIn, int yPosIn, int widthIn, int heightIn, int colourIn)
    {
        xPos = xPosIn;
        yPos = yPosIn;
        width = widthIn;
        height = heightIn;
        colour = colourIn;
        shape = new Rect(xPosIn, yPosIn, xPosIn+ widthIn, yPosIn + heightIn);
    }

    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(colour);
        c.drawRect(shape, p);
    }

    public void setPos(int xPosIn, int yPosIn)
    {
        xPos = xPosIn;
        yPos = yPosIn;
        shape.left = xPosIn;
        shape.right = xPosIn + width;
        shape.top = yPosIn;
        shape.bottom = yPosIn + height;

    }
    public void setColour(int colourIn)
    {
        colour = colourIn;
    }

    public int getWidth()
    {
        return  width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int widthIn)
    {
        width = widthIn;
        shape.right = xPos + widthIn;
    }

    public void setHeight(int heightIn)
    {
        height = heightIn;
        shape.bottom = yPos + heightIn;
    }
}
