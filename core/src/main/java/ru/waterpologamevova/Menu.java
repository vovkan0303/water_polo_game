package ru.waterpologamevova;
import static ru.waterpologamevova.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Menu implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture miach_iz;
    private Texture glav_menu_back;
    private Music menu_sound;
    //private Music menu_music;
    private Texture zv_vkl_kn;
    private Texture play_kn;
    private Texture settings_kn;
    private Texture exit_kn;

    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню
    public boolean igraet = true; // кнопка отвечающая за воспроизведения музыки
    public String ris = "zv_vkl.png";

    // таймер на 30 сек
    long Timer_Start;
    long Timer_Start_m;

    // кнопка мута музыки
    Button_Mute_Music btn_mute;
    // кнопки в меню
    Button_menu btn_play;
    Button_menu btn_settings;
    Button_menu btn_exit;

    int sk_myachei_v_menu = 33;
    Ne_myachi[] myach = new Ne_myachi[sk_myachei_v_menu];

    public Menu(Main main) {

        play_kn = new Texture("play_kn.png");
        settings_kn = new Texture("nastroiki.png");
        exit_kn = new Texture("exit.png");

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


        //создание звуков

        menu_sound = Gdx.audio.newMusic(Gdx.files.internal("menu_sound.ogg"));
        // музыка играет зациклино
        menu_sound.play();
        menu_sound.setLooping(true);
        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //создание мячей
        for (int i = 0; i < sk_myachei_v_menu; i ++){
            myach[i] = new Ne_myachi();
        }
        // кнопка мута
        btn_mute = new Button_Mute_Music(0,0, 100, 100);
        //кнопки в меню
        btn_play = new Button_menu((SCR_WIDTH - 400)/2, SCR_HEIGHT-300, 400,160);
        btn_settings = new Button_menu((SCR_WIDTH - 400)/2, SCR_HEIGHT-500, 400,160);
        btn_exit = new Button_menu(SCR_WIDTH - 160, SCR_HEIGHT/18, 150,60);

        // время создания всего
        Timer_Start = TimeUtils.millis();
        Timer_Start_m = TimeUtils.millis();
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        zv_vkl_kn = new Texture(ris);
        // касания
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btn_mute.hit(touch.x, touch.y) & igraet == true){
                Mute_mus();
                System.out.println(igraet);
            }
            else if (btn_mute.hit(touch.x, touch.y) & igraet == false){
                Go_mus();
                System.out.println(igraet);
            }
            if (btn_play.hit(touch.x, touch.y)){
                Mute_mus();
                main.setScreen(main.vibor_rezima);
            }
            if (btn_settings.hit(touch.x, touch.y)){
                Mute_mus();
                main.setScreen(main.settings);
            }
            if (btn_exit.hit(touch.x, touch.y)){
                Mute_mus();
                Gdx.app.exit();
            }

        }
        //кнопки


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
        font.draw(batch, "Приветствую вас!!!", SCR_WIDTH/3, SCR_HEIGHT-50);

        // Отображение времени
        font.draw(batch, Taim_played_vremya(TimeUtils.millis() - Timer_Start), SCR_WIDTH-200, SCR_HEIGHT-30);

        //рисуем кнопку мута
        batch.draw(zv_vkl_kn, btn_mute.x, btn_mute.y, btn_mute.width, btn_mute.height);
        //кнопки в меню
        batch.draw(play_kn, btn_play.x, btn_play.y, btn_play.width, btn_play.height);
        batch.draw(settings_kn, btn_settings.x, btn_settings.y, btn_settings.width, btn_settings.height);
        batch.draw(exit_kn, btn_exit.x, btn_exit.y, btn_exit.width, btn_exit.height);

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
        glav_menu_back.dispose();
        menu_sound.dispose();
        //menu_music.dispose();
        font.dispose();
        zv_vkl_kn.dispose();
        play_kn.dispose();
        settings_kn.dispose();
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

    private void Mute_mus(){
        menu_sound.pause();
        ris = "zv_mute.png";
        igraet = false;
    }

    private void Go_mus(){
        menu_sound.play();
        ris = "zv_vkl.png";
        igraet = true;
    }

}
