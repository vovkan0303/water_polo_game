package ru.waterpologamevova;

import com.badlogic.gdx.math.MathUtils;

public class Logika_igri {
    public int chto_za_sab = 0;
    /*
    0 - otchet pered startom igri
    1 - кто ервый за мячом
    2 - атака у союз
    3 - атака у врага
    4 - гол в ворота союз
    5 - гол в ворота противника
    6 - закончился период
    7 - закончилась атака у союз
    8 - закончилась атака у врага
     */
    public int isp_start_perioda = 0;
    public int isp_kto_zaber_myach = 0;



    public int kto_zab_myach;
    public Logika_igri () {

    }

    public void start_perioda(){
        if (isp_start_perioda == 0){

        }
        isp_start_perioda = 1;
    }
    public void kto_zaber_myach(){
        if (isp_kto_zaber_myach == 0){
            kto_zab_myach = MathUtils.random(0, 1);
            //System.out.println(kto_zab_myach);
        }

        isp_kto_zaber_myach = 1;
    }
}
