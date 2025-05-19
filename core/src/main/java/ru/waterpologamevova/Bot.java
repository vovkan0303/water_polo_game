package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_WIDTH;
import static ru.waterpologamevova.Standart.WORLD_HEIHGT;

import com.badlogic.gdx.math.Vector3;

public class Bot {
    public float x, y;
    public float width, height;
    public boolean side;
    float vx;
    float vy;

    public Bot (float x,float y) {
        width = height = 100;
        this.x = x;
        this.y = y;
    }

    public float scrX(){
        return x-width/2;
    }

    public float scrY(){
        return y-height/2;
    }

    public void stop(){
        vx = 0;
        vy = 0;
    }
    public void move() {
        outOfScreen();
        if (vx >= 2.5f) vx = 2.5f;
        if (vy >= 2.5f) vy = 2.5f;
        x += vx;
        y += vy;
    }
    private void outOfScreen(){
        if(x<width/2) {
            vx = 0;
            x = width/2;
        }
        if(x>SCR_WIDTH-width/2) {
            vx = 0;
            x = SCR_WIDTH-width/2;
        }
        if(y< 180 + height/2) {
            vy = 0;
            y = 180 + height/2;
        }
        if(y + 180>WORLD_HEIHGT-height/2) {
            vy = 0;
            y   = WORLD_HEIHGT-height/2 - 180;
        }
    }

}
