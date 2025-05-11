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
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Popal implements Screen {
    //public static final float SCR_WIDTH = 1218, SCR_HEIGHT = 540;


    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font; // создание шрифта

    private Texture glav_menu_back;



    private Texture back_vibor;
    private Texture cel;
    private Texture popal;
    private Texture miach_iz;
    private Texture vratar_iz;
    private Texture lev_ruk;
    private Texture prav_ruk;
    //private Texture zv_mute_kn;
    long Timer_Start_popal;
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
    Button_menu popal_kn;
    Vratar_in_penalti vratar_kn;
    Vratar_in_penalti prav_ruk_kn;
    Vratar_in_penalti lev_ruk_kn;
    public int popad_podryat = 0;
    public float cel_x, cel_y;
    float rotation = 0;
    float x_myacha;
    int misen_popal;
    boolean stop_vr;


    public Penalti_myach myach;



    public Popal(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font2;




        glav_menu_back = new Texture("vorota.png");
        popal = new Texture("popal_tabl.png");
        prav_ruk = new Texture("ruka_prav.png");
        lev_ruk = new Texture("ruka_lev.png");

        //Создание текста
        //font = new BitmapFont(Gdx.files.internal("srift_menu.fnt"));

        //кнопки в меню
        back_menu_kn = new Button_menu(SCR_WIDTH - 110, SCR_HEIGHT - 40, 100, 40);
        cel_kn1 = new Button_menu(40, SCR_HEIGHT - 200, 150, 150);
        cel_kn2 = new Button_menu((SCR_WIDTH / 2) - 75, SCR_HEIGHT - 200, 150, 150);
        cel_kn3 = new Button_menu(SCR_WIDTH - 190, SCR_HEIGHT - 200, 150, 150);
        cel_kn4 = new Button_menu(40, SCR_HEIGHT - 375, 150, 150);
        cel_kn5 = new Button_menu((SCR_WIDTH / 2) - 165, SCR_HEIGHT - 375, 150, 150);
        cel_kn6 = new Button_menu((SCR_WIDTH / 2) + 15, SCR_HEIGHT - 375, 150, 150);
        cel_kn7 = new Button_menu(SCR_WIDTH - 190, SCR_HEIGHT - 375, 150, 150);
        cel_kn8 = new Button_menu(40, SCR_HEIGHT - 535, 150, 150);
        cel_kn9 = new Button_menu(SCR_WIDTH - 190, SCR_HEIGHT - 535, 150, 150);
        cel_kn10 = new Button_menu((SCR_WIDTH / 2) - 375, SCR_HEIGHT - 300, 150, 150);
        cel_kn11 = new Button_menu((SCR_WIDTH / 2) + 375 - 150, SCR_HEIGHT - 300, 150, 150);
        cel_kn12 = new Button_menu((SCR_WIDTH / 2) - 375, SCR_HEIGHT - 500, 150, 150);
        cel_kn13 = new Button_menu((SCR_WIDTH / 2) + 375 - 150, SCR_HEIGHT - 500, 150, 150);
        popal_kn = new Button_menu(SCR_WIDTH/2 - 250, SCR_HEIGHT - 500, 500, 400);
        float nvrt = 1.3f;


    }

    @Override
    public void show() {
        float width_iz = 108 * 1.6f;
        stop_vr = false;
        Timer_Start_popal = TimeUtils.millis();
        String misen = new String(readFile());
        System.out.println("" + misen);
        if (("" + misen).equals("1")){
            cel_x = 40;
            cel_y = SCR_HEIGHT-200;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("2")){
            cel_x = (SCR_WIDTH/2)-75;
            cel_y = SCR_HEIGHT-200;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("3")){
            cel_x = SCR_WIDTH-190;
            cel_y = SCR_HEIGHT-200;
            x_myacha = width_iz + 15;
        }
        if (("" + misen).equals("4")){
            cel_x = 40;
            cel_y = SCR_HEIGHT-375;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("5")){
            cel_x = (SCR_WIDTH/2)-165;
            cel_y = SCR_HEIGHT-375;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("6")){
            cel_x = (SCR_WIDTH/2)+15;
            cel_y =  SCR_HEIGHT-375;
            x_myacha = width_iz + 15;
        }
        if (("" + misen).equals("7")){
            cel_x = SCR_WIDTH-190;
            cel_y =  SCR_HEIGHT-375;
            x_myacha = width_iz + 15;
        }
        if (("" + misen).equals("8")){
            cel_x = 40;
            cel_y =  SCR_HEIGHT-535;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("9")){
            cel_x = SCR_WIDTH-190;
            cel_y =  SCR_HEIGHT-535;
            x_myacha = width_iz + 15;
        }
        if (("" + misen).equals("10")){
            cel_x = (SCR_WIDTH/2)-375;
            cel_y =  SCR_HEIGHT-300;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("11")){
            cel_x = (SCR_WIDTH/2)+375-150;
            cel_y = SCR_HEIGHT-300;
            x_myacha = width_iz + 15;
        }
        if (("" + misen).equals("12")){
            cel_x = (SCR_WIDTH/2)-375;
            cel_y = SCR_HEIGHT-500;
            x_myacha = SCR_WIDTH - width_iz;
        }
        if (("" + misen).equals("13")){
            cel_x =  (SCR_WIDTH/2)+375-150;
            cel_y = SCR_HEIGHT-500;
            x_myacha = width_iz + 15;
        }
        misen_popal = Integer.valueOf("" + misen);
        System.out.println(cel_x);
        System.out.println(cel_y);



        vratar_kn = new Vratar_in_penalti(0, 3, 0, 0);
        lev_ruk_kn = new Vratar_in_penalti(1, 3, 0, 0);
        prav_ruk_kn = new Vratar_in_penalti(2, 3, 0, 0);


        myach = new Penalti_myach(cel_x, cel_y, x_myacha);


    }
    //float r;
    @Override
    public void render(float delta) {
        miach_iz = new Texture("miach.png");
        vratar_iz = new Texture("vratar_v_plavkah.png");
        back_vibor = new Texture("back.png");
        cel = new Texture("cel.png");



        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0, 0, SCR_WIDTH, SCR_HEIGHT); // фон в меню

        // надпись в меню
        font.draw(batch, "Выбери куда хочешь ударить!!!", 5, SCR_HEIGHT - 5);

        //кнопки в меню
        batch.draw(back_vibor, back_menu_kn.x, back_menu_kn.y, back_menu_kn.width, back_menu_kn.height);
        //цели
        /*
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
        */
        //r += 0.3f;
        batch.draw(vratar_iz, vratar_kn.x_vr, vratar_kn.y_vr,  // Позиция (x, y)
            vratar_kn.width_vr/2, 90,  // Точка вращения (центр изображения)
            vratar_kn.width_vr, vratar_kn.height_vr,     // Размеры
            1, 1,             // Масштаб
            vratar_kn.rotation_vr,          // Угол вращения (в градусах)
            0, 0,             // Область текстуры (srcX, srcY)
            446, 598, // Размеры текстуры
            false, false       // Отразить по X/Y?
        );
        batch.draw(lev_ruk, lev_ruk_kn.x_lev, lev_ruk_kn.y_lev,             // Позиция (x, y)
            lev_ruk_kn.width_lev-57, 52,  // Точка вращения (центр изображения)
            lev_ruk_kn.width_lev, lev_ruk_kn.height_lev,     // Размеры
            1, 1,             // Масштаб
            lev_ruk_kn.rotation_lev,          // Угол вращения (в градусах)
            0, 0,             // Область текстуры (srcX, srcY)
            400, 400, // Размеры текстуры
            false, false       // Отразить по X/Y?
        );
        batch.draw(prav_ruk, prav_ruk_kn.x_prav, prav_ruk_kn.y_prav,             // Позиция (x, y)
            57, 52,  // Точка вращения (центр изображения)
            prav_ruk_kn.width_prav, prav_ruk_kn.height_prav,     // Размеры
            1, 1,             // Масштаб
            prav_ruk_kn.rotation_prav,          // Угол вращения (в градусах)
            0, 0,             // Область текстуры (srcX, srcY)
            400, 400, // Размеры текстуры
            false, false       // Отразить по X/Y?
        );
        vratar_kn.popal(misen_popal, stop_vr);
        prav_ruk_kn.popal(misen_popal, stop_vr);
        lev_ruk_kn.popal(misen_popal, stop_vr);


        //batch.draw(miach_iz, myach.x, myach.y, myach.width_iz, myach.height_iz);
        myach.dvizenie_in_cel();
        batch.draw(
            miach_iz,
            myach.x, myach.y,             // Позиция (x, y)
            myach.width_iz/2, myach.height_iz/2,  // Точка вращения (центр изображения)
            myach.width_iz, myach.height_iz,     // Размеры
            1, 1,             // Масштаб
            rotation,          // Угол вращения (в градусах)
            0, 0,             // Область текстуры (srcX, srcY)
            miach_iz.getWidth(), miach_iz.getHeight(), // Размеры текстуры
            false, false       // Отразить по X/Y?
        );
        rotation += myach.rotation;
        Delay_vremya2(TimeUtils.millis() - Timer_Start_popal);
        Delay_vremya(TimeUtils.millis() - Timer_Start_popal);
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
        //myach = new Penalti_myach();
    }

    @Override
    public void dispose() {
        batch.dispose();
        glav_menu_back.dispose();
        font.dispose();
        cel.dispose();
        popal.dispose();
        back_vibor.dispose();
        miach_iz.dispose();
    }
    private String Delay_vremya(long Timer){
        long sec = Timer/1000%60;
        if (sec >= 9){
            main.setScreen(main.penalti);
            Timer_Start_popal = TimeUtils.millis();
            pere_zapis("data/vibrannay_cel.txt", "0");
        }
        return sec + "";
    }
    private String Delay_vremya2(long Timer){
        long sec = Timer/1000%60;
        if (sec >= 6){
            batch.draw(popal, popal_kn.x, popal_kn.y, popal_kn.width, popal_kn.height);

        }
        if (sec >= 5){
            stop_vr = true;
        }
        return sec + "";
    }

    private String readFile()
    {
        try {
            FileHandle file = Gdx.files.local("data/" + "vibrannay_cel.txt");
            if (file.exists()) {
                System.out.println(file.readString() + "- vibr cell");
                return file.readString();
            }
        } catch (Exception e) {
            Gdx.app.error("FILE", "Ошибка чтения: " + "vibrannay_cel.txt", e);
        }
        return "";
    }


    public static void pere_zapis(String filename, String content) {
        try {
            // Сначала открываем для очистки
            new FileWriter(filename, false).close();

            // Затем записываем новый контент
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
