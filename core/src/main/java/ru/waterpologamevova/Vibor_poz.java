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

public class Vibor_poz implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture glav_menu_back;
    private Texture dalee_iz;
    private Texture back_parmetri;
    private Texture poz1_iz;
    private Texture poz2_iz;
    private Texture poz3_iz;
    private Texture poz4_iz;
    private Texture poz5_iz;
    private Texture poz6_iz;
    private Texture tek_poz_iz;

    //private Texture zv_mute_kn;

    private String nadpis_menu; // надпись в меню

    // таймер на 30 сек
    long Timer_Start;
    long Timer_Start_m;

    // кнопки в меню
    Button_menu back_menu_kn;
    Button_menu dalee_kn;
    Button_menu poz1;
    Button_menu poz2;
    Button_menu poz3;
    Button_menu poz4;
    Button_menu poz5;
    Button_menu poz6;
    Button_menu tek_poz;


    int sk_myachei_v_menu = 33;


    public Vibor_poz(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font4;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();



        // создание картинок
        glav_menu_back = new Texture("back_vib_poz.png");
        back_parmetri = new Texture("back.png");
        dalee_iz = new Texture("dalee.png");
        poz1_iz = new Texture("poz1.png");
        poz2_iz = new Texture("poz2.png");
        poz3_iz = new Texture("poz3.png");
        poz4_iz = new Texture("poz4.png");
        poz5_iz = new Texture("poz5.png");
        poz6_iz = new Texture("poz6.png");
        tek_poz_iz = new Texture("tek_poz.png");

        //Создание текста



        back_menu_kn = new Button_menu(50, SCR_HEIGHT-(100), 200,80);
        dalee_kn = new Button_menu(SCR_WIDTH-230, SCR_HEIGHT-(100), 200,80);
        float nas = 3f;
        poz1  = new Button_menu(SCR_WIDTH-230,  SCR_HEIGHT-300-300/nas, 300/nas,300/nas);
        poz2  = new Button_menu(SCR_WIDTH/2+(300/nas)*2, SCR_HEIGHT-150-300/nas, 300/nas,300/nas);
        poz3  = new Button_menu(SCR_WIDTH/2-(300/nas)/2, SCR_HEIGHT-100-300/nas, 300/nas,300/nas);
        poz4  = new Button_menu(SCR_WIDTH/2-(300/nas)*3, SCR_HEIGHT-150-300/nas, 300/nas,300/nas);
        poz5  = new Button_menu(330-((300*2)/nas),  SCR_HEIGHT-300-300/nas, 300/nas,300/nas);
        poz6  = new Button_menu(SCR_WIDTH/2-(300/nas)/2, SCR_HEIGHT-300-300/nas, 300/nas,300/nas);
        tek_poz  = new Button_menu(0, 0, 300/nas + 20,300/nas + 20);


        // время создания всего
        Timer_Start = TimeUtils.millis();
        Timer_Start_m = TimeUtils.millis();
    }

    @Override
    public void show() {
        String num_pl = new String(readFile("nomer_igroka.txt"));
        int num_pl_ch = Integer.parseInt(num_pl);
        if (num_pl_ch != 1){
            overwriteFile("poz_igr.txt", "" + 3);
            //System.out.println("Ne vratar pos 3");
        }

    }

    @Override
    public void render(float delta) {
        // касания
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (back_menu_kn.hit(touch.x, touch.y)){
                main.setScreen(main.parametri_igri);
            }
            if (dalee_kn.hit(touch.x, touch.y)){
                main.setScreen(main.standart);
            }
            if (poz1.hit(touch.x, touch.y)){
                overwriteFile("poz_igr.txt", "" + 1);
            }
            if (poz2.hit(touch.x, touch.y)){
                overwriteFile("poz_igr.txt", "" + 2);
            }
            if (poz3.hit(touch.x, touch.y)){
                overwriteFile("poz_igr.txt", "" + 3);
            }
            if (poz4.hit(touch.x, touch.y)){
                overwriteFile("poz_igr.txt", "" + 4);
            }
            if (poz5.hit(touch.x, touch.y)){
                overwriteFile("poz_igr.txt", "" + 5);
            }
            if (poz6.hit(touch.x, touch.y)){
                overwriteFile("poz_igr.txt", "" + 6);
            }
        }

        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0,0, SCR_WIDTH, SCR_HEIGHT); // фон в меню

        // Отображение времени
        //font.draw(batch, Taim_played_vremya(TimeUtils.millis() - Timer_Start), SCR_WIDTH-200, SCR_HEIGHT-30);

        //кнопки в меню
        batch.draw(back_parmetri, back_menu_kn.x, back_menu_kn.y, back_menu_kn.width, back_menu_kn.height);
        batch.draw(dalee_iz, dalee_kn.x, dalee_kn.y, dalee_kn.width, dalee_kn.height);

        String poz = new String(readFile("poz_igr.txt"));
        int poz_ch = Integer.parseInt(poz);
        float nask = 40f;
        if (poz_ch == 1){
            batch.draw(tek_poz_iz, poz1.x-nask/2, poz1.y-nask/2, poz1.width+nask, poz1.height+nask);
        }
        else if (poz_ch == 2){
            batch.draw(tek_poz_iz, poz2.x-nask/2, poz2.y-nask/2, poz2.width+nask, poz2.height+nask);
        }
        else if (poz_ch == 3){
            batch.draw(tek_poz_iz, poz3.x-nask/2, poz3.y-nask/2, poz3.width+nask, poz3.height+nask);
        }
        else if (poz_ch == 4){
            batch.draw(tek_poz_iz, poz4.x-nask/2, poz4.y-nask/2, poz4.width+nask, poz4.height+nask);
        }
        else if (poz_ch == 5){
            batch.draw(tek_poz_iz, poz5.x-nask/2, poz5.y-nask/2, poz5.width+nask, poz5.height+nask);
        }
        else if (poz_ch == 6){
            batch.draw(tek_poz_iz, poz6.x-nask/2, poz6.y-nask/2, poz6.width+nask, poz6.height+nask);
        }
        batch.draw(poz1_iz, poz1.x, poz1.y, poz1.width, poz1.height);
        batch.draw(poz2_iz, poz2.x, poz2.y, poz2.width, poz2.height);
        batch.draw(poz3_iz, poz3.x, poz3.y, poz3.width, poz3.height);
        batch.draw(poz4_iz, poz4.x, poz4.y, poz4.width, poz4.height);
        batch.draw(poz5_iz, poz5.x, poz5.y, poz5.width, poz5.height);
        batch.draw(poz6_iz, poz6.x, poz6.y, poz6.width, poz6.height);


        font.draw(batch, "Выбери позицию", SCR_WIDTH/2-180, SCR_HEIGHT*0.96f);

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
        dalee_iz.dispose();
        font.dispose();
        poz1_iz.dispose();
        poz2_iz.dispose();
        poz3_iz.dispose();
        poz4_iz.dispose();
        poz5_iz.dispose();
        poz6_iz.dispose();
        tek_poz_iz.dispose();
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
