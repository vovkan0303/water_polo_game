package ru.waterpologamevova;
import static com.badlogic.gdx.Gdx.input;
import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Standart implements Screen {
    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;


    private BitmapFont font;
    private Texture imgJostik;
    private Texture glav_menu_back;
    private Texture  imgSprint;
    private Texture  imgUdar;
    private Texture player_img;
    private Texture igrovoi_myach_iz;
    private Jostik jostik;
    private Player player;
    private Texture krestik_iz;
    Button_menu krestik;
    private Igrovoi_myach igrovoi_myach;
    private int r_kas_myach = 70;
    private boolean u_igroka_myach_sey;
    private Sprint_kn sprint_kn;
    private Udar_kn udar_kn;
    public static float WORLD_HEIHGT = SCR_HEIGHT*5;
    long Timer_Start_perioda;
    long Timer_Start_matcha;

    // inic botov
    private Bot bot_soyus_1;
    private Bot bot_soyus_2;
    private Bot bot_soyus_3;
    private Bot bot_soyus_4;
    private Bot bot_soyus_5;
    private Bot bot_soyus_vr;
    private Bot bot_vrag_1;
    private Bot bot_vrag_2;
    private Bot bot_vrag_3;
    private Bot bot_vrag_4;
    private Bot bot_vrag_5;
    private Bot bot_vrag_6;
    private Bot  bot_vrag_vr;




    //конструктор
    public Standart(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font4;
        Gdx.input.setInputProcessor(new SpaceXProcessor());
        imgJostik = new Texture("jostik.png");


        glav_menu_back = new Texture("pole.png");
        imgSprint = new Texture("sprint.png");
        imgUdar = new Texture("udar.png");
        igrovoi_myach_iz = new Texture("myach_igr.png");
        krestik_iz = new Texture("krestik.png");
        jostik = new Jostik();
        player = new Player(SCR_WIDTH/2, SCR_HEIGHT/2);
        igrovoi_myach = new Igrovoi_myach(SCR_WIDTH/2+200, SCR_HEIGHT/2);
        udar_kn = new Udar_kn();
        sprint_kn = new Sprint_kn();
        krestik = new Button_menu(50, SCR_HEIGHT-(100), 70,70);
    }

    @Override
    public void show() {
        String n_playera = new String(readFile("nomer_igroka.txt"));
        String cvet = new String(readFile("cvet_kom.txt"));
        int n_playera_ch = Integer.parseInt(n_playera);
        int cvet_ch = Integer.parseInt(cvet);
        if (cvet_ch == 1){
            if (n_playera_ch == 1){
                player_img = new Texture("1_vr_white.png");
            }
            else {
                player_img = new Texture(n_playera+"_white.png");
            }

        }
        else {
            if (n_playera_ch == 1){
                player_img = new Texture("1_vr_blue.png");
            }
            else {
                player_img = new Texture(n_playera+"_blue.png");
            }

        }
        /*
        Random random = new Random();
        int excludedNumber = n_playera_ch;
        int randomNumber;

        do {
            randomNumber = random.nextInt(15) + 1; // 1-15
        } while (randomNumber == excludedNumber);

        System.out.println("Sluch ch  (bez " + excludedNumber + "): " + randomNumber);
        */




        Random random = new Random();
        int excludedNumber = n_playera_ch; // Число, которое нужно исключить
        Set<String> generatedNumbers = new HashSet<>(); // Храним уникальные числа

        while (generatedNumbers.size() < 5) { // Пока не наберем 5 чисел
            int randomNumber = random.nextInt(15) + 1; // 1-15

            // Добавляем, если не исключенное и не повторяется
            if (randomNumber != excludedNumber && randomNumber != 1) {
                String random_n_str = "" + randomNumber;
                generatedNumbers.add(random_n_str);
            }
        }

        System.out.println("5 un ch (bez " + excludedNumber + " i 1): " + generatedNumbers);
        List<String> Nomera_botov_spisok = new ArrayList<>(generatedNumbers);
        String nomer_bot_souz_1 = Nomera_botov_spisok.get(0);
        String nomer_bot_souz_2 = Nomera_botov_spisok.get(1);
        String nomer_bot_souz_3 = Nomera_botov_spisok.get(2);
        String nomer_bot_souz_4 = Nomera_botov_spisok.get(3);
        String nomer_bot_souz_5 = Nomera_botov_spisok.get(4);
        String nomer_bot_souz_vr = "1";



        if (player.y <= SCR_HEIGHT/2){
            camera.position.set(SCR_WIDTH/2, SCR_HEIGHT/2, 0);
        }
        else if(player.y >= WORLD_HEIHGT- SCR_HEIGHT/2){
            camera.position.set(SCR_WIDTH/2, WORLD_HEIHGT- SCR_HEIGHT/2, 0);
        }
        else {
            camera.position.set(SCR_WIDTH/2, player.y, 0);
        }
        camera.update();
        // время создания всего
        Timer_Start_perioda = TimeUtils.millis();
        Timer_Start_matcha = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (krestik.hit(touch.x+krestik.width/2, touch.y+krestik.height/2)){
                main.setScreen(main.vibor_rezima);
            }
        }

        // Отрисовка
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0, 0, SCR_WIDTH, WORLD_HEIHGT); // фон в меню


        batch.draw(imgJostik, jostik.scrX(), jostik.scrY(camera.position.y-SCR_HEIGHT/4), jostik.width, jostik.height);
        batch.draw(imgUdar, udar_kn.scrX(), udar_kn.scrY(camera.position.y-SCR_HEIGHT/4), udar_kn.width, udar_kn.height);
        batch.draw(imgSprint, sprint_kn.scrX(), sprint_kn.scrY(camera.position.y + 150), sprint_kn.width, sprint_kn.height);
        batch.draw(player_img, player.scrX(), player.scrY(), player.width, player.height);
        batch.draw(krestik_iz, krestik.scrX(), krestik.scrY(camera.position.y+SCR_HEIGHT/2.4f), krestik.width, krestik.height);
        batch.draw(igrovoi_myach_iz, igrovoi_myach.x, igrovoi_myach.y, igrovoi_myach.width, igrovoi_myach.height);
        // Отображение времени
        font.draw(batch, Taim_played_vremya(TimeUtils.millis() - Timer_Start_matcha), 100, camera.position.y+SCR_HEIGHT/2-15);



        // sobitia
        igrovoi_myach.move(0);
        if ((((player.scrX() + r_kas_myach) >= igrovoi_myach.scrX()) && ((player.scrX() - r_kas_myach) <= igrovoi_myach.scrX())) && (((player.scrY() + r_kas_myach) >= igrovoi_myach.scrY()) && ((player.scrY() - r_kas_myach) <= igrovoi_myach.scrY()))) {
            igrovoi_myach.u_igroka(player.scrX(), player.scrY(), true, igrovoi_myach.vx, igrovoi_myach.vy);
        }


        if (player.y <= SCR_HEIGHT/2){
            camera.position.set(SCR_WIDTH/2, SCR_HEIGHT/2, 0);
        }
        else if(player.y >= WORLD_HEIHGT- SCR_HEIGHT/2){
            camera.position.set(SCR_WIDTH/2, WORLD_HEIHGT- SCR_HEIGHT/2, 0);
        }
        else {camera.position.y += (player.y - camera.position.y) * 1f;}

        camera.update();
        player.move();
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
        glav_menu_back.dispose();
        font.dispose();
        imgJostik.dispose();
        imgUdar.dispose();
        igrovoi_myach_iz.dispose();
    }
    class SpaceXProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);
            if(jostik.isTouchInside(touch)) {
                player.touchJoystick(touch, jostik);}
            if(udar_kn.isTouchInside(touch)) {
                igrovoi_myach.touchJoystick_udar(touch, udar_kn);}
            if (sprint_kn.isTouchInside(touch)){
                player.sprint(); // ne rab
            }
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);
            player.stop();
            if (udar_kn.isTouchInside(touch)){
                if ((((player.scrX() + r_kas_myach) >= igrovoi_myach.scrX()) && ((player.scrX() - r_kas_myach) <= igrovoi_myach.scrX())) && (((player.scrY() + r_kas_myach) >= igrovoi_myach.scrY()) && ((player.scrY() - r_kas_myach) <= igrovoi_myach.scrY()))){
                    //igrovoi_myach.udar_v_napr(player.scrX(), player.scrY(), false, touch, jostik);
                    igrovoi_myach.otpustil(igrovoi_myach.vx_bud, igrovoi_myach.vy_bud);
                }
            }
                //igrovoi_myach.pridat_skor(napr_udar());
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);
            if(jostik.isTouchInside(touch)) {
                player.touchJoystick(touch, jostik);
            }
            if(udar_kn.isTouchInside(touch)) {
                igrovoi_myach.touchJoystick_udar(touch, udar_kn);
                //player.touchJoystick(touch, jostik);
            }
            if (sprint_kn.isTouchInside(touch)){
                player.sprint();
            }
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
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
}
