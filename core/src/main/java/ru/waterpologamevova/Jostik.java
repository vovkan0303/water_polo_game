package ru.waterpologamevova;
import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
public class Jostik {
    public float x, y;
    public float width, height;

    public Jostik () {
        width = height = 200;
        x = SCR_WIDTH/8;
        y = SCR_HEIGHT/4;
    }


    public boolean isTouchInside(Vector3 t){
        return Math.pow(t.x-x, 2) + Math.pow(t.y-y, 2) <= Math.pow((width+150)/2, 2);
    }

    public float scrX(){
        return x-width/2;
    }

    public float scrY(float pos_cameri){
        y = pos_cameri;
        return pos_cameri-height/2;
    }
}
