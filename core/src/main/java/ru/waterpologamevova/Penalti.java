package ru.waterpologamevova;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import static ru.waterpologamevova.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Penalti implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture glav_menu_back;

    private Texture back_vibor;
    private Texture cel;
    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню
    // кнопки в меню
    Button_menu back_menu_kn;
    Button_menu cel_kn1;
    Button_menu cel_kn2;
    Button_menu cel_kn3;
    Button_menu cel_kn4;
    Button_menu cel_kn5;
    Button_menu cel_kn6;
    Button_menu cel_kn7;
    Button_menu cel_kn8;
    Button_menu cel_kn9;
    Button_menu cel_kn10;
    Button_menu cel_kn11;
    Button_menu cel_kn12;
    Button_menu cel_kn13;

    public Penalti(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font2;

        glav_menu_back = new Texture("vorota.png");



        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //кнопки в меню
        back_menu_kn = new Button_menu(SCR_WIDTH - 110, SCR_HEIGHT-40, 100,40);
        cel_kn1 = new Button_menu(40, SCR_HEIGHT-200, 150,150);
        cel_kn2 = new Button_menu((SCR_WIDTH/2)-75, SCR_HEIGHT-200, 150,150);
        cel_kn3 = new Button_menu(SCR_WIDTH-190, SCR_HEIGHT-200, 150,150);
        cel_kn4 = new Button_menu(40, SCR_HEIGHT-375, 150,150);
        cel_kn5 = new Button_menu((SCR_WIDTH/2)-165, SCR_HEIGHT-375, 150,150);
        cel_kn6 = new Button_menu((SCR_WIDTH/2)+15, SCR_HEIGHT-375, 150,150);
        cel_kn7 = new Button_menu(SCR_WIDTH-190, SCR_HEIGHT-375, 150,150);
        cel_kn8 = new Button_menu(40, SCR_HEIGHT-535, 150,150);
        cel_kn9 = new Button_menu(SCR_WIDTH-190, SCR_HEIGHT-535, 150,150);
        cel_kn10 = new Button_menu((SCR_WIDTH/2)-375, SCR_HEIGHT-300, 150,150);
        cel_kn11 = new Button_menu((SCR_WIDTH/2)+375-150, SCR_HEIGHT-300, 150,150);
        cel_kn12 = new Button_menu((SCR_WIDTH/2)-375, SCR_HEIGHT-500, 150,150);
        cel_kn13 = new Button_menu((SCR_WIDTH/2)+375-150, SCR_HEIGHT-500, 150,150);
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        // касания
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (back_menu_kn.hit(touch.x, touch.y)){
                main.setScreen(main.vibor_rezima);
            }
            if (cel_kn1.hit(touch.x, touch.y)){
                udar(1);
            }
            if (cel_kn2.hit(touch.x, touch.y)){
                udar(2);
            }
            if (cel_kn3.hit(touch.x, touch.y)){
                udar(3);
            }
            if (cel_kn4.hit(touch.x, touch.y)){
                udar(4);
            }
            if (cel_kn5.hit(touch.x, touch.y)){
                udar(5);
            }
            if (cel_kn6.hit(touch.x, touch.y)){
                udar(6);
            }
            if (cel_kn7.hit(touch.x, touch.y)){
                udar(7);
            }
            if (cel_kn8.hit(touch.x, touch.y)){
                udar(8);
            }
            if (cel_kn9.hit(touch.x, touch.y)){
                udar(9);
            }
            if (cel_kn10.hit(touch.x, touch.y)){
                udar(10);
            }
            if (cel_kn11.hit(touch.x, touch.y)){
                udar(11);
            }
            if (cel_kn12.hit(touch.x, touch.y)){
                udar(12);
            }
            if (cel_kn13.hit(touch.x, touch.y)){
                udar(13);
            }
        }
        back_vibor = new Texture("back.png");
        cel = new Texture("cel.png");

        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0,0, SCR_WIDTH, SCR_HEIGHT); // фон в меню

        // надпись в меню
        font.draw(batch, "Выбери куда хочешь ударить!!!", 5, SCR_HEIGHT-5);

        //кнопки в меню
        batch.draw(back_vibor, back_menu_kn.x, back_menu_kn.y, back_menu_kn.width, back_menu_kn.height);
        //цели
        batch.draw(cel, cel_kn1.x, cel_kn1.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn2.x, cel_kn2.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn3.x, cel_kn3.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn4.x, cel_kn4.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn5.x, cel_kn5.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn6.x, cel_kn6.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn7.x, cel_kn7.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn8.x, cel_kn8.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn9.x, cel_kn9.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn10.x, cel_kn10.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn11.x, cel_kn11.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn12.x, cel_kn12.y, cel_kn1.width, cel_kn1.height);
        batch.draw(cel, cel_kn13.x, cel_kn13.y, cel_kn1.width, cel_kn1.height);
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
    public void dispose() {batch.dispose();
        glav_menu_back.dispose();
        font.dispose();
        cel.dispose();
        back_vibor.dispose();
    }

    private void udar(int mishen){
        System.out.println(mishen);
    }

}

