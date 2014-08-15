package com.francispro.dota2counterpick.ClasesMenuItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.TextView;

import com.francispro.dota2counterpick.ClasesDataBase.CopyAdapter;
import com.francispro.dota2counterpick.Connect.httpHandler;
import com.francispro.dota2counterpick.Main;
import com.francispro.dota2counterpick.R;

import java.lang.reflect.Field;


public class BestWinRate extends Activity {


    private static String TAG = "--BestWinRate : ";
    public static String hosting = "http://www.dota2info.hol.es/dota2/";
    public static float aux = 0;

    String[] Arreglo_name_info = {"Earthshaker","Sven","Tiny","Kunkka","Beastmaster","Dragon-Knight","Clockwerk","Omniknight","Huskar","Alchemist","Brewmaster","Treant-Protector","Io","Centaur-Warrunner","Timbersaw","Bristleback",
            "Tusk","Elder-Titan","Legion-Commander","Earth-Spirit","Axe","Pudge","Sand-King","Slardar","Tidehunter","Wraith-King","Lifestealer","Night-Stalker","Doom","Spirit-Breaker","Lycan","Chaos-Knight","Undying","Magnus","Abaddon","Phoenix",
            "Anti-Mage","Drow-Ranger","Juggernaut","Mirana","Morphling","Phantom-Lancer","Vengeful-Spirit","Riki","Sniper","Templar-Assassin","Luna","Bounty-Hunter","Ursa","Gyrocopter","Lone-Druid","Naga-Siren","Troll-Warlord","Ember-Spirit",
            "Bloodseeker","Shadow-Fiend","Razor","Venomancer","Faceless-Void","Phantom-Assassin","Viper","Clinkz","Broodmother","Weaver","Spectre","Meepo","Nyx-Assassin","Slark","Medusa","Terrorblade","Crystal-Maiden","Puck","Storm-Spirit",
            "Windranger","Zeus","Lina","Shadow-Shaman","Tinker","Natures-Prophet","Enchantress","Jakiro","Chen","Silencer","Ogre-Magi","Rubick","Disruptor","Keeper-of-the-Light","Skywrath-Mage","Bane","Lich","Lion","Witch-Doctor","Enigma","Necrophos",
            "Warlock","Queen-of-Pain","Death-Prophet","Pugna,Dazzle","Leshrac","Dark-Seer","Batrider","Ancient-Apparition","Invoker","Outworld-Devourer","Shadow-Demon","Visage"};


    String[] Arreglo_nombre={"Earthshaker","Sven","Tiny","Kunkka","Beastwmaster","Dragon Knight","Clockwerk","Omniknight","Huskar","Alchemist","Brewmaster","Treant Protector","Io","Centaur Warrunner","Timbersaw","Bristleback","Tusk",
            "Elder Titan","Legion Commander","Earth Spirit","Axe","Pudge","Sand King","Slardar","Tidehunter","Wraith King","Lifestealer","Night Stalker","Doom","Spirit Breaker","Lycan","Chaos Knight","Undying","Magnus","Abaddon","Phoenix",
            "Anti-Mage","Drow Ranger","Juggernaut","Mirana","Morphling","Phantom Lancer","Vengeful Spirit","Riki","Sniper","Templar Assassin","Luna","Bounty Hunter","Ursa","Gyrocopter","Lone Druid","Naga Siren","Troll Warlord","Ember Spirit",
            "Blood Seeker","Shadow Fiend","Razor","Venomancer","Faceless Void","Phantom Assassin","Viper","Clinkz","BroodMother","Weaver","Spectre","Meepo","Nyx Assassin","Slark","Medusa","Terrorblade","Crystal Maiden","Puck","Storm Spirit","Windranger",
            "Zeus","Lina","Shadow Shaman","Tinker","Nature's Prophet","Enchantress","Jakiro","Chen","Silencer","Ogre Magi","Rubick","Disruptor","Keeper of the Light","Skywrath Mage","Bane","Lich","Lion","Witch Doctor","Enigma","Necrophos",
            "Warlock","Queen of Pain","Death Prophet","Pugna","Dazzle","Leshrac","Dark Seer","Batrider","Ancient Apparition","Invoker","Outworld Devourer","Shadow Demon","Visage"};

    public static float[] Arreglo_winrate = new float[107];


    public TextView tv1h,tv1r,tv2h,tv2r,tv3h,tv3r,tv4h,tv4r,tv5h,tv5r,tv6h,tv6r;

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
        tv1r = (TextView)findViewById(R.id.tv_item1_rate);

        tv2h = (TextView)findViewById(R.id.tv_item2_hero);
        tv2r = (TextView)findViewById(R.id.tv_item2_rate);

        tv3h = (TextView)findViewById(R.id.tv_item3_hero);
        tv3r = (TextView)findViewById(R.id.tv_item3_rate);

        tv4h = (TextView)findViewById(R.id.tv_item4_hero);
        tv4r = (TextView)findViewById(R.id.tv_item4_rate);

        tv5h = (TextView)findViewById(R.id.tv_item5_hero);
        tv5r = (TextView)findViewById(R.id.tv_item5_rate);

        tv6h = (TextView)findViewById(R.id.tv_item6_hero);
        tv6r = (TextView)findViewById(R.id.tv_item6_rate);


        CopyAdapter mDbHelper = new CopyAdapter(BestWinRate.this);
        mDbHelper.openDB();


        for(int i=0;i<107;i++) {

            mDbHelper.retriveNameWinRate(i);
            System.out.println(TAG+" Valor aux : "+aux);
            Arreglo_winrate[i] = aux;
            //System.out.println(TAG+" Valor Arreglo_winrate : "+Arreglo_winrate[i]);
        }

        mDbHelper.closeDB();


        float mayor = 0, auxNum;
        int j=0;
        String auxName=null, auxInf=null;

        for (int f = 0; f < Arreglo_winrate.length - 1; f++) {
            j=f;
            auxNum = Arreglo_winrate[f];
            auxName = Arreglo_nombre[f];
            auxInf = Arreglo_name_info[f];

            while (j>0 && auxNum > Arreglo_winrate[j-1])
            {
                Arreglo_winrate[j] = Arreglo_winrate[j-1];
                Arreglo_nombre[j] = Arreglo_nombre[j-1];
                Arreglo_name_info[j] = Arreglo_name_info[j-1];
                j=j-1;
            }
            Arreglo_winrate[j] = auxNum;
            Arreglo_nombre[j] = auxName;
            Arreglo_name_info[j] = auxInf;

        }


        tv1h.setText(Arreglo_nombre[0]);
        tv2h.setText(Arreglo_nombre[1]);
        tv3h.setText(Arreglo_nombre[2]);
        tv4h.setText(Arreglo_nombre[3]);
        tv5h.setText(Arreglo_nombre[4]);
        tv6h.setText(Arreglo_nombre[5]);

        String[] cadena = new String[6];
        for(int x=0;x<6;x++) {
            cadena[x] = String.valueOf(Arreglo_winrate[x]);
        }

        tv1r.setText(cadena[0]+"%");
        tv2r.setText(cadena[1]+"%");
        tv3r.setText(cadena[2]+"%");
        tv4r.setText(cadena[3]+"%");
        tv5r.setText(cadena[4]+"%");
        tv6r.setText(cadena[5]+"%");






















    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), Main.class);
        startActivity(i);
        finish();
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
