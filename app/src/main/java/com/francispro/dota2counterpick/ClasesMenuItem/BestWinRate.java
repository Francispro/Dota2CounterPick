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
import com.francispro.dota2counterpick.Connect.httpHandler;
import com.francispro.dota2counterpick.Main;
import com.francispro.dota2counterpick.R;

import java.lang.reflect.Field;


public class BestWinRate extends Activity {

    //Descomente solo si tambien descomenta el comentario 1 para poder usarlo, ya que a estas variables se les asignas los valores desde la consulta a la bd
    //public static String nombre_Array_aux;
    //public static String name_info_Array_aux;

    private static String TAG = "--BestWinRate : ";
    public static String hosting = "http://www.dota2info.hol.es/dota2/";
    public static String win = "";
    private MiTareaAsincrona tarea1;

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

    public static int[] Arreglo_winrate = null;


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





        tarea1 = new MiTareaAsincrona();
        tarea1.execute();





















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

    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog = new ProgressDialog(BestWinRate.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setMessage(getResources().getString(R.string.label_data_loader));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... param) {
            String txt_rate;
            int rateInt = 0;

            for(int i=0;i<107;i++) {
                try{
                    win = Arreglo_name_info[i] + "_w.php";
                    System.out.println(TAG+" 000 Valor win : "+win);
                    httpHandler handler = new httpHandler();
                    txt_rate = handler.post(hosting+win);
                    System.out.println(TAG+" 000 Valor txt_rate pre get_cadega : "+txt_rate);
                    txt_rate = get_cadena(txt_rate);
                    System.out.println(TAG+" 000 Valor txt_rate post get_cadega : "+txt_rate);
                    rateInt = Integer.parseInt(txt_rate);
                    Arreglo_winrate[i] = rateInt;

                }catch(Exception e){
                    Log.e(TAG+"", "Error in http connection "+e.toString());
                }
            }

            int mayor = 0;
            int auxNum,j=0;
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

            tv1r.setText(cadena[0]);
            tv2r.setText(cadena[1]);
            tv3r.setText(cadena[2]);
            tv4r.setText(cadena[3]);
            tv5r.setText(cadena[4]);
            tv6r.setText(cadena[5]);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result){

            if(result)
            {
                progressDialog.dismiss();
            }
        }

    }

    public String get_cadena(String valor){

        String num = null;
        String vfinal = null;
        int aux, aux2;

        for(int i=0; i < valor.length();i++){
            num = valor.substring(i,i+1);
            System.out.println(TAG+" num > "+num);
            try{
                aux = Integer.parseInt(num);

            }catch (Exception e){
                vfinal = valor.substring(0,i);
                System.out.println(TAG+" valor vfinal get_cadena "+vfinal);
                break;
            }
            aux2 = aux;
            System.out.println(TAG+" Valor num get_cadena "+num);
        }
        return vfinal;
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
