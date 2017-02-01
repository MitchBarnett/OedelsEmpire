package com.example.frogman.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by frogman on 26/01/2017.
 */

public class Trap extends Placeable {

    public Trap()
    {

    }

    @Override
    public void draw(Canvas c)
    {
        Paint p = new Paint();

        p.setColor(Color.GREEN);
        c.drawCircle(center.x, center.y, radius, p);
        p.setTextSize(30);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText("Trap",(float) this.center.x, (float) this.center.y, p);
    }
}
