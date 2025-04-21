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
}
