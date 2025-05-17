package ru.waterpologamevova;
import static com.badlogic.gdx.Gdx.input;
import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


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
    private Igrovoi_myach igrovoi_myach;
    private int r_kas_myach = 70;
    private boolean u_igroka_myach_sey;
    private Sprint_kn sprint_kn;
    private Udar_kn udar_kn;
    public static float WORLD_HEIHGT = SCR_HEIGHT*5;




    //конструктор
    public Standart(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font2;
        Gdx.input.setInputProcessor(new SpaceXProcessor());
        imgJostik = new Texture("jostik.png");
        player_img = new Texture("igrok.png");
        glav_menu_back = new Texture("pole.png");
        imgSprint = new Texture("sprint.png");
        imgUdar = new Texture("udar.png");
        igrovoi_myach_iz = new Texture("myach_igr.png");
        jostik = new Jostik();
        player = new Player(SCR_WIDTH/2, SCR_HEIGHT/2);
        igrovoi_myach = new Igrovoi_myach(SCR_WIDTH/2+200, SCR_HEIGHT/2);
        udar_kn = new Udar_kn();
        sprint_kn = new Sprint_kn();
    }

    @Override
    public void show() {
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
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.7f, 1f);


        // Отрисовка
        batch.setProjectionMatrix(camera.combined); // помогает подстроить под экран
        batch.begin(); // начинаем рисовать
        batch.draw(glav_menu_back, 0, 0, SCR_WIDTH, WORLD_HEIHGT); // фон в меню


        batch.draw(imgJostik, jostik.scrX(), jostik.scrY(camera.position.y-SCR_HEIGHT/4), jostik.width, jostik.height);
        batch.draw(imgUdar, udar_kn.scrX(), udar_kn.scrY(camera.position.y-SCR_HEIGHT/4), udar_kn.width, udar_kn.height);
        batch.draw(imgSprint, sprint_kn.scrX(), sprint_kn.scrY(camera.position.y + 150), sprint_kn.width, sprint_kn.height);
        batch.draw(player_img, player.scrX(), player.scrY(), player.width, player.height);
        batch.draw(igrovoi_myach_iz, igrovoi_myach.x, igrovoi_myach.y, igrovoi_myach.width, igrovoi_myach.height);



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


}
