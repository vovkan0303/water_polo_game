package ru.waterpologamevova;
import static com.badlogic.gdx.Gdx.input;
import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Standart implements Screen {
    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private Music game_sound;
    private Sound svistok;

    private Logika_igri logika_igri;


    private BitmapFont font;
    private BitmapFont font2;
    private BitmapFont font_5s;

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
    long Timer_Start_igri;
    long Timer_Start_posle_gola;

    // inic botov
    private Bot bot_soyus_1;
    private Bot bot_soyus_2;
    private Bot bot_soyus_3;
    private Bot bot_soyus_4;
    private Bot bot_soyus_5;
    private Bot bot_soyus_6;
    private Bot bot_soyus_vr;
    private Bot bot_vrag_1;
    private Bot bot_vrag_2;
    private Bot bot_vrag_3;
    private Bot bot_vrag_4;
    private Bot bot_vrag_5;
    private Bot bot_vrag_6;
    private Bot  bot_vrag_vr;


    private Texture bot_soyus_1_iz;
    private Texture bot_soyus_2_iz;
    private Texture bot_soyus_3_iz;
    private Texture bot_soyus_4_iz;
    private Texture bot_soyus_5_iz;
    private Texture bot_soyus_6_iz;
    private Texture bot_soyus_vr_iz;
    private Texture bot_vrag_1_iz;
    private Texture bot_vrag_2_iz;
    private Texture bot_vrag_3_iz;
    private Texture bot_vrag_4_iz;
    private Texture bot_vrag_5_iz;
    private Texture bot_vrag_6_iz;
    private Texture  bot_vrag_vr_iz;
    private Texture dlya_otcheta;
    private Texture dlya_otcheta_gol_in_vrag_vor;
    private Texture dlya_otcheta_gol_in_souz_vor;
    private Texture obvodka;
    private Texture jos_udr_krug;
    private Texture jos_dviz_krug;


    public int kto_player = 0;

    public int poz_1_souz = 1;
    public int poz_2_souz = 2;
    public int poz_3_souz = 3;
    public int poz_4_souz = 4;
    public int poz_5_souz = 5;
    public int poz_6_souz = 6;

    private int nazat_jostik_dviz = 0;
    private int nazat_jostik_udara = 0;


    //конструктор
    public Standart(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font4;
        font2 = main.font_taimer;
        font_5s = main.font_taimer_5s;
        Gdx.input.setInputProcessor(new SpaceXProcessor());
        imgJostik = new Texture("jostik.png");


        dlya_otcheta = new Texture("yacheika_dly_chisel.png");
        dlya_otcheta_gol_in_vrag_vor = new Texture("Gol.png");
        dlya_otcheta_gol_in_souz_vor = new Texture("vib_team.png");
        obvodka = new Texture("tek_poz.png");
        jos_udr_krug= new Texture("tek_poz.png");
        jos_dviz_krug= new Texture("jos_n.png");

        glav_menu_back = new Texture("pole.png");
        imgSprint = new Texture("sprint.png");
        imgUdar = new Texture("udar.png");
        igrovoi_myach_iz = new Texture("myach_igr.png");
        krestik_iz = new Texture("krestik.png");
        jostik = new Jostik();
        player = new Player(8 * SCR_WIDTH/9f, SCR_HEIGHT/2);
        igrovoi_myach = new Igrovoi_myach(8 * SCR_WIDTH/9f, WORLD_HEIHGT/2 - 40);
        udar_kn = new Udar_kn();
        sprint_kn = new Sprint_kn();
        krestik = new Button_menu(50, SCR_HEIGHT-(100), 70,70);


        logika_igri = new Logika_igri();


        //boti koord
        float pn = 9f;
        //souz
        bot_soyus_1 = new Bot(1*SCR_WIDTH/pn-70, SCR_HEIGHT/2,1);
        bot_soyus_2 = new Bot(2*SCR_WIDTH/pn-70, SCR_HEIGHT/2,2);
        bot_soyus_3 = new Bot(3*SCR_WIDTH/pn-70, SCR_HEIGHT/2,3);
        bot_soyus_4 = new Bot(6*SCR_WIDTH/pn+70, SCR_HEIGHT/2,4);
        bot_soyus_5 = new Bot(7*SCR_WIDTH/pn+70, SCR_HEIGHT/2,5);
        bot_soyus_6 = new Bot(7*SCR_WIDTH/pn+70, SCR_HEIGHT/2,6);
        bot_soyus_vr = new Bot( SCR_WIDTH/2, SCR_HEIGHT/2,0);
        //vrag
        bot_vrag_1 = new Bot(1*SCR_WIDTH/pn-60, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,1);
        bot_vrag_2 = new Bot(2*SCR_WIDTH/pn-60, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,2);
        bot_vrag_3 = new Bot(3*SCR_WIDTH/pn-60, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,3);
        bot_vrag_4 = new Bot(6*SCR_WIDTH/pn+60, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,4);
        bot_vrag_5 = new Bot(7*SCR_WIDTH/pn+60, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,5);
        bot_vrag_6 = new Bot((8 * SCR_WIDTH/9f) + 50, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,6);
        bot_vrag_vr = new Bot(SCR_WIDTH/2, (WORLD_HEIHGT - SCR_HEIGHT/2) + 30,0);




    }

    @Override
    public void show() {

        //создание звуков

        game_sound = Gdx.audio.newMusic(Gdx.files.internal("game_sound.mp3"));
        // музыка играет зациклино
        game_sound.play();
        game_sound.setLooping(true);
        game_sound.setVolume(0.4f);


        logika_igri.isp_start_perioda = 0;
        logika_igri.isp_kto_zaber_myach = 0;
        logika_igri.isp_bil_zabit_souz_vor = 0;
        logika_igri.isp_bil_zabit_vrag_vor = 0;

        kto_player = 0;
        String n_pos_playera = new String(readFile("poz_igr.txt"));
        int n_pos_playera_ch = Integer.parseInt(n_pos_playera);
        String[] z = new String[]{ "0", "1", "2", "3", "4", "5", "6"};
        ArrayList<String> list_poz = new ArrayList<>(Arrays.asList(z));

        for (int i = list_poz.size()-1; i >= 0; i--) {
            if (n_pos_playera.equals(list_poz.get(i))){
                list_poz.remove(i);
            }
        }
        System.out.println(list_poz);
        if (n_pos_playera_ch != 0){
            if (n_pos_playera_ch == bot_soyus_1.pos) {
                bot_soyus_1.pos = bot_soyus_6.pos;
                poz_1_souz = 6;
            }
            else if (n_pos_playera_ch == bot_soyus_2.pos){
                bot_soyus_2.pos = bot_soyus_6.pos;
                poz_2_souz = 6;
            }
            else if (n_pos_playera_ch == bot_soyus_3.pos){
                bot_soyus_3.pos = bot_soyus_6.pos;
                poz_3_souz = 6;
            }
            else if (n_pos_playera_ch == bot_soyus_4.pos){
                bot_soyus_4.pos = bot_soyus_6.pos;
                poz_4_souz = 6;
            }
            else if (n_pos_playera_ch == bot_soyus_5.pos){
                bot_soyus_5.pos = bot_soyus_6.pos;
                poz_5_souz = 6;
            }
            else if (n_pos_playera_ch == bot_soyus_6.pos){
                bot_soyus_6.pos = bot_soyus_6.pos;
                poz_6_souz = 6;
            }

            //for (int i = 0; i < list_poz.size(); i++) {

              //  System.out.println(list_poz.get(i));
            //}
        }


        // obnov poz pers
        float pn = 9f;
        player.restart();
        igrovoi_myach.restart();
        igrovoi_myach.stop();
        player.x = 8 * SCR_WIDTH/9f + 70;
        player.y = SCR_HEIGHT/2;
        // souz
        bot_soyus_1.x = 1*SCR_WIDTH/pn-70;
        bot_soyus_1.y = SCR_HEIGHT/2;
        bot_soyus_2.x = 2*SCR_WIDTH/pn-70;
        bot_soyus_2.y = SCR_HEIGHT/2;
        bot_soyus_3.x = 3*SCR_WIDTH/pn-70;
        bot_soyus_3.y = SCR_HEIGHT/2;
        bot_soyus_4.x = 6*SCR_WIDTH/pn+70;
        bot_soyus_4.y = SCR_HEIGHT/2;
        bot_soyus_5.x = 7*SCR_WIDTH/pn+70;
        bot_soyus_5.y = SCR_HEIGHT/2;
        bot_soyus_vr.x =  SCR_WIDTH/2;
        bot_soyus_vr.y = SCR_HEIGHT/2-30;
        //vragi
        bot_vrag_1.x = 1*SCR_WIDTH/pn-60;
        bot_vrag_1.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;
        bot_vrag_2.x = 2*SCR_WIDTH/pn-60;
        bot_vrag_2.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;
        bot_vrag_3.x = 3*SCR_WIDTH/pn-60;
        bot_vrag_3.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;
        bot_vrag_4.x = 6*SCR_WIDTH/pn+60;
        bot_vrag_4.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;
        bot_vrag_5.x = 7*SCR_WIDTH/pn+60;
        bot_vrag_5.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;
        bot_vrag_6.x = (8 * SCR_WIDTH/9f) + 50;
        bot_vrag_6.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;
        bot_vrag_vr.x =  SCR_WIDTH/2+30;
        bot_vrag_vr.y = (WORLD_HEIHGT - SCR_HEIGHT/2) + 30;


        String n_playera = new String(readFile("nomer_igroka.txt"));
        int n_playera_ch = Integer.parseInt(n_playera);

        Random random = new Random();
        int excludedNumber = n_playera_ch; // Число, которое нужно исключить
        Set<String> generatedNumbers = new HashSet<>(); // Храним уникальные числа

        while (generatedNumbers.size() < 6) { // Пока не наберем 5 чисел
            int randomNumber = random.nextInt(15) + 1; // 1-15

            // Добавляем, если не исключенное и не повторяется
            if (randomNumber != excludedNumber && randomNumber != 1) {
                String random_n_str = "" + randomNumber;
                generatedNumbers.add(random_n_str);
            }
        }

        //System.out.println("5 un ch (bez " + excludedNumber + " i 1): " + generatedNumbers);
        List<String> Nomera_botov_spisok = new ArrayList<>(generatedNumbers);

        String nomer_bot_souz_1 = Nomera_botov_spisok.get(0);
        String nomer_bot_souz_2 = Nomera_botov_spisok.get(1);
        String nomer_bot_souz_3 = Nomera_botov_spisok.get(2);
        String nomer_bot_souz_4 = Nomera_botov_spisok.get(3);
        String nomer_bot_souz_5 = Nomera_botov_spisok.get(4);
        String nomer_bot_souz_6 = Nomera_botov_spisok.get(5);
        String nomer_bot_souz_vr = "1";


        Set<String> generatedNumbers2 = new HashSet<>(); // Храним уникальные числа

        while (generatedNumbers2.size() < 6) { // Пока не наберем 6 чисел
            int randomNumber = random.nextInt(15) + 1; // 1-15

            // Добавляем, если не исключенное и не повторяется
            if (randomNumber != excludedNumber && randomNumber != 1 && randomNumber != 13) {
                String random_n_str = "" + randomNumber;
                generatedNumbers2.add(random_n_str);
            }
        }

        //System.out.println("5 un ch (bez " + excludedNumber + " i 1 i 13): " + generatedNumbers2);
        List<String> Nomera_botov_spisok2 = new ArrayList<>(generatedNumbers2);


        String nomer_bot_vrag_1 = Nomera_botov_spisok2.get(0);
        String nomer_bot_vrag_2 = Nomera_botov_spisok2.get(1);
        String nomer_bot_vrag_3 = Nomera_botov_spisok2.get(2);
        String nomer_bot_vrag_4 = Nomera_botov_spisok2.get(3);
        String nomer_bot_vrag_5 = Nomera_botov_spisok2.get(4);
        String nomer_bot_vrag_6 = Nomera_botov_spisok2.get(5);
        String nomer_bot_vrag_vr = "13";



        n_playera = new String(readFile("nomer_igroka.txt"));
        String cvet = new String(readFile("cvet_kom.txt"));
        n_playera_ch = Integer.parseInt(n_playera);
        int cvet_ch = Integer.parseInt(cvet);
        if (cvet_ch == 1){
            // text souz
            bot_soyus_1_iz = new Texture(nomer_bot_souz_1+"_white.png");
            bot_soyus_2_iz = new Texture(nomer_bot_souz_2+"_white.png");
            bot_soyus_3_iz = new Texture(nomer_bot_souz_3+"_white.png");
            bot_soyus_4_iz = new Texture(nomer_bot_souz_4+"_white.png");
            bot_soyus_5_iz = new Texture(nomer_bot_souz_5+"_white.png");
            bot_soyus_6_iz = new Texture(nomer_bot_souz_6+"_white.png");

            if (n_playera_ch == 1){
                player_img = new Texture("1_vr_white.png");
                kto_player = 1;

            }
            else {
                player_img = new Texture(n_playera+"_white.png");
            }

            bot_vrag_1_iz = new Texture(nomer_bot_vrag_1+"_blue.png");
            bot_vrag_2_iz = new Texture(nomer_bot_vrag_2+"_blue.png");
            bot_vrag_3_iz = new Texture(nomer_bot_vrag_3+"_blue.png");
            bot_vrag_4_iz = new Texture(nomer_bot_vrag_4+"_blue.png");
            bot_vrag_5_iz = new Texture(nomer_bot_vrag_5+"_blue.png");
            bot_vrag_6_iz = new Texture(nomer_bot_vrag_6+"_blue.png");
            bot_vrag_vr_iz = new Texture(nomer_bot_vrag_vr+"_vr_blue.png");
            bot_soyus_vr_iz = new Texture("1" + "_vr_white.png");

        }
        else {
            // text souz

            bot_soyus_1_iz = new Texture(nomer_bot_souz_1+"_blue.png");
            bot_soyus_2_iz = new Texture(nomer_bot_souz_2+"_blue.png");
            bot_soyus_3_iz = new Texture(nomer_bot_souz_3+"_blue.png");
            bot_soyus_4_iz = new Texture(nomer_bot_souz_4+"_blue.png");
            bot_soyus_5_iz = new Texture(nomer_bot_souz_5+"_blue.png");
            bot_soyus_6_iz = new Texture(nomer_bot_souz_6+"_blue.png");


            if (n_playera_ch == 1){
                player_img = new Texture("1_vr_blue.png");
                kto_player = 1;
            }
            else {
                player_img = new Texture(n_playera+"_blue.png");

            }

            bot_vrag_1_iz = new Texture(nomer_bot_vrag_1+"_white.png");
            bot_vrag_2_iz = new Texture(nomer_bot_vrag_2+"_white.png");
            bot_vrag_3_iz = new Texture(nomer_bot_vrag_3+"_white.png");
            bot_vrag_4_iz = new Texture(nomer_bot_vrag_4+"_white.png");
            bot_vrag_5_iz = new Texture(nomer_bot_vrag_5+"_white.png");
            bot_vrag_6_iz = new Texture(nomer_bot_vrag_6+"_white.png");
            bot_vrag_vr_iz = new Texture(nomer_bot_vrag_vr+"_vr_white.png");
            bot_soyus_vr_iz = new Texture("1" + "_vr_blue.png");

        }

        if (kto_player == 1){
            player.x =  SCR_WIDTH/2;
            player.y = SCR_HEIGHT/2;
        }
        bot_soyus_6.x = 8 * SCR_WIDTH/9f + 70;
        bot_soyus_6.y = SCR_HEIGHT/2;


        if (player.y <= SCR_HEIGHT/2){
            camera.position.set(SCR_WIDTH/2, SCR_HEIGHT/2, 0);
        }
        else if(player.y >= WORLD_HEIHGT- SCR_HEIGHT/2){
            camera.position.set(SCR_WIDTH/2, WORLD_HEIHGT- SCR_HEIGHT/2, 0);
        }
        else {
            camera.position.set(SCR_WIDTH/2, player.y, 0);
        }
        bot_soyus_1.isp = 0;
        bot_soyus_2.isp = 0;
        bot_soyus_3.isp = 0;
        bot_soyus_4.isp = 0;
        bot_soyus_5.isp = 0;
        bot_soyus_6.isp = 0;
        bot_soyus_vr.isp = 0;

        bot_vrag_1.isp = 0;
        bot_vrag_2.isp = 0;
        bot_vrag_3.isp = 0;
        bot_vrag_4.isp = 0;
        bot_vrag_5.isp = 0;
        bot_vrag_6.isp = 0;
        bot_vrag_vr.isp = 0;

        camera.update();
        // время создания всего
        Timer_Start_igri  = TimeUtils.millis();
        logika_igri.chto_za_sab = 0;

    }

    @Override
    public void render(float delta) {
        Go_mus_game();
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
        // otrisovka botov
        // souz
        batch.draw(bot_soyus_1_iz, bot_soyus_1.scrX(), bot_soyus_1.scrY(), bot_soyus_1.width, bot_soyus_1.height);
        batch.draw(bot_soyus_2_iz, bot_soyus_2.scrX(), bot_soyus_2.scrY(), bot_soyus_2.width, bot_soyus_2.height);
        batch.draw(bot_soyus_3_iz, bot_soyus_3.scrX(), bot_soyus_3.scrY(), bot_soyus_3.width, bot_soyus_3.height);
        batch.draw(bot_soyus_4_iz, bot_soyus_4.scrX(), bot_soyus_4.scrY(), bot_soyus_4.width, bot_soyus_4.height);
        batch.draw(bot_soyus_5_iz, bot_soyus_5.scrX(), bot_soyus_5.scrY(), bot_soyus_5.width, bot_soyus_5.height);


        // vrag
        batch.draw(bot_vrag_1_iz, bot_vrag_1.scrX(), bot_vrag_1.scrY(), bot_vrag_1.width, bot_vrag_1.height);
        batch.draw(bot_vrag_2_iz, bot_vrag_2.scrX(), bot_vrag_2.scrY(), bot_vrag_2.width, bot_vrag_2.height);
        batch.draw(bot_vrag_3_iz, bot_vrag_3.scrX(), bot_vrag_3.scrY(), bot_vrag_3.width, bot_vrag_3.height);
        batch.draw(bot_vrag_4_iz, bot_vrag_4.scrX(), bot_vrag_4.scrY(), bot_vrag_4.width, bot_vrag_4.height);
        batch.draw(bot_vrag_5_iz, bot_vrag_5.scrX(), bot_vrag_5.scrY(), bot_vrag_5.width, bot_vrag_5.height);
        batch.draw(bot_vrag_6_iz, bot_vrag_6.scrX(), bot_vrag_6.scrY(), bot_vrag_6.width, bot_vrag_6.height);
        batch.draw(bot_vrag_vr_iz, bot_vrag_vr.scrX(), bot_vrag_vr.scrY(), bot_vrag_vr.width, bot_vrag_vr.height);

        batch.draw(obvodka, player.scrX()-10, player.scrY()-10, player.width+20, player.height+20);
        batch.draw(player_img, player.scrX(), player.scrY(), player.width, player.height);


        batch.draw(imgJostik, jostik.scrX(), jostik.scrY(camera.position.y-SCR_HEIGHT/4), jostik.width, jostik.height);
        batch.draw(imgUdar, udar_kn.scrX(), udar_kn.scrY(camera.position.y-SCR_HEIGHT/4), udar_kn.width, udar_kn.height);
        batch.draw(imgSprint, sprint_kn.scrX(), sprint_kn.scrY(camera.position.y + 150), sprint_kn.width, sprint_kn.height);
        if (nazat_jostik_dviz == 1) batch.draw(jos_dviz_krug, touch.x-25, touch.y-25, 50, 50);
        if (nazat_jostik_udara == 1) batch.draw(jos_udr_krug, touch.x-25, touch.y-25, 50, 50);





        batch.draw(krestik_iz, krestik.scrX(), krestik.scrY(camera.position.y+SCR_HEIGHT/2.4f), krestik.width, krestik.height);
        batch.draw(igrovoi_myach_iz, igrovoi_myach.x, igrovoi_myach.y, igrovoi_myach.width, igrovoi_myach.height);


        if ((igrovoi_myach.u_kogo == 4 ) && logika_igri.chto_za_sab != 4 && logika_igri.chto_za_sab != 5 && logika_igri.isp_bil_zabit_vrag_vor == 0) {
            logika_igri.chto_za_sab = igrovoi_myach.u_kogo;
            igrovoi_myach.move(logika_igri.chto_za_sab);
            Timer_Start_posle_gola = TimeUtils.millis();
        }
        else if ((igrovoi_myach.u_kogo == 5 ) && logika_igri.chto_za_sab != 4 && logika_igri.chto_za_sab != 5 && logika_igri.isp_bil_zabit_souz_vor == 0) {
            logika_igri.chto_za_sab = igrovoi_myach.u_kogo;
            igrovoi_myach.move(logika_igri.chto_za_sab);
            Timer_Start_posle_gola = TimeUtils.millis();
        }
        else  {
            igrovoi_myach.move(0);
        }


            // SOBITIAAAAAAAAAAAAA
        if (logika_igri.chto_za_sab == 0){
            player.nx = 0;
            player.ny = 0;
            bot_soyus_1.stop();
            bot_soyus_2.stop();
            bot_soyus_3.stop();
            bot_soyus_4.stop();
            bot_soyus_5.stop();
            bot_soyus_6.stop();
            bot_vrag_1.stop();
            bot_vrag_2.stop();
            bot_vrag_3.stop();
            bot_vrag_4.stop();
            bot_vrag_5.stop();
            bot_vrag_6.stop();
            batch.draw(dlya_otcheta, camera.position.x-SCR_WIDTH/4, camera.position.y-250, SCR_WIDTH/2, 500);
            font_5s.draw(batch, Obratniy_otchet(TimeUtils.millis() - Timer_Start_igri), camera.position.x-25, camera.position.y);
            font.draw(batch, "Приготовься к игре!", camera.position.x-260, camera.position.y + 150);
            int vrem = (int) (TimeUtils.millis() - Timer_Start_igri);
            if (vrem > 5500){
                logika_igri.chto_za_sab = 1;
                Timer_Start_perioda = TimeUtils.millis();
                Timer_Start_matcha = TimeUtils.millis();
                svistok = Gdx.audio.newSound(Gdx.files.internal("svistok_gromche.mp3"));
                svistok.setVolume(svistok.play(), 0.8f);

            }
        }
        if (logika_igri.chto_za_sab == 1){
            // start per
            //souz
            bot_soyus_1.start_per(1);
            bot_soyus_2.start_per(1);
            bot_soyus_3.start_per(1);
            bot_soyus_4.start_per(1);
            bot_soyus_5.start_per(1);
            //vragi
            bot_vrag_1.start_per(0);
            bot_vrag_2.start_per(0);
            bot_vrag_3.start_per(0);
            bot_vrag_4.start_per(0);
            bot_vrag_5.start_per(0);
            if (kto_player == 1){
                logika_igri.kto_zaber_myach();
                //System.out.println(logika_igri.kto_zab_myach + "tut");
                if (logika_igri.kto_zab_myach == 1){
                    bot_soyus_6.start_per_za_myach(1);
                    bot_vrag_6.start_per(0);
                }
                else {
                    bot_vrag_6.start_per_za_myach(0);
                    bot_soyus_6.start_per(1);
                }
            }
            else {
                bot_vrag_6.start_per_za_myach(0); // pliv_za myach
            }
        }
        if (logika_igri.chto_za_sab != 0){
            // Отображение времени
            bot_soyus_vr.isp = 0;
            bot_vrag_vr.isp = 0;


            String goal_souz = new String(readFile("goal_souz.txt"));
            String goal_vrag = new String(readFile("goal_vrag.txt"));


            font.draw(batch, Taim_played_vremya(TimeUtils.millis() - Timer_Start_matcha) + " Счёт: " + goal_souz + " : "+ goal_vrag, 100, camera.position.y+SCR_HEIGHT/2-15);
            font2.draw(batch, Vremya_ataki(TimeUtils.millis() - Timer_Start_perioda), 100, camera.position.y+SCR_HEIGHT/2-85);
            if (kto_player == 1){
                batch.draw(bot_soyus_6_iz, bot_soyus_6.scrX(), bot_soyus_6.scrY(), bot_soyus_6.width, bot_soyus_6.height);
            }
            else {
                bot_soyus_vr.move();
                bot_soyus_vr.dviz_vrat(1);
                batch.draw(bot_soyus_vr_iz, bot_soyus_vr.scrX(), bot_soyus_vr.scrY(), bot_soyus_vr.width, bot_soyus_vr.height);
            }
            bot_vrag_vr.move();
            bot_vrag_vr.dviz_vrat(0);
        }

        if (logika_igri.chto_za_sab == 4){
            if (logika_igri.isp_bil_zabit_vrag_vor == 0){
                player.nx = 0;
                player.ny = 0;
                int vrem2 = (int) (TimeUtils.millis() - Timer_Start_posle_gola);
                if (vrem2 > 4000){

                    bot_soyus_1.isp = 0;
                    bot_soyus_2.isp = 0;
                    bot_soyus_3.isp = 0;
                    bot_soyus_4.isp = 0;
                    bot_soyus_5.isp = 0;
                    bot_soyus_6.isp = 0;
                    bot_soyus_vr.isp = 0;

                    bot_vrag_1.isp = 0;
                    bot_vrag_2.isp = 0;
                    bot_vrag_3.isp = 0;
                    bot_vrag_4.isp = 0;
                    bot_vrag_5.isp = 0;
                    bot_vrag_6.isp = 0;
                    bot_vrag_vr.isp = 0;

                    String goal_souz = new String(readFile("goal_souz.txt"));
                    int goal_souz_ch = Integer.parseInt(goal_souz);
                    overwriteFile("goal_souz.txt", "" + (goal_souz_ch + 1));

                    logika_igri.chto_za_sab = 9;
                    logika_igri.isp_bil_zabit_vrag_vor = 1;
                }
                else {
                    batch.draw(dlya_otcheta_gol_in_vrag_vor, camera.position.x-SCR_WIDTH/4, camera.position.y-250, SCR_WIDTH/2, 500);
                    font.draw(batch, "ГОООООООООООООООЛ!", camera.position.x-260, camera.position.y+100);
                    batch.draw(igrovoi_myach_iz, camera.position.x-260, camera.position.y-200, 200, 200);
                    svistok = Gdx.audio.newSound(Gdx.files.internal("svistok_gromche.mp3"));
                }
                if (vrem2 <= 30){
                    svistok.setVolume(svistok.play(), 0.8f);
                }
            }

        }
        else if (logika_igri.chto_za_sab == 5){
            if (logika_igri.isp_bil_zabit_souz_vor == 0){
                player.nx = 0;
                player.ny = 0;
                int vrem2 = (int) (TimeUtils.millis() - Timer_Start_posle_gola);
                if (vrem2 > 4000){

                    bot_soyus_1.isp = 0;
                    bot_soyus_2.isp = 0;
                    bot_soyus_3.isp = 0;
                    bot_soyus_4.isp = 0;
                    bot_soyus_5.isp = 0;
                    bot_soyus_6.isp = 0;
                    bot_soyus_vr.isp = 0;

                    bot_vrag_1.isp = 0;
                    bot_vrag_2.isp = 0;
                    bot_vrag_3.isp = 0;
                    bot_vrag_4.isp = 0;
                    bot_vrag_5.isp = 0;
                    bot_vrag_6.isp = 0;
                    bot_vrag_vr.isp = 0;

                    String goal_vrag = new String(readFile("goal_vrag.txt"));
                    int goal_vrag_ch = Integer.parseInt(goal_vrag);
                    overwriteFile("goal_vrag.txt", "" + (goal_vrag_ch + 1));

                    logika_igri.chto_za_sab = 10;
                    logika_igri.isp_bil_zabit_souz_vor = 1;
                }
                else {
                    batch.draw(dlya_otcheta_gol_in_souz_vor, camera.position.x-SCR_WIDTH/4, camera.position.y-250, SCR_WIDTH/2, 500);
                    font.draw(batch, "Вам забили!", camera.position.x-130, camera.position.y+100);
                    batch.draw(igrovoi_myach_iz, camera.position.x-260, camera.position.y-200, 200, 200);
                    svistok = Gdx.audio.newSound(Gdx.files.internal("svistok_gromche.mp3"));
                }
                if (vrem2 <= 30){
                    svistok.setVolume(svistok.play(), 0.8f);
                }
            }
        }
        if (logika_igri.chto_za_sab == 9){
            igrovoi_myach.x = SCR_WIDTH/2 - igrovoi_myach.width/2;
            igrovoi_myach.y = WORLD_HEIHGT/2 - igrovoi_myach.height/2;


            // rasstanovka igrokov

            player.x = SCR_WIDTH/2;
            player.y = WORLD_HEIHGT/2 - player.height - 200;

            float pn = 9f;
            bot_soyus_1.x = 1*SCR_WIDTH/pn-70;
            bot_soyus_1.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_2.x = 2*SCR_WIDTH/pn-70;
            bot_soyus_2.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_3.x = 3*SCR_WIDTH/pn-70;
            bot_soyus_3.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_4.x = 6*SCR_WIDTH/pn+70;
            bot_soyus_4.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_5.x = 7*SCR_WIDTH/pn+70;
            bot_soyus_5.y = WORLD_HEIHGT/2 - 200;
            if (kto_player == 1){
                bot_soyus_6.x = 8 * SCR_WIDTH/9f + 70;
                bot_soyus_6.y = WORLD_HEIHGT/2 - 200;
            }
            //vragi
            bot_vrag_1.x = 1*SCR_WIDTH/pn-60;
            bot_vrag_1.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_2.x = 2*SCR_WIDTH/pn-60;
            bot_vrag_2.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_3.x = 3*SCR_WIDTH/pn-60;
            bot_vrag_3.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_4.x = 6*SCR_WIDTH/pn+60;
            bot_vrag_4.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_5.x = 7*SCR_WIDTH/pn+60;
            bot_vrag_5.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_6.x = (8 * SCR_WIDTH/9f) + 50;
            bot_vrag_6.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);



            logika_igri.chto_za_sab = 3;

        }
        if (logika_igri.chto_za_sab == 10){
            igrovoi_myach.x = SCR_WIDTH/2 - igrovoi_myach.width/2;
            igrovoi_myach.y = WORLD_HEIHGT/2 - igrovoi_myach.height/2;



            // rasstanovka igrokov

            player.x = SCR_WIDTH/2;
            player.y = WORLD_HEIHGT/2 - player.height - 200;

            float pn = 9f;
            bot_soyus_1.x = 1*SCR_WIDTH/pn-70;
            bot_soyus_1.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_2.x = 2*SCR_WIDTH/pn-70;
            bot_soyus_2.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_3.x = 3*SCR_WIDTH/pn-70;
            bot_soyus_3.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_4.x = 6*SCR_WIDTH/pn+70;
            bot_soyus_4.y = WORLD_HEIHGT/2 - 200;
            bot_soyus_5.x = 7*SCR_WIDTH/pn+70;
            bot_soyus_5.y = WORLD_HEIHGT/2 - 200;
            if (kto_player == 1){
                bot_soyus_6.x = 8 * SCR_WIDTH/9f + 70;
                bot_soyus_6.y = WORLD_HEIHGT/2 - 200;
            }
            //vragi
            bot_vrag_1.x = 1*SCR_WIDTH/pn-60;
            bot_vrag_1.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_2.x = 2*SCR_WIDTH/pn-60;
            bot_vrag_2.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_3.x = 3*SCR_WIDTH/pn-60;
            bot_vrag_3.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_4.x = 6*SCR_WIDTH/pn+60;
            bot_vrag_4.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_5.x = 7*SCR_WIDTH/pn+60;
            bot_vrag_5.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);
            bot_vrag_6.x = (8 * SCR_WIDTH/9f) + 50;
            bot_vrag_6.y = WORLD_HEIHGT/2 + (200 - bot_soyus_1.height);


            logika_igri.chto_za_sab = 2;
        }
        if (logika_igri.chto_za_sab == 2){
            bot_vrag_1.peremesenie_na_poz(1, 0, 1);
            bot_vrag_2.peremesenie_na_poz(2, 0, 1);
            bot_vrag_3.peremesenie_na_poz(3,0, 1);
            bot_vrag_4.peremesenie_na_poz(4, 0, 1);
            bot_vrag_5.peremesenie_na_poz(5, 0, 1);
            bot_vrag_6.peremesenie_na_poz(6, 0, 1);


            bot_soyus_1.peremesenie_na_poz(bot_soyus_1.pos, 1, 0);
            bot_soyus_2.peremesenie_na_poz(bot_soyus_2.pos, 1, 0);
            bot_soyus_3.peremesenie_na_poz(bot_soyus_3.pos, 1, 0);
            bot_soyus_4.peremesenie_na_poz(bot_soyus_4.pos, 1, 0);
            bot_soyus_5.peremesenie_na_poz(bot_soyus_5.pos, 1, 0);
            if (kto_player == 1){bot_soyus_6.peremesenie_na_poz(bot_soyus_6.pos, 1, 0);}
        }
        if (logika_igri.chto_za_sab == 3){

            bot_vrag_1.peremesenie_na_poz(1, 0, 0);
            bot_vrag_2.peremesenie_na_poz(2, 0, 0);
            bot_vrag_3.peremesenie_na_poz(3,0, 0);
            bot_vrag_4.peremesenie_na_poz(4, 0, 0);
            bot_vrag_5.peremesenie_na_poz(5, 0, 0);
            bot_vrag_6.peremesenie_na_poz(6, 0, 0);


            bot_soyus_1.peremesenie_na_poz(bot_soyus_1.pos, 1, 1);
            bot_soyus_2.peremesenie_na_poz(bot_soyus_2.pos, 1, 1);
            bot_soyus_3.peremesenie_na_poz(bot_soyus_3.pos, 1, 1);
            bot_soyus_4.peremesenie_na_poz(bot_soyus_4.pos, 1, 1);
            bot_soyus_5.peremesenie_na_poz(bot_soyus_5.pos, 1, 1);
            if (kto_player == 1){bot_soyus_6.peremesenie_na_poz(bot_soyus_6.pos, 1, 1);}



        }



        if ((igrovoi_myach.y >= bot_soyus_vr.scrY() - bot_soyus_vr.height/2 - 5  && igrovoi_myach.y <= bot_soyus_vr.scrY() + bot_soyus_vr.height/2 + 5) && (bot_soyus_vr.scrX() - bot_soyus_vr.width/2 - 5 <= igrovoi_myach.scrX() - igrovoi_myach.width/2  && igrovoi_myach.scrX() + igrovoi_myach.width/2 <= bot_soyus_vr.scrX() + bot_soyus_vr.width/2 + 5)){
            igrovoi_myach.vx = -igrovoi_myach.vx;
            igrovoi_myach.vy = -igrovoi_myach.vy;

        }
        if ((igrovoi_myach.y >= bot_vrag_vr.scrY() - bot_vrag_vr.height/2 - 5  && igrovoi_myach.y <= bot_vrag_vr.scrY() + bot_vrag_vr.height/2 + 5) && (bot_vrag_vr.scrX() - bot_vrag_vr.width/2 - 5 <= igrovoi_myach.scrX() - igrovoi_myach.width/2  && igrovoi_myach.scrX() + igrovoi_myach.width/2 <= bot_vrag_vr.scrX() + bot_vrag_vr.width/2 + 5)){
            igrovoi_myach.vx = -igrovoi_myach.vx;
            igrovoi_myach.vy = -igrovoi_myach.vy;

        }
        // sobitia



        // souz
        /*
        bot_soyus_1.peremesenie_na_poz(poz_1_souz, 1);
        bot_soyus_2.peremesenie_na_poz(poz_2_souz, 1);
        bot_soyus_3.peremesenie_na_poz(poz_3_souz,1);
        bot_soyus_4.peremesenie_na_poz(poz_4_souz, 1);
        bot_soyus_5.peremesenie_na_poz(poz_5_souz, 1);
        String n_pos_playera = new String(readFile("poz_igr.txt"));
        int n_pos_playera_ch = Integer.parseInt(n_pos_playera);
        if (n_pos_playera_ch == 0) {
            bot_soyus_6.peremesenie_na_poz(poz_6_souz, 1);
        }





        //vragi
        bot_vrag_1.peremesenie_na_poz(1, 0);
        bot_vrag_2.peremesenie_na_poz(2, 0);
        bot_vrag_3.peremesenie_na_poz(3,0);
        bot_vrag_4.peremesenie_na_poz(4, 0);
        bot_vrag_5.peremesenie_na_poz(5, 0);
        bot_vrag_6.peremesenie_na_poz(6, 0);

         */


        if ((((player.scrX() + r_kas_myach) >= igrovoi_myach.scrX()) && ((player.scrX() - r_kas_myach) <= igrovoi_myach.scrX())) && (((player.scrY() + r_kas_myach) >= igrovoi_myach.scrY()) && ((player.scrY() - r_kas_myach) <= igrovoi_myach.scrY()))) {
            igrovoi_myach.u_igroka(player.scrX(), player.scrY(), true, igrovoi_myach.vx, igrovoi_myach.vy);

            bot_soyus_1.isp = 0;
            bot_soyus_2.isp = 0;
            bot_soyus_3.isp = 0;
            bot_soyus_4.isp = 0;
            bot_soyus_5.isp = 0;
            bot_soyus_6.isp = 0;
            bot_soyus_vr.isp = 0;

            bot_vrag_1.isp = 0;
            bot_vrag_2.isp = 0;
            bot_vrag_3.isp = 0;
            bot_vrag_4.isp = 0;
            bot_vrag_5.isp = 0;
            bot_vrag_6.isp = 0;
            bot_vrag_vr.isp = 0;

            logika_igri.chto_za_sab = 2;

            //logika_igri.isp_bil_zabit_souz_vor = 0;
            //logika_igri.isp_bil_zabit_vrag_vor = 0;
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


        //System.out.println(logika_igri.chto_za_sab);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game_sound.stop();
    }

    @Override
    public void resume() {
        game_sound.stop();
    }

    @Override
    public void hide() {
        game_sound.stop();
    }

    @Override
    public void dispose() {
        svistok.dispose();
        batch.dispose();
        glav_menu_back.dispose();
        font.dispose();
        imgJostik.dispose();
        imgUdar.dispose();
        igrovoi_myach_iz.dispose();
        bot_soyus_1_iz.dispose();
        bot_soyus_2_iz.dispose();
        bot_soyus_3_iz.dispose();
        bot_soyus_4_iz.dispose();
        bot_soyus_5_iz.dispose();
        bot_soyus_6_iz.dispose();
        bot_soyus_vr_iz.dispose();
        bot_vrag_1_iz.dispose();
        bot_vrag_2_iz.dispose();
        bot_vrag_3_iz.dispose();
        bot_vrag_4_iz.dispose();
        bot_vrag_5_iz.dispose();
        bot_vrag_6_iz.dispose();
        bot_vrag_vr_iz.dispose();
        font2.dispose();
        game_sound.dispose();
        obvodka.dispose();
        dlya_otcheta.dispose();
        dlya_otcheta_gol_in_souz_vor.dispose();
        dlya_otcheta_gol_in_vrag_vor.dispose();
        jos_udr_krug.dispose();
        jos_dviz_krug.dispose();
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
                player.touchJoystick(touch, jostik);
            }
            if(udar_kn.isTouchInside(touch)) {
                igrovoi_myach.touchJoystick_udar(touch, udar_kn);
            }
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
                    logika_igri.isp_bil_zabit_souz_vor = 0;
                    logika_igri.isp_bil_zabit_vrag_vor = 0;
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
                nazat_jostik_dviz = 1;
            }
            else {nazat_jostik_dviz = 0;}
            if(udar_kn.isTouchInside(touch)) {
                igrovoi_myach.touchJoystick_udar(touch, udar_kn);
                nazat_jostik_udara = 1;
            }
            else {nazat_jostik_udara = 0;}
                //player.touchJoystick(touch, jostik);
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
    private String Taim_played_vremya(long Timer){
        long msec = 10 - (Timer % 1000) / 100;
        long sec = 59 - Timer/1000%60;
        long min = 7 - Timer/1000/60%60;
        long hour = Timer/1000/60/60%24;
        return min / 10 + min % 10 + ":" +sec / 10 + sec % 10; // + ":" + msec
    }

    private String Vremya_ataki(long Timer){
        long msec = 10 - (Timer % 1000) / 100;
        long sec = 29 - Timer/1000%60;
        return sec + ":" + msec;
    }
    private String Obratniy_otchet(long Timer){
        long sec = 5 - Timer/1000%60;
        return sec + "";
    }
    private String Obratniy_otchet2(long Timer){
        long sec = 5 - Timer/1000%60;
        return sec + "";
    }
    private void Go_mus_game(){
        game_sound.play();
    }
}
