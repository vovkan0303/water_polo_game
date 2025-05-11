package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vratar_in_penalti {
    float sk = 1.5f;
    float width_vr = 446/sk, height_vr = 598/sk;
    float width_prav = 400/sk, height_prav = 400/sk;
    float width_lev = 400/sk, height_lev = 400/sk;
    float x_vr = SCR_WIDTH /2 - width_vr/2, y_vr=-90, rotation_vr=0;
    float x_prav = x_vr + 200, y_prav = y_vr + height_vr/2.2f , rotation_prav = -90;
    float x_lev = x_vr - width_lev/1.5f, y_lev = y_vr + height_vr/2.1f, rotation_lev = 90;
    int kto;

    float dviz_popal_x = 0.25f;
    float dviz_popal_y = 0.09f;
    float vras_vr_popal = 0.05f;

    float skor_dlya_pen_x = 1f;
    float skor_dlya_pen_y = 1f;
    public float rast_x,rast_y;
    float cel_x, cel_y;
    int cel_n;
    String misen;

    Vratar_in_penalti(int kto, int cel_n, float cel_x, float cel_y) {
        this.cel_x = cel_x;
        this.cel_y = cel_y;
        this.kto = kto; // 0 - vr, 1-lev, 2-prav
        this.cel_n = cel_n;
    }


    public void popal(int cel, boolean stop){
        if (stop == false){
            if (cel == 3 || cel == 7 || cel == 9 || cel ==11 || cel == 13 ){
                //System.out.println(cel);
                x_vr -= dviz_popal_x;
                y_vr += dviz_popal_y;
                rotation_vr += vras_vr_popal;

                x_prav -= dviz_popal_x*1.5f;
                y_prav += dviz_popal_y*1.7f;
                rotation_prav += vras_vr_popal*3.3f;


                x_lev -= dviz_popal_x*1.4f;
                y_lev += dviz_popal_y - dviz_popal_y*1.7f;
                rotation_lev -= vras_vr_popal*1.4f;


            }
            if (cel == 1 || cel == 4 || cel == 8 || cel ==10 || cel == 12 || cel == 2 ){
                x_vr += dviz_popal_x;
                y_vr += dviz_popal_y;
                rotation_vr -= vras_vr_popal;

                x_prav += dviz_popal_x*1.3f;
                y_prav += dviz_popal_y - dviz_popal_y*1.6f;
                rotation_prav += vras_vr_popal*1.6f;


                x_lev += dviz_popal_x*1.5f;
                y_lev += dviz_popal_y*1.5f;
                rotation_lev -= vras_vr_popal*4f;
            }
            if (cel == 6){
                x_vr -= dviz_popal_x;
                y_vr += dviz_popal_y;
                rotation_vr += vras_vr_popal;

                x_prav -= dviz_popal_x*1.4f;
                y_prav += dviz_popal_y*1.7f;
                rotation_prav += vras_vr_popal*5f;


                x_lev -= dviz_popal_x*1.4f;
                y_lev += dviz_popal_y - dviz_popal_y*1.7f;
                rotation_lev -= vras_vr_popal*1.8f;
            }
            if (cel == 5){
                x_vr += dviz_popal_x;
                y_vr += dviz_popal_y;
                rotation_vr -= vras_vr_popal;

                x_prav += dviz_popal_x*1.3f;
                y_prav += dviz_popal_y - dviz_popal_y*1.6f;
                rotation_prav += vras_vr_popal*1.9f;


                x_lev += dviz_popal_x*1.5f;
                y_lev += dviz_popal_y*1.5f;
                rotation_lev -= vras_vr_popal*5.5f;
            }
        }
        else{
            //System.out.println("STOP");
        }




    }
    public void nepopal(int cel){

    }


    private String readFile()
    {
        try {
            FileHandle file = Gdx.files.local("data/" + "vibrannay_cel.txt");
            if (file.exists()) {
                return file.readString();
            }
        } catch (Exception e) {
            Gdx.app.error("FILE", "Ошибка чтения: " + "vibrannay_cel.txt", e);
        }
        return "";
    }



}
