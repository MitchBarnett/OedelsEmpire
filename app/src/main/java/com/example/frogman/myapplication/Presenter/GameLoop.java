package com.example.frogman.myapplication.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Process;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.frogman.myapplication.Presenter.Game;


/**
 * Created by frogman on 25/01/2017.
 */

public class GameLoop extends SurfaceView implements Runnable{

    private Game game;               // The game
    private Point           screenSize;         // Holds the screen size
    private SurfaceHolder   holder;             // Surface holder for the canvas
    private boolean         running = false;    // Boolean to control pause and resume
    private Thread          gameThread = null;  // Thread for the game logic

    private int             skippedFrames = 0;  // The amount of frame renders that have been skipped
    private int             targetFPS = 30;     // The target / maximum frame rate
    private int             frameTime = 1000 / targetFPS;   // Number of milliseconds for a frame
    private long            startTime;          // Start time of a frame
    private long            deltaTime;          // Time taken to update and render a frame
    private long            sleepTime ;         // Time to wait to keep framerate constant


    public GameLoop(Context context, Point screenS)
    {
        super(context);
        holder = getHolder();
        screenSize = screenS;
        game = new Game(screenS);
    }

    public void pressUpdate(float xPos, float yPos)
    {
        game.pressUpdate(xPos, yPos);
    }

    public void pressRelease()
    {
        game.pressRelease();
    }

    private void updateCanvas ()
    {
        game.update();
    }

    protected void drawCanvas(Canvas canvas)
    {
        canvas.drawARGB(255, 100, 100, 0); //Add a background colour
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        canvas.drawText("Hello World!", 0, 100, paint);
        game.render(canvas);
    }

    public void run()
    {
        //Remove conflict between the UI thread and the game thread.
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

        while (running){
            //perform canvas drawing
            if(!holder.getSurface().isValid()){continue;}//skip anything below it
            startTime = System.currentTimeMillis();
            skippedFrames = 0;
            Canvas c = holder.lockCanvas();

            this.updateCanvas();
            this.drawCanvas(c);


            deltaTime = (System.currentTimeMillis() - startTime);
            sleepTime = frameTime - deltaTime; // Time to wait
            if(sleepTime > 0 )
                try {
                    gameThread.sleep(sleepTime);
                }
                catch (InterruptedException e){
                }

            while(sleepTime < 0 && skippedFrames < 20)
            {
                updateCanvas();
                sleepTime += frameTime;
                skippedFrames++;
            }
            holder.unlockCanvasAndPost(c);
        }
    }


    public void pause()
    {
        running = false;
        while(true){
            try{
                gameThread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            break;
        }
        gameThread = null;
    }

    public void resume(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


}

