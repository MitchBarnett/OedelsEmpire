package com.example.frogman.myapplication.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Debug;
import android.util.Log;

import com.example.frogman.myapplication.Model.BoxCollider;
import com.example.frogman.myapplication.Model.GameObject;
import com.example.frogman.myapplication.Model.Tower;

import java.util.ArrayList;

/**
 * Created by frogman on 26/01/2017.
 */

public class Game {
    private GameObject placeableIcon;
    private  ArrayList<GameObject> gameObjects;
    private ArrayList<Tower> towers;
    private Tower towerBeingPlaced = null;
    private Rect uiRect;
    private BoxCollider uiCollider;
    private Point screenSize;

    public Game(Point screenS)
    {
        screenSize = screenS;
        towers = new ArrayList<>();
        gameObjects = new ArrayList<>();
        placeableIcon = new GameObject();
        gameObjects.add(placeableIcon);
        placeableIcon.setPos(screenSize.x - 150, screenSize.y/2);
        uiRect = new Rect(screenSize.x - 300, 0, screenSize.x, screenSize.y);
        uiCollider = new BoxCollider(uiRect);
    }

    public void pressUpdate(float xPos, float yPos)
    {
        Point pos = new Point((int) xPos, (int) yPos);
        if(Collision.isColliding(pos, placeableIcon.getCollider()) && towerBeingPlaced == null)
        {
            gameObjects.add(new Tower());
            towerBeingPlaced = (Tower) gameObjects.get(gameObjects.size()-1);
        }
        if(towerBeingPlaced != null)
        {
           towerBeingPlaced.setPos(pos);
            towerBeingPlaced.setRangeColour(Color.argb(175, 75, 75, 75));
            if(Collision.isColliding(uiCollider, towerBeingPlaced.getCollider()))
            {
               towerBeingPlaced.setRangeColour(Color.argb(175, 75, 0, 0));
            }

            for(int i = 0; i < gameObjects.size() -1 ; i++)
            {
                if(Collision.isColliding(gameObjects.get(i).getCollider(),
                        towerBeingPlaced.getCollider()))
                {
                    towerBeingPlaced.setRangeColour(Color.argb(175, 75, 0, 0));
                }
            }
        }
    }

    public void pressRelease()
    {
        if(towerBeingPlaced != null)
        {
            boolean colliding = false;
            if(Collision.isColliding(uiCollider, towerBeingPlaced.getCollider()))
            {
                colliding = true;
            }
            for (int i = 0; i < gameObjects.size() - 1; i++) {
                if (Collision.isColliding(gameObjects.get(i).getCollider(),
                        towerBeingPlaced.getCollider()))
                {
                    colliding = true;
                }

            }
            if (!colliding)
            {
                towerBeingPlaced.showRange(false);
                towerBeingPlaced = null;
            }
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
        for(GameObject item : gameObjects)
        {
            if (item.isVisible()) {item.draw(canvas);}
        }
    }
}
