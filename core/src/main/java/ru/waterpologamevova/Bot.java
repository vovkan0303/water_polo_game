package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;
import static ru.waterpologamevova.Standart.WORLD_HEIHGT;

import com.badlogic.gdx.math.Vector3;

public class Bot {
    public float x, y;
    public float width, height;
    public boolean side;
    float vx = 1;
    float vy = 0.25f;
    int pos; //0-6

    public Bot (float x,float y, int pos_bot) {
        width = height = 100;
        this.x = x;
        this.y = y;
        pos = pos_bot;
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
    public void dviz_vrat(int kto){
        if (x <= 450 || x >=800){
            vx = -vx;
        }
        if (kto == 1){
            if (y < SCR_HEIGHT/2 -50 || y >= SCR_HEIGHT/2 + 50){
                vy = -vy;
            }
        }
        else {
            if (y < WORLD_HEIHGT - SCR_HEIGHT/2 -50 || y >= WORLD_HEIHGT - SCR_HEIGHT/2 + 50){
                vy = -vy;
            }
        }


    }
    public void move() {
        outOfScreen();
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
    }

}
