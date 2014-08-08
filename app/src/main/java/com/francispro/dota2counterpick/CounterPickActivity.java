package com.francispro.dota2counterpick;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import com.francispro.dota2counterpick.ClasesDataBase.CopyAdapter;
import com.francispro.dota2counterpick.ClasesMenuItem.BestWinRate;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;


public class CounterPickActivity extends TabActivity {

    public static final String TAG = "--CounterPickActivity ";
    private TabHost th_CP;
    private ViewGroup vg;
    public static TextView tv = null,Atributo = null, Nombre = null;
    public static String URL_IMAGEN, CounterPick, StrongerPick;
    private int Currentab = 0, position=0;
    public int Identificador = 0;
    public static int pixels,largoCP_SP=8;
    public static int [] cp = {0,0,0,0,0,0,0,0};
    public static int [] sp = {0,0,0,0,0,0,0,0};
    public static String [] cp_url = new String[largoCP_SP];
    public static String [] sp_url = new String[largoCP_SP];
    public boolean Conexion ;

    //Variables para los Adapter
    public static int [] CPA_CP = {0,0,0,0,0,0,0,0};
    public static int [] SPA_SP = {0,0,0,0,0,0,0,0};

    private static final String DB_NAME = "DBDota2.sqlite";
    private static final int DATABASE_VERSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_pick);

        Bundle bundle = getIntent().getExtras();
        Identificador = bundle.getInt("id");

        //comando de ayuda para mostrar siempre el menu opciones
        getOverflowMenu();

        /*Medidas de DPI
         XHDPI < 300
         HDPI  = 240
         MDPI  = 160
         LDPI  = 120
        */
        Resources res = getResources();
        int dips = 200,aux;
        float pixelsFloat = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, res.getDisplayMetrics());
        pixels = (int)pixelsFloat;

        //crea una variable con un nuevo tipo de fuente, el font tiene que estar en la carpeta assets
        Typeface face = Typeface.createFromAsset(getAssets(), "OptimusPrinceps.ttf");

        Intent extraer = getIntent();
        position = (Integer) extraer.getExtras().get("id");

        ImageButton imageBanner = (ImageButton)findViewById(R.id.imageButtonCP);
        imageBanner.setImageResource(R.drawable.centaur_full);
        //imageBanner.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Nombre = (TextView)findViewById(R.id.Nombre_hero);
        Nombre.setText("Anti-mage");
        Nombre.setTextSize(22);
        Nombre.setTypeface(face);

        Atributo = (TextView)findViewById(R.id.Atributos_hero);
        Atributo.setText("Cuerpo a cuerpo - Portador - Escurridizo");
        Atributo.setTextSize(14);

        ImageButton imageButton;
        imageButton = (ImageButton)findViewById(R.id.imageButtonCP);




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Conexion = isOnline();
                if(Conexion==true)
                {
                    System.out.println("Conexion a internet ok");
                    Intent i = new Intent(getApplicationContext(), Informacion.class);
                    i.putExtra("id",position);
                    i.putExtra("pixels",pixels);
                    startActivity(i);

                }else{
                    new AlertDialog.Builder(CounterPickActivity.this)
                            .setTitle(getResources().getString(R.string.label_data_connection_title))
                            .setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert))
                            .setMessage(getResources().getString(R.string.label_data_connection))
                            .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            })
                            .show();
                    //Toast.makeText(CounterPickActivity.this, "No hay conexión a internet.", Toast.LENGTH_LONG).show();
                }


            }
        });


        Locale locale = Locale.getDefault();
        System.out.println("Locale is : [" + locale + "]"); // make sure there is a default Locale
        Calendar calendar = Calendar.getInstance(locale);

        //System.out.println("--CounterPick : 1");
        CopyAdapter mDbHelper = new CopyAdapter(CounterPickActivity.this);
        try {
            mDbHelper.createDatabase();
            System.out.println("--CounterPick : 2");
        } catch (SQLException e) {
            System.out.println("--CounterPick : 3");
            e.printStackTrace();

        }

        //System.out.println("--CounterPick : Valor Identificador: "+Identificador+"");


        mDbHelper.retriveData(Identificador);
        //el valor URL_IMAGEN es obtenido en la clase CopyAdapter y asignado a la variable publica para posteriormente ser asignado como ruta
        //carga la imagen para el banner
        int res_imagen = this.getResources().getIdentifier("drawable/" + URL_IMAGEN+"_banner", null, this.getPackageName());
        imageBanner.setImageResource(res_imagen);
        //System.out.println("--CounterPick : Valor CounterPick : "+CounterPick+"-"+res_imagen);

        TransformarCP(CounterPick);
        TransformarSP(StrongerPick);

        for(int x=0; x<largoCP_SP;x++)
        {
            cp_url[x] = mDbHelper.RegresaURL_Imagen(cp[x]);
            sp_url[x] = mDbHelper.RegresaURL_Imagen(sp[x]);
        }
        //System.out.println("--CounterPick : Valor cp_url: "+cp_url+"-"+sp_url);

        for(int x=0; x<largoCP_SP;x++)
        {
            CPA_CP[x] = this.getResources().getIdentifier("drawable/" + cp_url[x], null, this.getPackageName());
            SPA_SP[x] = this.getResources().getIdentifier("drawable/" + sp_url[x], null, this.getPackageName());
        }




        th_CP = getTabHost();
        TabHost.TabSpec specCP;
        Intent intent;

        Bundle b = new Bundle();
        b.putIntArray("MiArrayCp",CPA_CP);
        b.putIntArray("MiArraySp",SPA_SP);
        b.putIntArray("MiArrayIDCP",cp);
        b.putIntArray("MiArrayIDSP",sp);
        b.putInt("pixels",pixels);

        //CounterPick tabs
        intent = new Intent(this, cp.class);
        intent.putExtras(b);
        specCP = th_CP.newTabSpec("counterpick")
                .setIndicator("CounterPick")
                .setContent(intent);
        th_CP.addTab(specCP);

        //StrongerPick tabs
        intent = new Intent(this, sp.class);
        intent.putExtras(b);
        specCP = th_CP.newTabSpec("strongerpick")
                .setIndicator("StrongerPick")
                .setContent(intent);
        th_CP.addTab(specCP);


        // permite elegir que tab se muestre primero al ejecutar la aplicacion
        th_CP.setCurrentTab(0);

        int tamanio = 0;
        if(pixels==300)
        {
            tamanio = (int)getResources().getDimension(R.dimen.textsize300);
        }else if(pixels==400){
            tamanio = (int)getResources().getDimension(R.dimen.textsize400);
        }else if(pixels<400){
            tamanio = (int)getResources().getDimension(R.dimen.textsize600);
        }

        // pinta el texto de cada tab al correr la aplicacion
        for (int i = 0; i < th_CP.getTabWidget().getTabCount(); i++) {
            vg = (ViewGroup) getTabHost().getTabWidget().getChildAt(i);
            tv = (TextView) vg.getChildAt(1);

            //modifica el tamaño de la letra, se relaciona con el archivo values/dimensions.xml
            //tv.setTextSize(getResources().getDimension(R.dimen.textsize));
            tv.setTextSize(tamanio);

            //cambia el tipo de fuente por defecto por el que se cargo al "face"
            tv.setTypeface(face);

            if (i == 0) {
                tv.setTextColor(Color.parseColor("#2daed9"));
                Currentab = 0;
            } else {
                tv.setTextColor(Color.parseColor("#a9a9a9"));
            }
        }

        // configura el color de cada tab una vez que es pulsada
        getTabHost().setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                int i = getTabHost().getCurrentTab();
                if (Currentab != i) {
                    vg = (ViewGroup) getTabHost().getTabWidget().getChildAt(Currentab);
                    tv = (TextView) vg.getChildAt(1);
                    tv.setTextColor(Color.parseColor("#a9a9a9"));// cuando esta off

                    Currentab = i;
                    vg = (ViewGroup) getTabHost().getTabWidget()
                            .getChildAt(i);
                    tv = (TextView) vg.getChildAt(1);
                    tv.setTextColor(Color.parseColor("#2daed9"));// cuando esta on
                }
            }
        });

    }


    public void TransformarCP(String valor)
    {
        String []s = CounterPick.split(",");
        for(int x=0;x<largoCP_SP;x++)
        {
            cp[x]= Integer.parseInt(s[x]);
            //System.out.println("valor de x : "+cp[x]+" valor de x : "+x);
        }
    }

    public void TransformarSP(String valor)
    {
        String []s = StrongerPick.split(",");
        for(int x=0;x<largoCP_SP;x++)
        {
            sp[x]= Integer.parseInt(s[x]);
            //System.out.println("valor de x : "+sp[x]+" valor de x : "+x);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(position>=35)
        {
            Intent i = new Intent(getApplicationContext(), FuerzaActivity.class);
            startActivity(i);
            finish();

        }else if(position>35 && position<=69)
        {
            Intent i = new Intent(getApplicationContext(), AgilidadActivity.class);
            startActivity(i);
            finish();
        }else if(position<69)
        {
            Intent i = new Intent(getApplicationContext(), InteligenciaActivity.class);
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
