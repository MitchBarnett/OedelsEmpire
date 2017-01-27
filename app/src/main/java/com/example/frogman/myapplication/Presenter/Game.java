package com.example.frogman.myapplication.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Debug;
import android.util.Log;

import com.example.frogman.myapplication.Model.Placeable;

import java.util.ArrayList;

/**
 * Created by frogman on 26/01/2017.
 */

public class Game {
    private Placeable placeableIcon;
    private ArrayList<Placeable> placeables;
    private boolean active;
    public Game()
    {
        placeables = new ArrayList<>();
        placeableIcon = new Placeable(100, 100, 100, 100, Color.RED);
    }

    public void pressUpdate(float xPos, float yPos)
    {
        if(!active && xPos > placeableIcon.shape.left && xPos < placeableIcon.shape.right &&
                yPos > placeableIcon.shape.top && yPos < placeableIcon.shape.bottom)
        {
            placeables.add(new Placeable(100, 100, 100, 100, Color.argb(50, 255, 0, 0)));
            active = true;
        }
        if(active)
        {
            int centerX = (int) xPos - (placeables.get(placeables.size()-1).getWidth() / 2);
            int centerY = (int) yPos - (placeables.get(placeables.size()-1).getHeight() / 2 );
            placeables.get(placeables.size()-1).setPos(centerX, centerY);
        }
    }

    public void pressRelease()
    {
        active = false;

        placeables.get(placeables.size()-1).setColour(Color.RED);
    }

    public void update()
    {
        return;
    }

    public void render(Canvas canvas)
    {
        placeableIcon.draw(canvas);
        for(Placeable item : placeables)
        {
            item.draw(canvas);
        }
    }
}
