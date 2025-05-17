package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Settings implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture glav_menu_back;

    private Texture back_vibor;
    private Texture sloznost;
    private Texture legko;
    private Texture prosto;
    private Texture normalno;
    private Texture slozno;
    private Texture super_slozno;
    private Texture sloznost_now;
    private Texture sloznost_tek;
    //private Texture zv_mute_kn;
    long Timer_Start_nepopal;
    private String nadpis_menu; // надпись в меню
    // кнопки в меню
    Button_menu back_menu_kn;
    Button_menu sloznost_kn;
    Button_menu sloznost_now_kn;
    Button_menu legko_kn;
    Button_menu prosto_kn;
    Button_menu normalno_kn;
    Button_menu slozno_kn;
    Button_menu super_slozno_kn;
    Button_menu sloznost_tek_kn;


    public Settings(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font3;




        glav_menu_back = new Texture("settings_back.png");
        sloznost = new Texture("sloznost.png");
        legko = new Texture("legko.png");
        prosto = new Texture("prosto.png");
        slozno = new Texture("slozno.png");
        normalno = new Texture("normalno.png");
        super_slozno = new Texture("super_slozno.png");
        sloznost_now = new Texture("sloznost_now.png");

        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //кнопки в меню
        back_menu_kn = new Button_menu(SCR_WIDTH - 215, 10, 200, 80);
        sloznost_kn = new Button_menu(SCR_WIDTH/2 - 200, SCR_HEIGHT/3+160, 400, 160);
        sloznost_now_kn = new Button_menu(20, 40, 600, 100);
        float k = 2.2f;
        sloznost_tek_kn = new Button_menu(620 + 30, 40 + 5, 400/k, 200/k);

        legko_kn = new Button_menu(25, 200, 400/k, 200/k);
        prosto_kn = new Button_menu(230, 200, 400/k, 200/k);
        normalno_kn = new Button_menu(425, 200, 800/k, 200/k);
        slozno_kn = new Button_menu(SCR_WIDTH - 410, 200, 400/k, 200/k);
        super_slozno_kn = new Button_menu(SCR_WIDTH - 200, 200, 400/k, 200/k);

    }

    @Override
    public void show() {
        Timer_Start_nepopal = TimeUtils.millis();

    }

    @Override
    public void render(float delta) {
        float k = 2.2f;
        // касания
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (back_menu_kn.hit(touch.x, touch.y)){
                main.setScreen(main.menu);
            }
            if (legko_kn.hit(touch.x, touch.y)){
                overwriteFile("sloznost.txt", "5");
                //System.out.println("Super_slozno");
            }
            if (prosto_kn.hit(touch.x, touch.y)){
                overwriteFile("sloznost.txt", "4");
            }
            if (normalno_kn.hit(touch.x, touch.y)){
                overwriteFile("sloznost.txt", "3");
            }
            if (slozno_kn.hit(touch.x, touch.y)){
                overwriteFile("sloznost.txt", "2");
            }
            if (super_slozno_kn.hit(touch.x, touch.y)){
                overwriteFile("sloznost.txt", "1");
            }}

        String fail = new String(readFile("sloznost.txt"));
        char fail_ch = fail.charAt(0);
        if (fail_ch == '5') {
            sloznost_tek = new Texture("legko.png");
            sloznost_tek_kn = new Button_menu(620 + 30, 40 + 5, 400/k, 200/k);
            //System.out.println("Legko");
        }
        if (fail_ch == '4') {
            sloznost_tek = new Texture("prosto.png");
            sloznost_tek_kn = new Button_menu(620 + 30, 40 + 5, 400/k, 200/k);
            //System.out.println("Prosto");
        }
        if (fail_ch == '3') {
            sloznost_tek = new Texture("normalno.png");
            sloznost_tek_kn = new Button_menu(620 + 30, 40 + 5, 800/k, 200/k);
            //System.out.println("Normalno");
        }
        if (fail_ch == '2') {
            sloznost_tek = new Texture("slozno.png");
            sloznost_tek_kn = new Button_menu(620 + 30, 40 + 5, 400/k, 200/k);
            //System.out.println("Slozno");
        }
        if (fail_ch == '1') {
            sloznost_tek = new Texture("super_slozno.png");
            sloznost_tek_kn = new Button_menu(620 + 30, 40 + 5, 400/k, 200/k);
            //System.out.println("Super_slozno");
        }


        back_vibor = new Texture("back.png");

        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0, 0, SCR_WIDTH, SCR_HEIGHT); // фон в меню

        // надпись в меню
        font.draw(batch, "Настройки", 200, SCR_HEIGHT - 15);

        //кнопки в меню
        batch.draw(back_vibor, back_menu_kn.x, back_menu_kn.y, back_menu_kn.width, back_menu_kn.height);
        batch.draw(sloznost, sloznost_kn.x, sloznost_kn.y, sloznost_kn.width, sloznost_kn.height);
        batch.draw(sloznost_now, sloznost_now_kn.x, sloznost_now_kn.y, sloznost_now_kn.width, sloznost_now_kn.height);
        batch.draw(sloznost_tek, sloznost_tek_kn.x, sloznost_tek_kn.y, sloznost_tek_kn.width, sloznost_tek_kn.height);

        batch.draw(legko, legko_kn.x, legko_kn.y, legko_kn.width, legko_kn.height);
        batch.draw(prosto, prosto_kn.x, prosto_kn.y, prosto_kn.width, prosto_kn.height);
        batch.draw(normalno, normalno_kn.x, normalno_kn.y, normalno_kn.width, normalno_kn.height);
        batch.draw(slozno, slozno_kn.x, slozno_kn.y, slozno_kn.width, slozno_kn.height);
        batch.draw(super_slozno, super_slozno_kn.x, super_slozno_kn.y, super_slozno_kn.width, super_slozno_kn.height);
        //цели
        batch.end();




    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        glav_menu_back.dispose();
        font.dispose();
        back_vibor.dispose();
        slozno.dispose();
        sloznost.dispose();
        legko.dispose();
        prosto.dispose();
        normalno.dispose();
        super_slozno.dispose();
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
