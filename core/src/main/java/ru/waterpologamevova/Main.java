package ru.waterpologamevova;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class Main extends Game {
    public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont font2;

    Menu menu;
    Vibor_rezima vibor_rezima;
    Penalti penalti;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("ch_zeltiu.fnt"));


        menu = new Menu(this);
        vibor_rezima = new Vibor_rezima(this);
        penalti = new Penalti(this);

        setScreen(menu);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
