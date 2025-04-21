package ru.waterpologamevova;

import static ru.waterpologamevova.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Vibor_rezima implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture miach_iz;
    private Texture glav_menu_back;

    private Texture back_menu;
    private Texture klassika;
    private Texture penalti;

    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню

    // таймер на 30 сек
    long Timer_Start;
    long Timer_Start_m;

    // кнопки в меню
    Button_menu klassika_kn;
    Button_menu penalti_kn;
    Button_menu back_menu_kn;

    int sk_myachei_v_menu = 33;
    Dvizenie_myacha_v_menu[] myach = new Dvizenie_myacha_v_menu[sk_myachei_v_menu];

    public Vibor_rezima(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        //touch = new Vector3();
        // создание картинок
        miach_iz = new Texture("miach.png");
        glav_menu_back = new Texture("glav_m.png");



        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //создание мячей
        for (int i = 0; i < sk_myachei_v_menu; i ++){
            myach[i] = new Dvizenie_myacha_v_menu();
        }

        //кнопки в меню
        klassika_kn = new Button_menu((SCR_WIDTH - 300)/2, SCR_HEIGHT-250, 300,120);
        penalti_kn = new Button_menu((SCR_WIDTH - 300)/2, SCR_HEIGHT-400, 300,120);
        back_menu_kn = new Button_menu((SCR_WIDTH - 200)/2, SCR_HEIGHT-500, 200,80);

        // время создания всего
        Timer_Start = TimeUtils.millis();
        Timer_Start_m = TimeUtils.millis();
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
                main.setScreen(main.menu);
            }
            if (penalti_kn.hit(touch.x, touch.y)){
                main.setScreen(main.penalti);
            }

        }
        klassika = new Texture("klassika.png");
        penalti = new Texture("penalti.png");
        back_menu = new Texture("back.png");

        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0,0, SCR_WIDTH, SCR_HEIGHT); // фон в меню
        for (int i = 0; i < sk_myachei_v_menu; i ++) {
            myach[i].dvizenie();
            batch.draw(miach_iz, myach[i].x, myach[i].y, myach[i].width_iz, myach[i].height_iz,
                0,0, myach[i].w_myacha, myach[i].h_myacha, myach[i].flip_mach_x(), myach[i].flip_mach_y());
        }

        // надпись в меню
        font.draw(batch, "Выберите режим игры", SCR_WIDTH/3, SCR_HEIGHT-50);

        // Отображение времени
        font.draw(batch, Taim_played_vremya(TimeUtils.millis() - Timer_Start), SCR_WIDTH-200, SCR_HEIGHT-30);

        //кнопки в меню
        batch.draw(klassika, klassika_kn.x, klassika_kn.y, klassika_kn.width, klassika_kn.height);
        batch.draw(penalti, penalti_kn.x, penalti_kn.y, penalti_kn.width, penalti_kn.height);
        batch.draw(back_menu, back_menu_kn.x, back_menu_kn.y, back_menu_kn.width, back_menu_kn.height);

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
    public void dispose() {batch.dispose();
        miach_iz.dispose();
        glav_menu_back.dispose();
        font.dispose();
        klassika.dispose();
        back_menu.dispose();
        penalti.dispose();

    }

    private String Taim_played_vremya(long Timer){
        long msec = 10 - (Timer % 1000) / 100;
        long sec = 59 - Timer/1000%60;
        long min = 7 - Timer/1000/60%60;
        long hour = Timer/1000/60/60%24;
        return min / 10 + min % 10 + ":" +sec / 10 + sec % 10 + ":" + msec;
    }

    private String Vremya_ataki(long Timer){
        long msec = 10 - (Timer % 1000) / 100;
        long sec = 29 - Timer/1000%60;
        return sec / 10 + sec % 10 + ":" + msec;
    }

    private String Vremia_Muziki(long Timer){
        long sec = Timer/1000%60;
        return "" + sec / 10 + sec % 10;
    }


}
