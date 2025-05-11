package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Penalti_myach {
        float x, y;
        float sk = 1.6f;
        public int w_myacha = 108;
        public int h_myacha = 129;
        float width_iz;
        float height_iz;
        float skor_dlya_pen_x = 1f;
        float skor_dlya_pen_y = 1f;
        public float rast_x,rast_y;
        float rotation = 10f;
        float cel_x, cel_y;
        String misen;

        Penalti_myach(float cel_x, float cel_y, float x) {
            this.cel_x = cel_x;
            this.cel_y = cel_y;
            width_iz =  108 * sk;
            height_iz = 129 * sk;
            //this.x = SCR_WIDTH - 120*sk;
            this.x = x;

            this.y = -30;
            this.rast_x = cel_x-x + 20;
            this.rast_y = cel_y-y + 10;
            skor_dlya_pen_x = Math.abs(rast_x)/(60 *7);
            skor_dlya_pen_y = Math.abs(rast_y)/(60 * 7);
            if (rast_x < 0) skor_dlya_pen_x = -1 * skor_dlya_pen_x;
            if (rast_y < 0) skor_dlya_pen_y = -skor_dlya_pen_y;
        }


        public void dvizenie_in_cel(){
            x += skor_dlya_pen_x;
            y += skor_dlya_pen_y;

            rast_x -= skor_dlya_pen_x;
            rast_y -= skor_dlya_pen_y;


            //System.out.println(cel_x);
            //System.out.println(cel_y);



            if (rast_x >= -3 && rast_x <= 3) {
                skor_dlya_pen_x = 0;
                rotation = 0;
            }
            else width_iz = width_iz - width_iz/960;
            if (rast_y >= -3 && rast_y <= 3) {
                skor_dlya_pen_y = 0;
                rotation = 0;
            }
            else height_iz = height_iz - height_iz/960;



        }
        public void dvizenie(){
            x += skor_dlya_pen_x;
            y += skor_dlya_pen_y;
            if (x + width_iz >= SCR_WIDTH || x <= 0) skor_dlya_pen_x = -skor_dlya_pen_x;
            if (y + height_iz >= SCR_HEIGHT || y <= 0) skor_dlya_pen_y = -skor_dlya_pen_y;

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


    public static void pere_zapis(String filename, String content) {
        try {
            // Сначала открываем для очистки
            new FileWriter(filename, false).close();

            // Затем записываем новый контент
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
