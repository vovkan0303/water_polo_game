package ru.waterpologamevova;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main extends Game {
    public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont font2;
    public BitmapFont font3;
    public int ch;
    Menu menu;
    Vibor_rezima vibor_rezima;
    Penalti penalti;
    Popal popal;
    Ne_popal ne_popal;
    Settings settings;




    @Override
    public void create() {


        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("ch_zeltiu.fnt"));
        font3 = new BitmapFont(Gdx.files.internal("3fon_bel_ch.fnt"));





        menu = new Menu(this);
        vibor_rezima = new Vibor_rezima(this);
        penalti = new Penalti(this);
        popal = new Popal(this);
        ne_popal = new Ne_popal(this);
        settings = new Settings(this);

        new Thread(() -> {
            createFiles();
            Gdx.app.postRunnable(() -> {
                // Этот код выполнится в основном потоке после создания файлов
                Gdx.app.log("FILES", "Faili ysp sozd");
            });
        }).start();


        setScreen(menu);
    }
    private void createFiles() {
        try {// Для всех платформ используем локальное хранилище приложения
            FileHandle folder = Gdx.files.local("data/"); // Будет создано в папке приложения

            // Создаем папку, если ее нет
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Файл сложности
            FileHandle fileSlozn = folder.child("sloznost.txt");
            if (!fileSlozn.exists()) {
                fileSlozn.writeString("5", false);
                Gdx.app.log("FILE", "Created: " + fileSlozn.path());
            }
            else {
                Gdx.app.log("Yze", "Est: " + fileSlozn.path());
                System.out.println("Desktop path: " + Gdx.files.getLocalStoragePath());}

            // Файл выбранной цели
            FileHandle fileCel = folder.child("vibrannay_cel.txt");
            if (!fileCel.exists()) {
                fileCel.writeString("0", false);
                Gdx.app.log("FILE", "Created: " + fileCel.path());
            }

        } catch (Exception e) {
            Gdx.app.error("FILE", "Error creating files", e);
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
    }



}



