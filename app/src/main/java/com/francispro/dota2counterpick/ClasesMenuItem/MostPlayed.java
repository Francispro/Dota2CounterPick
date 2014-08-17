package com.francispro.dota2counterpick.ClasesMenuItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.francispro.dota2counterpick.ClasesDataBase.CopyAdapter;
import com.francispro.dota2counterpick.Main;
import com.francispro.dota2counterpick.R;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MostPlayed extends Activity {

    private static String TAG = "--BestWinRate : ";
    public static int auxMostPlayed = 0;

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

    public static int[] Arreglo_mostplayed = new int[107];


    public TextView tv1h,tv2h,tv3h,tv4h,tv5h,tv6h,tv7h,tv8h,tv9h,tv10h;
    public TextView tv1m,tv2m,tv3m,tv4m,tv5m,tv6m,tv7m,tv8m,tv9m,tv10m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_played);

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

        tv1h = (TextView)findViewById(R.id.tv_item1_heromp);
        tv1m = (TextView)findViewById(R.id.tv_item1_played);
        tv2h = (TextView)findViewById(R.id.tv_item2_heromp);
        tv2m = (TextView)findViewById(R.id.tv_item2_played);
        tv3h = (TextView)findViewById(R.id.tv_item3_heromp);
        tv3m = (TextView)findViewById(R.id.tv_item3_played);
        tv4h = (TextView)findViewById(R.id.tv_item4_heromp);
        tv4m = (TextView)findViewById(R.id.tv_item4_played);
        tv5h = (TextView)findViewById(R.id.tv_item5_heromp);
        tv5m = (TextView)findViewById(R.id.tv_item5_played);
        tv6h = (TextView)findViewById(R.id.tv_item6_heromp);
        tv6m = (TextView)findViewById(R.id.tv_item6_played);
        tv6h = (TextView)findViewById(R.id.tv_item6_heromp);
        tv6m = (TextView)findViewById(R.id.tv_item6_played);
        tv7h = (TextView)findViewById(R.id.tv_item7_heromp);
        tv7m = (TextView)findViewById(R.id.tv_item7_played);
        tv8h = (TextView)findViewById(R.id.tv_item8_heromp);
        tv8m = (TextView)findViewById(R.id.tv_item8_played);
        tv9h = (TextView)findViewById(R.id.tv_item9_heromp);
        tv9m = (TextView)findViewById(R.id.tv_item9_played);
        tv10h = (TextView)findViewById(R.id.tv_item10_heromp);
        tv10m = (TextView)findViewById(R.id.tv_item10_played);

        CopyAdapter mDbHelper = new CopyAdapter(MostPlayed.this);

        try {
            mDbHelper.createDatabase();
            System.out.println(TAG+"  2");
        } catch (SQLException e) {
            System.out.println(TAG+"  3");
            e.printStackTrace();

        }

        mDbHelper.openDB();

        for(int i=0;i<107;i++) {
            mDbHelper.retriveNameMostPlayed(i);
            Arreglo_mostplayed[i] = auxMostPlayed;
            //System.out.println(TAG+" Valor Arreglo_mostplayed : "+Arreglo_mostplayed[i]);
        }

        mDbHelper.closeDB();


        float mayor = 0;
        int j=0, auxNum;
        String auxName=null, auxInf=null;

        for (int f = 0; f < Arreglo_mostplayed.length - 1; f++) {
            j=f;
            auxNum = Arreglo_mostplayed[f];
            auxName = Arreglo_nombre[f];
            auxInf = Arreglo_name_info[f];

            while (j>0 && auxNum > Arreglo_mostplayed[j-1])
            {
                Arreglo_mostplayed[j] = Arreglo_mostplayed[j-1];
                Arreglo_nombre[j] = Arreglo_nombre[j-1];
                Arreglo_name_info[j] = Arreglo_name_info[j-1];
                j=j-1;
            }
            Arreglo_mostplayed[j] = auxNum;
            Arreglo_nombre[j] = auxName;
            Arreglo_name_info[j] = auxInf;

        }

        // System.out.println(TAG+"Arreglo_winrate[9] > "+Arreglo_winrate[9]);


        tv1h.setText(Arreglo_nombre[0]);
        tv2h.setText(Arreglo_nombre[1]);
        tv3h.setText(Arreglo_nombre[2]);
        tv4h.setText(Arreglo_nombre[3]);
        tv5h.setText(Arreglo_nombre[4]);
        tv6h.setText(Arreglo_nombre[5]);
        tv7h.setText(Arreglo_nombre[6]);
        tv8h.setText(Arreglo_nombre[7]);
        tv9h.setText(Arreglo_nombre[8]);
        tv10h.setText(Arreglo_nombre[9]);

        String[] cadena = new String[10];
        for(int x=0;x<cadena.length;x++) {
            NumberFormat nf = NumberFormat.getNumberInstance();
            DecimalFormat df = (DecimalFormat)nf;
            cadena[x] = df.format(Arreglo_mostplayed[x]);
        }



        tv1m.setText(cadena[0]+"");
        tv2m.setText(cadena[1]+"");
        tv3m.setText(cadena[2]+"");
        tv4m.setText(cadena[3]+"");
        tv5m.setText(cadena[4]+"");
        tv6m.setText(cadena[5]+"");
        tv7m.setText(cadena[6]+"");
        tv8m.setText(cadena[7]+"");
        tv9m.setText(cadena[8]+"");
        tv10m.setText(cadena[9]+"");

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
