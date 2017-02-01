package com.example.frogman.myapplication.Presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.frogman.myapplication.Model.BoxCollider;
import com.example.frogman.myapplication.Model.GameObject;
import com.example.frogman.myapplication.Model.Placeable;
import com.example.frogman.myapplication.Model.Tower;
import com.example.frogman.myapplication.Model.Trap;

import java.util.ArrayList;

/**
 * Created by frogman on 26/01/2017.
 */

public class Game {
    private GameObject placeableIcon;
    private GameObject trapIcon;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Tower> towers;
    private Placeable objectBeingPlaced = null;
    private Rect uiRect;
    private BoxCollider uiCollider;
    private Point screenSize;

    public Game(Point screenS)
    {
        screenSize = screenS;
        towers = new ArrayList<>();
        gameObjects = new ArrayList<>();
        placeableIcon = new GameObject();
        trapIcon = new GameObject();
        trapIcon.setColour(Color.GREEN);
        gameObjects.add(placeableIcon);
        gameObjects.add(trapIcon);
        placeableIcon.setPos(screenSize.x - 150, screenSize.y/2);
        trapIcon.setPos(screenSize.x - 150, screenSize.y/4);
        uiRect = new Rect(screenSize.x - 300, 0, screenSize.x, screenSize.y);
        uiCollider = new BoxCollider(uiRect);
    }

    public void pressUpdate(float xPos, float yPos)
    {
        Point pos = new Point((int) xPos, (int) yPos);
        if(Collision.isColliding(pos, placeableIcon.getCollider()) && objectBeingPlaced == null)
        {
            gameObjects.add(new Tower());
            objectBeingPlaced = (Placeable) gameObjects.get(gameObjects.size()-1);
            objectBeingPlaced.isBeingPlaced = true;
        }

        if(Collision.isColliding(pos, trapIcon.getCollider()) && objectBeingPlaced == null)
        {
            gameObjects.add(new Trap());
            objectBeingPlaced = (Trap) gameObjects.get(gameObjects.size()-1);
            objectBeingPlaced.isBeingPlaced = true;
        }


        if(objectBeingPlaced != null)
        {
            objectBeingPlaced.isColiding = false;
            objectBeingPlaced.setPos(pos);
            if(Collision.isColliding(uiCollider, objectBeingPlaced.getCollider()))
            {
               objectBeingPlaced.isColiding = true;
            }

            for(int i = 0; i < gameObjects.size() -1 ; i++)
            {
                if(Collision.isColliding(gameObjects.get(i).getCollider(),
                        objectBeingPlaced.getCollider()))
                {
                    objectBeingPlaced.isColiding = true;
                }
            }
        }
    }

    public void pressRelease()
    {
        if(objectBeingPlaced != null)
        {
            boolean colliding = false;
            if(Collision.isColliding(uiCollider, objectBeingPlaced.getCollider()))
            {
                colliding = true;
            }
            for (int i = 0; i < gameObjects.size() - 1; i++) {
                if (Collision.isColliding(gameObjects.get(i).getCollider(),
                        objectBeingPlaced.getCollider()))
                {
                    colliding = true;
                }

            }
            if (!colliding)
            {
                objectBeingPlaced.isBeingPlaced = false;
                objectBeingPlaced = null;
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
