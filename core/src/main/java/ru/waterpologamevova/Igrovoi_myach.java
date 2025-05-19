package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;
import static ru.waterpologamevova.Standart.WORLD_HEIHGT;

import com.badlogic.gdx.math.Vector3;

public class Igrovoi_myach {
    public float x, y;
    public float width, height;
    public int u_kogo;       //sam = 0, u_igroka = 1, u_npc = 2; gol = 3; auto_gol = 4;
    public float vx, vy;
    public float vx_bud, vy_bud;

    public Igrovoi_myach (float x, float y) {
        width = height = 80;
        this.x = x;
        this.y = y;
    }

    public void move(int gde) {
        dvizenie();
        u_kogo = gde;
        if (u_kogo == 0){
            if ((vx >= -0.07f) && (vx <= 0.07f)){
                vx = 0;
                }
            if ((vy >= -0.07f) && (vy <= 0.07f)){
                vy = 0;
                }
            else {
                if (vx > 0){
                    vx = vx - 0.005f;
                    }
                else{
                    vx = vx + 0.005f;
                    }
                if (vy > 0){
                    vy = vy - 0.005f;
                    }
                else{
                    vy = vy + 0.005f;
                    }
                }
            }

        if (u_kogo == 1){

        }
        if (u_kogo == 2){

        }
        x += vx;
        y += vy;
    }


    public void pridat_skor(float vx, float vy){
        this.vx = vx;
        this.vy = vy;
    }

    public void dvizenie(){
        if (x + width >= SCR_WIDTH || x <= 0) {
            vx = -vx;
        }
        if ((x < 470 || x > 560 + 200) &&((y < 180) || (y + width > WORLD_HEIHGT-180))){
            vy = -vy;
        }
        else {
            if (y + width < 150) {
                vy = 0;
                vx = 0;
                u_kogo = 4;
            }
            else if ((y + width + 30 > WORLD_HEIHGT)) {
                vy = 0;
                vx = 0;
                u_kogo = 3;
            }
        }

    }

    public void u_igroka(float x_pl, float y_pl, boolean derzit, float kuda_vx, float kuda_vy){
        if (derzit == false){
            if (kuda_vx > 0) x = x + 100;
            else x = x - 100;
            if (kuda_vy > 0) y = y + 100;
            else y = y - 100;

        }
        else {
            this.x = x_pl + 80;
            this.y = y_pl + 20;
        }
    }
    public void touchJoystick_udar(Vector3 t, Udar_kn u){
        vx_bud = (t.x-u.x)/15;
        vy_bud = (t.y-u.y)/15;

    }
    public void otpustil(float vx_bud, float vy_bud){
        pridat_skor(vx_bud, vy_bud);
        u_igroka(scrX(), scrY(), false, vx_bud, vy_bud);
    }




    public float scrX(){
        return x-width/2;
    }

    public float scrY(){
        return y-height/2;
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
        if(y<height/2) {
            vy = 0;
            y = height/2;
        }
        if(y>SCR_HEIGHT-height/2) {
            vy = 0;
            y = SCR_HEIGHT-height/2;
        }
    }
}


