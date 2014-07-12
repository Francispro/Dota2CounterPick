package com.francispro.dota2counterpick;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.francispro.dota2counterpick.Connect.httpHandler;
import com.francispro.dota2counterpick.ClasesDataBase.CopyAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class Informacion extends Activity {

    public String TAG = "--Informacion : ";
    public static int Identificador = 0;
    public String txt_rate = null, txt_pop = null, color;
    private MiTareaAsincrona tarea1;
    public static TextView Text_Name, Text_pop, Text_win;
    public static String URL_IMAGEN_INFO = null;
    public static String name_info = null;
    public static String win,pop,resolucion;
    public static float Pantalla=0;
    public Typeface faceOP,faceCD;
    public WebView mCharView,mCharView2;



    //Variables Chart API
    public String src = "http://chart.apis.google.com/chart?";
    public String tamanio = "chs=350x150";
    public String porcentajes = "&chd=t:30,50,20";
    public String porciones = "&cht=p3";
    public String nombres = "chl=Win Rate|Daniel|Jose";
    public String color_graficos = "%&chco=1d9524,971497";


    // http://chart.apis.google.com/chart?chs=250x100&chd=t:12.5,43.5,44.0&cht=p3&chl=Luis|Ana|Jesus

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        getOverflowMenu();

        Bundle bundle = getIntent().getExtras();
        Identificador = bundle.getInt("id");
        Pantalla = bundle.getFloat("pixels");

        faceOP = Typeface.createFromAsset(getAssets(), "OptimusPrinceps.ttf");
        faceCD = Typeface.createFromAsset(getAssets(), "CaviarDreams.ttf");

        Text_Name = (TextView)findViewById(R.id.editText_Name);
        Text_pop = (TextView)findViewById(R.id.editText_pop);
        Text_win = (TextView)findViewById(R.id.editText_win);
        Text_Name.setTypeface(faceOP);
        Text_Name.setTextSize(22);
        Text_pop.setTypeface(faceCD);
        Text_win.setTypeface(faceCD);

        ImageView imageBanner = (ImageView)findViewById(R.id.imageViewInfo);

        CopyAdapter mDbHelper = new CopyAdapter(Informacion.this);
        mDbHelper.retriveDataFromInfo(Identificador);
        int res_imagen = this.getResources().getIdentifier("drawable/" + URL_IMAGEN_INFO+"_banner", null, this.getPackageName());
        imageBanner.setImageResource(res_imagen);

        if(Pantalla>=400)
        {
            resolucion = "300x150";
        }else{
            resolucion = "250x100";
        }


        tarea1 = new MiTareaAsincrona();
        tarea1.execute();

        System.out.println(TAG+" >>> "+name_info);
        win = name_info+"_w.php";
        pop = name_info+"_p.php";

    }





    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progressDialog = new ProgressDialog(Informacion.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setMessage(getResources().getString(R.string.label_data_loader));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... param) {

            try{
                //WinRate
                httpHandler handler = new httpHandler();
                txt_rate = handler.post("http://infodota2clone.comli.com/Dota2/"+win);
                System.out.println(TAG+" URL > http://infodota2clone.comli.com/Dota2/"+win);
                float rate = Float.parseFloat(txt_rate);
                System.out.println(TAG + " WINRATE > " + rate);
                float sobra = 100-rate;
                String url =  "http://chart.apis.google.com/chart?chf=bg,s,171919&chs="+resolucion+"&chd=t:"+rate+","+sobra+"&cht=p&chl="+rate+"%|"+sobra+"%&chco=669900,99CC00";
                mCharView = (WebView) findViewById(R.id.char_view);
                mCharView.loadUrl(url);

                mCharView.clearHistory();
                mCharView.clearFormData();
                mCharView.clearCache(true);


                //Popularity
                httpHandler handler2 = new httpHandler();
                txt_pop = handler2.post2("http://infodota2clone.comli.com/Dota2/"+pop);

                float pop = Float.parseFloat(txt_pop);
                float sobra2 = (pop*100)/107;
                float porcentaje = 100-sobra2;

                url =  "http://chart.apis.google.com/chart?chf=bg,s,171919&chs="+resolucion+"&chd=t:"+porcentaje+","+sobra2+"&cht=p&chl="+porcentaje+"%|"+sobra2+"%&chco=0099CC,33B5E5";
                mCharView2 = (WebView) findViewById(R.id.char_view_pop);
                mCharView2.loadUrl(url);

                mCharView2.clearHistory();
                mCharView2.clearFormData();
                mCharView2.clearCache(true);


            }catch(Exception e){
                Log.e(TAG+"", "Error in http connection "+e.toString());
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result){

            if(result)
            {
                progressDialog.dismiss();
                Text_win.setText("Win rate "+txt_rate+"%");
                Text_pop.setText("Popularity "+txt_pop+"th");
                mCharView2.setVisibility(View.VISIBLE);
                mCharView.setVisibility(View.VISIBLE);

                //Toast.makeText(Informacion.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();

            }
        }

    }

    @Override
    public void onBackPressed() {
        if (Identificador >= 35) {
            Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
            i.putExtra("id", Identificador);
            startActivity(i);
            finish();

        } else if (Identificador > 35 && Identificador <= 69) {
            Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
            i.putExtra("id", Identificador);
            startActivity(i);
            finish();
        } else if (Identificador < 69) {
            Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
            i.putExtra("id", Identificador);
            startActivity(i);
            finish();
        }

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
            case R.id.TopWinRate:
                return true;
            case R.id.TopPopularity:
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
