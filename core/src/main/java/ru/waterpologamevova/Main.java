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
    public BitmapFont font4;
    public int ch;
    Menu menu;
    Vibor_rezima vibor_rezima;
    Penalti penalti;
    Popal popal;
    Ne_popal ne_popal;
    Settings settings;
    Standart standart;
    Parametri_igri parametri_igri;
    Vibor_poz vibor_poz;




    @Override
    public void create() {


        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("ch_zeltiu.fnt"));
        font3 = new BitmapFont(Gdx.files.internal("3fon_bel_ch.fnt"));
        font4 = new BitmapFont(Gdx.files.internal("nadpis_in_param.fnt"));

        menu = new Menu(this);
        vibor_rezima = new Vibor_rezima(this);
        penalti = new Penalti(this);
        popal = new Popal(this);
        ne_popal = new Ne_popal(this);
        settings = new Settings(this);
        standart = new Standart(this);
        parametri_igri = new Parametri_igri(this);
        vibor_poz = new Vibor_poz(this);


        new Thread(() -> {
            createFile("sloznost.txt", "5");
            createFile("vibrannay_cel.txt", "0");
            createFile("cvet_kom.txt", "1");
            createFile("nomer_igroka.txt", "2");
            createFile("kol_per.txt", "1");
            createFile("poz_igr.txt", "0");
            Gdx.app.postRunnable(() -> {
                // Этот код выполнится в основном потоке после создания файлов
                Gdx.app.log("FILES", "Faili ysp sozd");
            });
        }).start();


        setScreen(menu);
    }
    public static boolean createFile(String fileName, String content) {
        try {
            FileHandle file = Gdx.files.local(fileName);
            file.writeString(content, false); // false - перезаписать файл
            return true;
        } catch (Exception e) {
            Gdx.app.error("FileUtils", "Ошибка создания файла", e);
            return false;
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
    }



}



