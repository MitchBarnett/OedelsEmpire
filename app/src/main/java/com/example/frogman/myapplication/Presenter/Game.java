package com.example.frogman.myapplication.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Debug;
import android.util.Log;

import com.example.frogman.myapplication.Model.Placeable;
import com.example.frogman.myapplication.Model.Tower;

import java.util.ArrayList;

/**
 * Created by frogman on 26/01/2017.
 */

public class Game {
    private Placeable placeableIcon;
    private ArrayList<Tower> towers;
    private boolean placementInProgress = false;
    private Rect uiRect;
    private Point screenSize;

    public Game(Point screenS)
    {
        screenSize = screenS;
        towers = new ArrayList<>();
        placeableIcon = new Placeable();
        placeableIcon.setPos(screenSize.x - 150, screenSize.y/2);
        uiRect = new Rect(screenSize.x - 300, 0, screenSize.x, screenSize.y);
    }

    public void pressUpdate(float xPos, float yPos)
    {
        Point pos = new Point((int) xPos, (int) yPos);
        if(Collision.isColliding(pos, placeableIcon.getCenter() , placeableIcon.getRadius()) && !placementInProgress)
        {
            towers.add(new Tower());
            placementInProgress = true;
        }
        if(placementInProgress)
        {
            towers.get(towers.size()-1).setPos(pos);
            towers.get(towers.size()-1).setRangeColour(Color.argb(175, 75, 75, 75));
            if(Collision.isColliding(uiRect, towers.get(towers.size()-1).getCenter(),  towers.get(towers.size()-1).getRadius()))
            {
                towers.get(towers.size()-1).setRangeColour(Color.argb(175, 75, 0, 0));
            }

            for(int i = 0; i < towers.size() -1 ; i++)
            {
                if(Collision.isColliding(towers.get(i).getCenter(), towers.get(i).getRadius(),
                        towers.get(towers.size()-1).getCenter(),  towers.get(towers.size()-1).getRadius()))
                {
                    towers.get(towers.size()-1).setRangeColour(Color.argb(175, 75, 0, 0));
                }
            }
        }
    }

    public void pressRelease() {
        boolean colliding = false;
        if(Collision.isColliding(uiRect, towers.get(towers.size()-1).getCenter(),  towers.get(towers.size()-1).getRadius()))
        {
            colliding = true;
        }
        for (int i = 0; i < towers.size() - 1; i++) {
            if (Collision.isColliding(towers.get(i).getCenter(), towers.get(i).getRadius(),
                    towers.get(towers.size() - 1).getCenter(), towers.get(towers.size() - 1).getRadius()))
            {
                colliding = true;
            }

        }
        if (!colliding)
        {
            placementInProgress = false;
            towers.get(towers.size() - 1).showRange(false);
        }
    }

    public void update()
    {
        return;
    }

    public void render(Canvas canvas)
    {
        Paint p = new Paint();
        canvas.drawRect(uiRect, p);
        placeableIcon.draw(canvas);
        for(Tower item : towers)
        {
            if (item.isVisible()) {item.draw(canvas);}
        }
    }
}
