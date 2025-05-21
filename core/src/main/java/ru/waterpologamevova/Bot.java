package ru.waterpologamevova;

import static ru.waterpologamevova.Main.SCR_HEIGHT;
import static ru.waterpologamevova.Main.SCR_WIDTH;
import static ru.waterpologamevova.Standart.WORLD_HEIHGT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector3;

public class Bot {
    public float x, y;
    public float width, height;
    public boolean side;
    float vx = 1;
    float vy = 0.25f;
    int pos; //0-6
    public float kon_x = 0, kon_y = 0;
    public int isp = 0;
    public float nuz_rast_x, nuz_rast_y;
    public int kto;
    public int sob = 1;   // 0 - start 1 - ataka_souz 2 - ataka_vrag 3 - gol 4 - end per 5 - end igri


    public Bot (float x,float y, int pos_bot) {
        width = height = 100;
        this.x = x;
        this.y = y;
        pos = pos_bot;
    }

    public float scrX(){
        return x-width/2;
    }

    public float scrY(){
        return y-height/2;
    }

    public void stop(){
        vx = 0;
        vy = 0;
    }
    public void dviz_vrat(int kto){
        if (x <= 450 || x >=800){
            vx = -vx;
        }
        if (kto == 1){
            if (y <= SCR_HEIGHT/2 -80 || y >= SCR_HEIGHT/2 - 10){
                vy = -vy;
            }
        }
        else {
            if (y <= WORLD_HEIHGT - SCR_HEIGHT/2 +10 || y >= WORLD_HEIHGT - SCR_HEIGHT/2 + 80){
                vy = -vy;
            }
        }


    }
    public void move() {
        outOfScreen();
        x += vx;
        y += vy;
    }
    private void outOfScreen(){
        if(x<width/2) {
            vx = 0;
            x = width/2;
        }
        if(x>SCR_WIDTH-width/2) {
            vx = 0;
            x = SCR_WIDTH-width/2;
        }
    }
    public void nuz_rast_do_poz(int poz, int zas_ili_net){
        float sl_v = 18f;
        if (zas_ili_net == 0){
            if (kto == 1){
                if (poz == 1){
                    kon_x = 230-width/2f;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+100;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }

                if (poz == 2){
                    kon_x = SCR_WIDTH/2-250;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }

                if (poz == 3){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height-150;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 4){
                    kon_x = SCR_WIDTH/2+125;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }


                if (poz == 5){
                    kon_x = SCR_WIDTH-300;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+100;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 6){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+100;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
            }
            if (kto == 0){
                if (poz == 1){
                    kon_x = SCR_WIDTH-300;
                    kon_y = SCR_HEIGHT/2;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;

                }
                if (poz == 2){
                    kon_x = SCR_WIDTH/2+125;
                    kon_y = SCR_HEIGHT-150;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;

                }
                if (poz == 3){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = SCR_HEIGHT-height/2;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 4){
                    kon_x = SCR_WIDTH/2-250;
                    kon_y = SCR_HEIGHT-150;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 5){
                    kon_x = 230-width/2f;
                    kon_y = SCR_HEIGHT/2;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 6){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = SCR_HEIGHT/2;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
            }
        }
        else if (zas_ili_net == 1){
            if (kto == 0){
                if (poz == 1){
                    kon_x = 230-width/2f;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+100+height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }

                if (poz == 2){
                    kon_x = SCR_WIDTH/2-250;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }

                if (poz == 3){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height-150+height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 4){
                    kon_x = SCR_WIDTH/2+125;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }


                if (poz == 5){
                    kon_x = SCR_WIDTH-300;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+100+height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 6){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = WORLD_HEIHGT -SCR_HEIGHT+height+100+height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
            }
            if (kto == 1){
                if (poz == 1){
                    kon_x = SCR_WIDTH-320-width/2-30;
                    kon_y = SCR_HEIGHT/2-height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;

                }
                if (poz == 2){
                    kon_x = SCR_WIDTH/2+125-width/2-30;
                    kon_y = SCR_HEIGHT-150-height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;

                }
                if (poz == 3){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = SCR_HEIGHT-height/2-height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 4){
                    kon_x = SCR_WIDTH/2-250+width/2-30;
                    kon_y = SCR_HEIGHT-150-height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 5){
                    kon_x = 230-width/2f+width/2+30;
                    kon_y = SCR_HEIGHT/2-height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
                if (poz == 6){
                    kon_x = SCR_WIDTH/2-width/2f;
                    kon_y = SCR_HEIGHT/2-height/3;
                    nuz_rast_x = kon_x-scrX();
                    nuz_rast_y = kon_y-scrY();
                    vx = nuz_rast_x/sl_v/60;
                    vy = nuz_rast_y/sl_v/60;
                    isp = 1;
                }
            }
        }

    }

    public void posle_gola(int poz, int hoo){
        if (hoo == 1){
            if (poz == 1){
                x = 230-width/2f;
                y = WORLD_HEIHGT/2 - 100;
            }

            if (poz == 2){
                x = SCR_WIDTH/2-250;
                y = WORLD_HEIHGT/2 - 100;
            }

            if (poz == 3){
                x = SCR_WIDTH/2-width/2f;
                y = WORLD_HEIHGT/2 - 100;
            }
            if (poz == 4){
                x = SCR_WIDTH/2+125;
                y = WORLD_HEIHGT/2 - 100;
            }


            if (poz == 5){
                x = SCR_WIDTH-300;
                y = WORLD_HEIHGT/2 - 100;
                isp = 1;
            }
            if (poz == 6){
                x = SCR_WIDTH/2-width/2f;
                y = WORLD_HEIHGT/2 - 300;
            }
        }
        if (hoo == 0){
            if (poz == 1){
                kon_x = SCR_WIDTH-300;
                kon_y = SCR_HEIGHT/2 + 20;

            }
            if (poz == 2){
                kon_x = SCR_WIDTH/2+125;
                kon_y = SCR_HEIGHT/2 + 20;

            }
            if (poz == 3){
                kon_x = SCR_WIDTH/2-width/2f;
                kon_y = SCR_HEIGHT/2 + 20;
            }
            if (poz == 4){
                kon_x = SCR_WIDTH/2-250;
                kon_y = SCR_HEIGHT/2 + 20;
            }
            if (poz == 5){
                kon_x = 230-width/2f;
                kon_y = SCR_HEIGHT/2 + 20;
            }
            if (poz == 6){
                kon_x = SCR_WIDTH/2-width/2f;
                kon_y = SCR_HEIGHT/2 + 20;
            }
        }

    }


    public void peremesenie_na_poz(int poz, int kto_b, int zas_or_no){ // kto = 1 - souz esli 0 - vrag
        kto = kto_b;
        if (kto == 1){
            if (poz == 1){
                if (isp == 0){
                    nuz_rast_do_poz(1, zas_or_no);
                }

            }
            if (poz == 2){
                if (isp == 0){
                    nuz_rast_do_poz(2, zas_or_no);
                }

            }
            if (poz == 3){
                if (isp == 0){
                    nuz_rast_do_poz(3, zas_or_no);
                }

            }
            if (poz == 4){
                if (isp == 0){
                    nuz_rast_do_poz(4, zas_or_no);
                }

            }
            if (poz == 5){
                if (isp == 0){
                    nuz_rast_do_poz(5, zas_or_no);
                }

            }
            if (poz == 6){
                if (isp == 0){
                    nuz_rast_do_poz(6, zas_or_no);
                }

            }

            if (kon_x - 5 <= scrX() && scrX() <= kon_x + 5){
                vx = 0;
                }
            if (kon_y - 5 <= scrY() && scrY() <= kon_y + 5){
                vy = 0;
                }
            x += vx;
            y += vy;
            }
        if (kto == 0) {
                if (poz == 1){
                    if (isp == 0){
                        nuz_rast_do_poz(1, zas_or_no);
                    }

                }
                if (poz == 2){
                    if (isp == 0){
                        nuz_rast_do_poz(2, zas_or_no);
                    }

                }
                if (poz == 3){
                    if (isp == 0){
                        nuz_rast_do_poz(3, zas_or_no);
                    }

                }
                if (poz == 4){
                    if (isp == 0){
                        nuz_rast_do_poz(4, zas_or_no);
                    }

                }
                if (poz == 5){
                    if (isp == 0){
                        nuz_rast_do_poz(5, zas_or_no);
                    }

                }
                if (poz == 6){
                    if (isp == 0){
                        nuz_rast_do_poz(6, zas_or_no);
                    }

                }

                if (kon_x - 5 <= scrX() && scrX() <= kon_x + 5){
                    vx = 0;
                }
                if (kon_y - 5 <= scrY() && scrY() <= kon_y + 5){
                    vy = 0;
                }
                x += vx;
                y += vy;
            }

    }
    public void start_per(int kto){
        if (kto == 1 && y + height >= WORLD_HEIHGT/2 - 50){
            vy = 0;
        }
        else if (kto == 0 && y - 150 <= WORLD_HEIHGT/2){
            vy = 0;
        }
        else {
            String sloz = new String(readFile("sloznost.txt"));
            int sloz_ch = Integer.parseInt(sloz);
            if (sloz_ch == 5){
                vy = 0.8f;
            }
            else if (sloz_ch == 4){
                vy = 1f;
            }
            else if (sloz_ch == 3){
                vy = 1.5f;
            }
            else if (sloz_ch == 2){
                vy = 1.7f;
            }
            else if (sloz_ch == 1){
                vy = 2f;
            }
            if (kto == 0){
                vy = vy * (-1);
            }
            y += vy;
        }



    }


    public void start_per_za_myach(int kto){
        if (kto == 1 && y + height >= WORLD_HEIHGT/2 - 50){
            vy = 0;
        }
        else if (kto == 0 && y - 150 <= WORLD_HEIHGT/2){
            vy = 0;
        }
        else {
            String sloz = new String(readFile("sloznost.txt"));
            int sloz_ch = Integer.parseInt(sloz);
            if (sloz_ch == 5){
                vy = 0.9f;
            }
            else if (sloz_ch == 4){
                vy = 1.1f;
            }
            else if (sloz_ch == 3){
                vy = 1.6f;
            }
            else if (sloz_ch == 2){
                vy = 1.8f;
            }
            else if (sloz_ch == 1){
                vy = 2.1f;
            }
            if (kto == 0){
                vy = vy * (-1);
            }
            y += vy;
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
}
