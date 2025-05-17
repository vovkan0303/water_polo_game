package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;
import static ru.waterpologamevova.Standart.WORLD_HEIHGT;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Player {
    public float x, y;
    public float width, height;
    public boolean side;
    float nx;
    float ny;

    public Player (float x,float y) {
        width = height = 100;
        this.x = x;
        this.y = y;
    }


    public boolean isTouchInside(Vector3 t){
        return Math.pow(t.x-x, 2) + Math.pow(t.y-y, 2) <= Math.pow(width/2, 2);
    }

    public float scrX(){
        return x-width/2;
    }

    public float scrY(){
        return y-height/2;
    }

    public void stop(){
        nx = 0;
        ny = 0;
    }
    public void move() {
        outOfScreen();
        if (nx >= 2.5f) nx = 2.5f;
        if (ny >= 2.5f) ny = 2.5f;
        x += nx;
        y += ny;
        //System.out.println(nx + "-nx");
        //System.out.println(nx + "-ny");
    }
    public void sprint(){
        float sprint_v = 1.1f;
        nx = nx * sprint_v ;
        ny = ny * sprint_v;
        move();
    }
    private void outOfScreen(){
        if(x<width/2) {
            nx = 0;
            x = width/2;
        }
        if(x>SCR_WIDTH-width/2) {
            nx = 0;
            x = SCR_WIDTH-width/2;
        }
        if(y< 180 + height/2) {
            ny = 0;
            y = 180 + height/2;
        }
        if(y + 180>WORLD_HEIHGT-height/2) {
            ny = 0;
            y   = WORLD_HEIHGT-height/2 - 180;
        }
    }

    public void touchJoystick(Vector3 t, Jostik j){
        nx = (t.x-j.x)/50;
        ny = (t.y-j.y)/50;
    }
}
