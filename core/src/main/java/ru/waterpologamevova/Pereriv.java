package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Pereriv implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта
    private BitmapFont font2;

    private Texture miach_iz;

    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню

    // таймер на 30 сек
    long Timer_Start;

    int sk_myachei_v_menu = 33;
    Ne_myachi[] myach = new Ne_myachi[sk_myachei_v_menu];

    public Pereriv(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font4;
        font2 = main.font_taimer_5s;

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        //touch = new Vector3();
        // создание картинок
        miach_iz = new Texture("miach.png");


        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //создание мячей
        for (int i = 0; i < sk_myachei_v_menu; i++) {
            myach[i] = new Ne_myachi();
        }

        // время создания всего

    }

    @Override
    public void show() {
        Timer_Start = TimeUtils.millis();

    }

    @Override
    public void render(float delta) {
        camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
        camera.update();

        ScreenUtils.clear(1f, 1f, 1f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        for (int i = 0; i < sk_myachei_v_menu; i++) {
            myach[i].dvizenie();
            batch.draw(miach_iz, myach[i].x, myach[i].y, myach[i].width_iz, myach[i].height_iz,
                0, 0, myach[i].w_myacha, myach[i].h_myacha, myach[i].flip_mach_x(), myach[i].flip_mach_y());
        }

        // надпись в меню
        font.draw(batch, "Перерыв на 15 секунд)", SCR_WIDTH / 3 - 100, SCR_HEIGHT - 150);

        // Отображение времени
        font2.draw(batch, Vremya_pereriva(TimeUtils.millis() - Timer_Start), SCR_WIDTH/2-50, SCR_HEIGHT/2-50);
        if (TimeUtils.millis() - Timer_Start > 15000){
            main.setScreen(main.standart);
        }


        // таймер для зацикливания музыки
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
        miach_iz.dispose();
        font2.dispose();
        font.dispose();

    }


    private String Vremya_pereriva(long Timer) {
        long msec = 10 - (Timer % 1000) / 100;
        long sec = 15 - Timer / 1000 % 60;
        return sec + "";
    }


}
