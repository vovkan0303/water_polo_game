package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;



public class Ne_myachi {
    float x, y;
    private float nx; // делаем приватной и только в этом файле мы можем вызвать эту переменную
    private float ny;
    public int w_myacha = 108;
    public int h_myacha = 129;
    int width_iz;
    int height_iz;

    Ne_myachi() {
        width_iz = height_iz = MathUtils.random(50, 200);
        this.x = SCR_WIDTH / 2;
        this.y = SCR_HEIGHT / 2;
        nx = MathUtils.random(-5f, 5);
        ny = MathUtils.random(-5f, 5);
    }


    public void dvizenie(){
        x += nx;
        y += ny;
        if (x + width_iz >= SCR_WIDTH || x <= 0) nx = -nx;
        if (y + height_iz >= SCR_HEIGHT || y <= 0) ny = -ny;

    }


    public boolean flip_mach_x(){
        return nx < 0;
    }


    public boolean flip_mach_y(){
        return ny < 0;
    }
}
