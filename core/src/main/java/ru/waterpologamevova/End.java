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

public class End implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта
    private BitmapFont font2;

    private Texture miach_iz;
    private Texture exit_iz;
    private Button_menu btn_exit;

    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню

    // таймер на 30 сек
    long Timer_Start;

    int sk_myachei_v_menu = 33;
    Ne_myachi[] myach = new Ne_myachi[sk_myachei_v_menu];

    public End(Main main) {
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
        exit_iz = new Texture("exit.png");


        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //создание мячей
        for (int i = 0; i < sk_myachei_v_menu; i++) {
            myach[i] = new Ne_myachi();
        }

        // время создания всего
        btn_exit = new Button_menu(SCR_WIDTH/2-150*1.5f/2, SCR_HEIGHT/9, 150*1.5f,60*1.5f);

    }

    @Override
    public void show() {
        Timer_Start = TimeUtils.millis();

    }

    @Override
    public void render(float delta) {
        camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
        camera.update();
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btn_exit.hit(touch.x, touch.y)){
                main.setScreen(main.menu);
            }
        }
        ScreenUtils.clear(0.3f, 0.7f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        for (int i = 0; i < sk_myachei_v_menu; i++) {
            myach[i].dvizenie();
            batch.draw(miach_iz, myach[i].x, myach[i].y, myach[i].width_iz, myach[i].height_iz,
                0, 0, myach[i].w_myacha, myach[i].h_myacha, myach[i].flip_mach_x(), myach[i].flip_mach_y());
        }

        // надпись в меню
        String goal_souz = new String(readFile("goal_souz.txt"));
        String goal_vrag = new String(readFile("goal_vrag.txt"));

        font.draw(batch, "Игра окончена(", SCR_WIDTH / 2 - 200, SCR_HEIGHT - 150);
        font.draw(batch, "Итоговый счёт: " + goal_souz + " : "+ goal_vrag, SCR_WIDTH/2-250, SCR_HEIGHT/2-50);

        batch.draw(exit_iz, btn_exit.x, btn_exit.y, btn_exit.width, btn_exit.height);

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
}
