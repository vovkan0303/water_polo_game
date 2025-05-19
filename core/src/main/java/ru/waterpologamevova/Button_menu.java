package ru.waterpologamevova;

public class Button_menu {
    float x, y;
    float width, height;


    public Button_menu(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    boolean hit(float tx, float ty){
        return x < tx && tx < x + width && y < ty && ty < y + height;
    }
    public float scrX(){
        return x-width/2;
    }

    public float scrY(float pos_cameri){
        y = pos_cameri;
        return pos_cameri-height/2;
    }
}
