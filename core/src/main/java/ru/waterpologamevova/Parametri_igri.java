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

public class Parametri_igri implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture glav_menu_back;
    private Texture dalee_iz;
    private Texture strel_lev_n_iz;
    private Texture strel_prav_n_iz;
    private Texture yacheika_dlya_n_iz;
    private Texture strel_lev_per_iz;
    private Texture yacheika_dlya_per_iz;
    private Texture strel_prav_per_iz;
    private Texture bel_team_iz;
    private Texture sin_team_iz;
    private Texture vibran_cvet_iz;


    private Texture back_parmetri;

    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню

    // таймер на 30 сек
    long Timer_Start;
    long Timer_Start_m;

    // кнопки в меню
    Button_menu back_menu_kn;
    Button_menu dalee_kn;
    Button_menu strel_lev_n;
    Button_menu strel_prav_n;
    Button_menu yacheika_dlya_n;
    Button_menu strel_lev_per;
    Button_menu yacheika_dlya_per;
    Button_menu strel_prav_per;
    Button_menu bel_team;
    Button_menu sin_team;
    Button_menu vibr_cvet;


    int sk_myachei_v_menu = 33;


    public Parametri_igri(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font4;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();



        // создание картинок
        glav_menu_back = new Texture("parametri_back.png");
        back_parmetri = new Texture("back.png");
        dalee_iz = new Texture("dalee.png");

        strel_lev_n_iz = new Texture("strelka_lev.png");
        yacheika_dlya_n_iz = new Texture("yacheika_dly_chisel.png");
        strel_prav_n_iz = new Texture("strelka_prav.png");

        bel_team_iz = new Texture("bel_team_vib.png");
        sin_team_iz = new Texture("sin_team_vib.png");
        vibran_cvet_iz = new Texture("vib_team.png");

        strel_lev_per_iz = new Texture("strelka_lev.png");
        yacheika_dlya_per_iz = new Texture("yacheika_dly_chisel.png");
        strel_prav_per_iz = new Texture("strelka_prav.png");


        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));


        back_menu_kn = new Button_menu(50, SCR_HEIGHT-(100), 200,80);
        dalee_kn = new Button_menu(SCR_WIDTH-200, SCR_HEIGHT-(100), 200,80);

        strel_lev_n = new Button_menu(SCR_WIDTH/2, SCR_HEIGHT*0.55f, 300/2.5f,300/2.9f);
        yacheika_dlya_n = new Button_menu(SCR_WIDTH/2+150, SCR_HEIGHT*0.55f, 300/2.5f,300/2.9f);
        strel_prav_n = new Button_menu(SCR_WIDTH/2 + 300, SCR_HEIGHT*0.55f, 300/2.5f,300/2.9f);


        bel_team = new Button_menu(SCR_WIDTH/2-150, SCR_HEIGHT*0.28f, 300/2.5f,300/2.5f);
        sin_team = new Button_menu(SCR_WIDTH/2, SCR_HEIGHT*0.28f, 300/2.5f,300/2.5f);
        vibr_cvet = new Button_menu(0,0, 300,300);

        strel_lev_per = new Button_menu(SCR_WIDTH/1.8f, SCR_HEIGHT*0.06f, 300/2.5f,300/2.9f);
        yacheika_dlya_per = new Button_menu(SCR_WIDTH/1.8f+150, SCR_HEIGHT*0.06f, 300/2.5f,300/2.9f);
        strel_prav_per = new Button_menu(SCR_WIDTH/1.8f + 300, SCR_HEIGHT*0.06f, 300/2.5f,300/2.9f);


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
                main.setScreen(main.vibor_rezima);
            }
            if (dalee_kn.hit(touch.x, touch.y)){
                String n_playera = new String(readFile("nomer_igroka.txt"));
                int n_playera_ch = Integer.parseInt(n_playera);
                if (n_playera_ch == 1){
                    overwriteFile("poz_igr.txt", "" + 0);
                    main.setScreen(main.standart);
                }
                else {
                    main.setScreen(main.vibor_poz);
                }
            }
            if (strel_prav_n.hit(touch.x, touch.y)){
                String n_playera = new String(readFile("nomer_igroka.txt"));
                int n_playera_ch = Integer.parseInt(n_playera);
                if (n_playera_ch + 1 > 15){
                    n_playera_ch = 1;
                    overwriteFile("nomer_igroka.txt", "" + n_playera_ch);
                }
                else{
                    n_playera_ch = n_playera_ch + 1;
                    overwriteFile("nomer_igroka.txt", "" + n_playera_ch);
                }

            }
            if (strel_lev_n.hit(touch.x, touch.y)){
                String n_playera = new String(readFile("nomer_igroka.txt"));
                int n_playera_ch = Integer.parseInt(n_playera);
                if (n_playera_ch - 1 < 1){
                    n_playera_ch = 15;
                    overwriteFile("nomer_igroka.txt", "" + n_playera_ch);
                }
                else{
                    n_playera_ch = n_playera_ch - 1;
                    overwriteFile("nomer_igroka.txt", "" + n_playera_ch);
                }

            }

            if (strel_prav_per.hit(touch.x, touch.y)){
                String kol_per = new String(readFile("kol_per.txt"));
                int kol_per_ch = Integer.parseInt(kol_per);
                if (kol_per_ch + 1 > 4){
                    kol_per_ch = 1;
                    overwriteFile("kol_per.txt", "" + kol_per_ch);
                }
                else{
                    kol_per_ch = kol_per_ch + 1;
                    overwriteFile("kol_per.txt", "" + kol_per_ch);
                }

            }
            if (strel_lev_per.hit(touch.x, touch.y)){
                String kol_per = new String(readFile("kol_per.txt"));
                int kol_per_ch = Integer.parseInt(kol_per);
                if (kol_per_ch - 1 < 1){
                    kol_per_ch = 4;
                    overwriteFile("kol_per.txt", "" + kol_per_ch);
                }
                else{
                    kol_per_ch = kol_per_ch - 1;
                    overwriteFile("kol_per.txt", "" + kol_per_ch);
                }
            }


            if (bel_team.hit(touch.x, touch.y)){
                overwriteFile("cvet_kom.txt", "" + 1);

            }
            if (sin_team.hit(touch.x, touch.y)){
                overwriteFile("cvet_kom.txt", "" + 2);
            }
        }

        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0,0, SCR_WIDTH, SCR_HEIGHT); // фон в меню

        // Отображение времени
        //font.draw(batch, Taim_played_vremya(TimeUtils.millis() - Timer_Start), SCR_WIDTH-200, SCR_HEIGHT-30);

        //кнопки в меню
        String cvet = new String(readFile("cvet_kom.txt"));
        int cvet_ch = Integer.parseInt(cvet);
        float nask = 40f;
        if (cvet_ch == 1){
            batch.draw(vibran_cvet_iz, bel_team.x-nask/2, bel_team.y-nask/2, bel_team.width+nask, bel_team.height+nask);
        }
        else {
            batch.draw(vibran_cvet_iz, sin_team.x-nask/2, sin_team.y-nask/2, sin_team.width+nask, sin_team.height+nask);
        }

        batch.draw(back_parmetri, back_menu_kn.x, back_menu_kn.y, back_menu_kn.width, back_menu_kn.height);
        batch.draw(dalee_iz, dalee_kn.x, dalee_kn.y, dalee_kn.width, dalee_kn.height);

        batch.draw(strel_lev_n_iz, strel_lev_n.x, strel_lev_n.y, strel_lev_n.width, strel_lev_n.height);
        batch.draw(yacheika_dlya_n_iz, yacheika_dlya_n.x, yacheika_dlya_n.y, yacheika_dlya_n.width, yacheika_dlya_n.height);
        batch.draw(strel_prav_n_iz, strel_prav_n.x, strel_prav_n.y, strel_prav_n.width, strel_prav_n.height);

        batch.draw(bel_team_iz, bel_team.x, bel_team.y, bel_team.width, bel_team.height);
        batch.draw(sin_team_iz, sin_team.x, sin_team.y, sin_team.width, sin_team.height);

        batch.draw(strel_lev_per_iz, strel_lev_per.x, strel_lev_per.y, strel_lev_per.width, strel_lev_per.height);
        batch.draw(yacheika_dlya_per_iz, yacheika_dlya_per.x, yacheika_dlya_per.y, yacheika_dlya_per.width, yacheika_dlya_per.height);
        batch.draw(strel_prav_per_iz, strel_prav_per.x, strel_prav_per.y, strel_prav_per.width, strel_prav_per.height);


        String n_playera = new String(readFile("nomer_igroka.txt"));
        String kol_per = new String(readFile("kol_per.txt"));
        //nadpisi
        font.draw(batch, n_playera, SCR_WIDTH/2+194, SCR_HEIGHT*0.70f);
        font.draw(batch, kol_per, SCR_WIDTH/1.8f+194, SCR_HEIGHT*0.21f);
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
        back_parmetri.dispose();
        glav_menu_back.dispose();
        font.dispose();
        strel_prav_per_iz.dispose();
        strel_lev_per_iz.dispose();
        strel_prav_n_iz.dispose();
        strel_lev_n_iz.dispose();
        bel_team_iz.dispose();
        dalee_iz.dispose();
        sin_team_iz.dispose();
        vibran_cvet_iz.dispose();
        yacheika_dlya_n_iz.dispose();
        yacheika_dlya_per_iz.dispose();

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
