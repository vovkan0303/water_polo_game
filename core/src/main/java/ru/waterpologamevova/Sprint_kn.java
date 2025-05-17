package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.math.Vector3;

public class Sprint_kn{
    public float x, y;
    public float width, height;

    public Sprint_kn () {
        width = height = 130;
        x = SCR_WIDTH*0.93f;
        y = SCR_HEIGHT*0.40f;
    }


    public boolean isTouchInside(Vector3 t){
        return Math.pow(t.x-x, 2) + Math.pow(t.y-y, 2) <= Math.pow((width)/2, 2);
    }

    public float scrX(){
        return x-width/2;
    }

    public float scrY(float pos_cameri){
        y = pos_cameri;
        return pos_cameri-height/2;
    }
}
