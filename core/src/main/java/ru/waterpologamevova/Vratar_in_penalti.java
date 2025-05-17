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
                rotation_prav += vras_vr_popal*4.5f;


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
                rotation_lev -= vras_vr_popal*4.5f;
            }
        }
        else{
            //System.out.println("STOP");
        }




    }
    public void nepopal(int cel, boolean stop){ // 1 2 3 4 5 6 7 8 9 10 12 13
        if (stop == false) {
            if (cel == 1) {
                x_vr -= dviz_popal_x*1.1f;
                y_vr += dviz_popal_y * 1.95f;
                rotation_vr += vras_vr_popal*0.75f;

                x_prav -= dviz_popal_x * 1.6f;
                y_prav += dviz_popal_y *2.25f;
                rotation_prav += vras_vr_popal * 3;


                x_lev -= dviz_popal_x * 1.55f;
                y_lev += dviz_popal_y;
                width_lev += 0.05f;
                height_lev += 0.05f;
                rotation_lev -= vras_vr_popal * 3.58f*0.83f;
            }
            if (cel == 3) {
                x_vr += dviz_popal_x*1.1f;
                y_vr += dviz_popal_y * 1.95f;
                rotation_vr -= vras_vr_popal*0.75f;

                x_prav += dviz_popal_x * 1.5f;
                y_prav += dviz_popal_y *1.1f;
                width_prav += 0.05f;
                height_prav += 0.05f;
                rotation_prav += vras_vr_popal * 3.48f*0.83f;

                x_lev += dviz_popal_x * 1.55f;
                y_lev += dviz_popal_y*3f;
                rotation_lev = rotation_lev;

                }
            if (cel == 2) {
                x_vr = x_vr;
                y_vr += dviz_popal_y * 1.7f;
                rotation_vr = rotation_vr;

                x_prav = x_prav;
                y_prav += dviz_popal_y * 1.6f;
                rotation_prav += vras_vr_popal * 4.44f;


                x_lev = x_lev;
                y_lev += dviz_popal_y * 1.5f;
                rotation_lev -= vras_vr_popal * 4.44f;
            }
            if (cel == 5 || cel == 6) {
                x_vr = x_vr;
                y_vr += dviz_popal_y * 1.2f;
                rotation_vr = rotation_vr;

                x_prav = x_prav;
                y_prav += dviz_popal_y * 1.1f;
                rotation_prav += vras_vr_popal * 3.7f;


                x_lev = x_lev;
                y_lev += dviz_popal_y * 1f;
                rotation_lev -= vras_vr_popal * 3.7f;
            }
            if (cel == 4){
                x_vr -= dviz_popal_x*1.2f;
                y_vr += dviz_popal_y*0.95f;
                rotation_vr += vras_vr_popal;

                x_prav -= dviz_popal_x*1.3f*1.3f;
                y_prav += dviz_popal_y*1.5f*0.95f;
                rotation_prav += vras_vr_popal*4f;


                x_lev -= dviz_popal_x*1.5f;
                y_lev += (dviz_popal_y - dviz_popal_y*1.5f);
                rotation_lev -=  vras_vr_popal*2.4f;
            }
            if (cel == 10){
                x_vr -= dviz_popal_x*0.5f;
                y_vr += dviz_popal_y*0.95f;
                rotation_vr += vras_vr_popal*0.3f;

                x_prav -= dviz_popal_x*0.6f;
                y_prav += dviz_popal_y*1.5f*0.95f;
                rotation_prav += vras_vr_popal;


                x_lev -= dviz_popal_x*0.6f;
                y_lev += dviz_popal_y*0.7f;
                rotation_lev -=  vras_vr_popal*2.8f;
            }
            if (cel == 11){
                x_vr += dviz_popal_x*0.5f;
                y_vr += dviz_popal_y*0.95f;
                rotation_vr -= vras_vr_popal*0.3f;

                x_prav += dviz_popal_x*0.6f;
                y_prav += dviz_popal_y*0.95f;
                rotation_prav +=  vras_vr_popal*2.8f;



                x_lev += dviz_popal_x*0.6f;
                y_lev += dviz_popal_y*0.9f;
                rotation_lev -= vras_vr_popal;

            }
            if (cel == 7){
                x_vr += dviz_popal_x*1.2f;
                y_vr += dviz_popal_y*0.95f;
                rotation_vr -= vras_vr_popal;

                x_prav += dviz_popal_x*1.3f*1.2f;
                y_prav += (dviz_popal_y - dviz_popal_y*1.3f);
                rotation_prav += vras_vr_popal*2.4f;


                x_lev += dviz_popal_x*1.5f*1.2f;
                y_lev += dviz_popal_y*1.5f*0.95f;
                rotation_lev -= vras_vr_popal*4f;
            }
            if (cel == 8 || cel == 12) {
                x_vr -= dviz_popal_x;
                y_vr += dviz_popal_y * 0.90f;
                rotation_vr += vras_vr_popal;

                x_prav -= dviz_popal_x * 1.5f;
                y_prav += dviz_popal_y * 1.7f * 0.90f;
                rotation_prav += vras_vr_popal * 3.3f;


                x_lev -= dviz_popal_x * 1.4f;
                y_lev += dviz_popal_y - dviz_popal_y * 1.7f * 0.90f;
                rotation_lev -= vras_vr_popal * 1.08f;
            }
            if (cel == 9){
                x_vr += dviz_popal_x*1.2f;
                y_vr += dviz_popal_y*0.95f;
                rotation_vr -= vras_vr_popal;

                x_prav += dviz_popal_x*1.3f*1.2f;
                y_prav += (dviz_popal_y - dviz_popal_y*1.6f)*0.95f;
                rotation_prav += vras_vr_popal*1.18f;


                x_lev += dviz_popal_x*1.5f*1.2f;
                y_lev += dviz_popal_y*1.5f*0.95f;
                rotation_lev -= vras_vr_popal*4f;
            }
            if (cel == 13){
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
        }
        else {}
    }


    public static String readFile(String fileName) {
        try {
            FileHandle file = Gdx.files.local(fileName);
            if (!file.exists()) {
                Gdx.app.error("FileUtils", "Файл не найден: " + fileName);
                return null;
            }
            return file.readString();
        } catch (Exception e) {
            Gdx.app.error("FileUtils", "Ошибка чтения файла", e);
            return null;
        }
    }
    public static boolean overwriteFile(String fileName, String newContent) {
        try {
            FileHandle file = Gdx.files.local(fileName);
            file.writeString(newContent, false); // false - перезаписать
            return true;
        } catch (Exception e) {
            Gdx.app.error("FileUtils", "Ошибка перезаписи файла", e);
            return false;
        }
    }



}
