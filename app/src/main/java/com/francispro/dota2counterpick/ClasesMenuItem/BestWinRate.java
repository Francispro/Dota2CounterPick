package com.francispro.dota2counterpick.ClasesMenuItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.francispro.dota2counterpick.ClasesDataBase.CopyAdapter;
import com.francispro.dota2counterpick.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BestWinRate extends Activity {

    //Descomente solo si tambien descomenta el comentario 1 para poder usarlo, ya que a estas variables se les asignas los valores desde la consulta a la bd
    //public static String nombre_Array_aux;
    //public static String name_info_Array_aux;

    String[] Arreglo_name_info = {"Earthshaker,Sven,Tiny,Kunkka,Beastmaster,Dragon-Knight,Clockwerk,Omniknight,Huskar,Alchemist,Brewmaster,Treant-Protector,Io,Centaur-Warrunner,Timbersaw,Bristleback," +
            "Tusk,Elder-Titan,Legion-Commander,Earth-Spirit,Axe,Pudge,Sand-King,Slardar,Tidehunter,Wraith-King,Lifestealer,Night-Stalker,Doom,Spirit-Breaker,Lycan,Chaos-Knight,Undying,Magnus,Abaddon,Phoenix," +
            "Anti-Mage,Drow-Ranger,Juggernaut,Mirana,Morphling,Phantom-Lancer,Vengeful-Spirit,Riki,Sniper,Templar-Assassin,Luna,Bounty-Hunter,Ursa,Gyrocopter,Lone-Druid,Naga-Siren,Troll-Warlord,Ember-Spirit," +
            "Bloodseeker,Shadow-Fiend,Razor,Venomancer,Faceless-Void,Phantom-Assassin,Viper,Clinkz,Broodmother,Weaver,Spectre,Meepo,Nyx-Assassin,Slark,Medusa,Terrorblade,Crystal-Maiden,Puck,Storm-Spirit," +
            "Windranger,Zeus,Lina,Shadow-Shaman,Tinker,Nature's-Prophet,Enchantress,Jakiro,Chen,Silencer,Ogre-Magi,Rubick,Disruptor,Keeper-of-the-Light,Skywrath-Mage,Bane,Lich,Lion,Witch-Doctor,Enigma,Necrophos," +
            "Warlock,Queen-of-Pain,Death-Prophet,Pugna,Dazzle,Leshrac,Dark-Seer,Batrider,Ancient-Apparition,Invoker,Outworld-Devourer,Shadow-Demon,Visage"};


    String[] Arreglo_nombre={"Earthshaker,Sven,Tiny,Kunkka,Beastwmaster,Dragon Knight,Clockwerk,Omniknight,Huskar,Alchemist,Brewmaster,Treant Protector,Io,Centaur Warrunner,Timbersaw,Bristleback,Tusk," +
            "Elder Titan,Legion Commander,Earth Spirit,Axe,Pudge,Sand King,Slardar,Tidehunter,Wraith King,Lifestealer,Night Stalker,Doom,Spirit Breaker,Lycan,Chaos Knight,Undying,Magnus,Abaddon,Phoenix," +
            "Anti-Mage,Drow Ranger,Juggernaut,Mirana,Morphling,Phantom Lancer,Vengeful Spirit,Riki,Sniper,Templar Assassin,Luna,Bounty Hunter,Ursa,Gyrocopter,Lone Druid,Naga Siren,Troll Warlord,Ember Spirit," +
            "Blood Seeker,Shadow Fiend,Razor,Venomancer,Faceless Void,Phantom Assassin,Viper,Clinkz,BroodMother,Weaver,Spectre,Meepo,Nyx Assassin,Slark,Medusa,Terrorblade,Crystal Maiden,Puck,Storm Spirit,Windranger," +
            "Zeus,Lina,Shadow Shaman,Tinker,Nature's Prophet,Enchantress,Jakiro,Chen,Silencer,Ogre Magi,Rubick,Disruptor,Keeper of the Light,Skywrath Mage,Bane,Lich,Lion,Witch Doctor,Enigma,Necrophos,Warlock,Queen of Pain," +
            "Death Prophet,Pugna,Dazzle,Leshrac,Dark Seer,Batrider,Ancient Apparition,Invoker,Outworld Devourer,Shadow Demon,Visage"};

    public static int[] Arreglo_winrate = null;

    public TextView tv1h,tv1m,tv2h,tv2m,tv3h,tv3m,tv4h,tv4m,tv5h,tv5m,tv6h,tv6m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_win_rate);

        //comando de ayuda para mostrar siempre el menu opciones
        getOverflowMenu();

        //crea una variable con un nuevo tipo de fuente, el font tiene que estar en la carpeta assets
        Typeface face_o = Typeface.createFromAsset(getAssets(), "OptimusPrinceps.ttf");
        Typeface face_r = Typeface.createFromAsset(getAssets(), "Realize.otf");

        TextView tituloActivity = (TextView)findViewById(R.id.tv_titulo_Activity);
        TextView tituloHero = (TextView)findViewById(R.id.tv_titulo_hero);
        TextView tituloMatch = (TextView)findViewById(R.id.tv_titulo_match);

        tituloActivity.setTextSize(26);
        tituloActivity.setTypeface(face_o);
        tituloHero.setTextSize(20);
        tituloHero.setTypeface(face_r);
        tituloMatch.setTextSize(20);
        tituloMatch.setTypeface(face_r);

        tv1h = (TextView)findViewById(R.id.tv_item1_hero);
        tv1m = (TextView)findViewById(R.id.tv_item1_match);

        tv2h = (TextView)findViewById(R.id.tv_item2_hero);
        tv2m = (TextView)findViewById(R.id.tv_item2_match);

        tv3h = (TextView)findViewById(R.id.tv_item3_hero);
        tv3m = (TextView)findViewById(R.id.tv_item3_match);

        tv4h = (TextView)findViewById(R.id.tv_item4_hero);
        tv4m = (TextView)findViewById(R.id.tv_item4_match);

        tv5h = (TextView)findViewById(R.id.tv_item5_hero);
        tv5m = (TextView)findViewById(R.id.tv_item5_match);

        tv6h = (TextView)findViewById(R.id.tv_item6_hero);
        tv6m = (TextView)findViewById(R.id.tv_item6_match);



        for( int i=0;i<107;i++){
            
        }



























        /***********Comentario1**************** Informacion para guardar algo a un archivo.txt
        codigo para ir a buscar el nombre y el name_inf a la base de datos y agregarlos a un arreglo, con el cual posteriormente creo un archivo.txt con todos la informacion para ahorar tiempo
        en las consultas ya que tengo la informacion en la misma clase y no requiero de la conexion a la base de datos

        CopyAdapter mDbHelper = new CopyAdapter(BestWinRate.this);
        //mDbHelper.retriveNameUrlcallBestWinRate();

        for( int x=0;x<107;x++)
        {
            mDbHelper.retriveNameUrlcallBestWinRate(x);
            Arreglo_nombre[x] = nombre_Array_aux;
            Arreglo_name_info[x] = name_info_Array_aux;
            System.out.println("-*_ "+Arreglo_nombre[x]+" - "+Arreglo_name_info[x]);
        }
        String extr = Environment.getExternalStorageDirectory().toString();
        File mFolder = new File(extr + "/MyApp");
        if (!mFolder.exists()) {
            mFolder.mkdir();
        }
        String strF = mFolder.getAbsolutePath();
        File mSubFolder = new File(strF + "/Dota2");

        if (!mSubFolder.exists()) {
            mSubFolder.mkdir();
        }
        String contenedor="start:";
        //me crea una cadena con todos los nombres concatenados para luegos pasarlos al archivo.txt
        for( int i=0; i<107;i++) {
            contenedor = contenedor+","+Arreglo_name_info[i];
        }

        try {
            File f = new File(strF + "/Dota2/", "Arreglo_name_info.txt");
            OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
            fout.write(contenedor);
            fout.close();
        } catch (IOException e) {
        }

        */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.BestWinRate:
                Intent i = new Intent(getApplicationContext(), BestWinRate.class);
                startActivity(i);
                finish();
                return true;
            case R.id.MostPlayed:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
