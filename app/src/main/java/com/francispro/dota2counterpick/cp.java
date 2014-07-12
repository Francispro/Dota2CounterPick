package com.francispro.dota2counterpick;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.francispro.dota2counterpick.ClasesAdapter.CounterPickAdapter;

public class cp extends ActionBarActivity {

    public static final String TAG = "--cp ";
    public static int[] URL_CP, Counterpcik_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp);

        Bundle b=this.getIntent().getExtras();
        URL_CP = b.getIntArray("MiArrayCp");
        Counterpcik_id = b.getIntArray("MiArrayIDCP");

        /*for(int x=0;x<6;x++){
            System.out.println(TAG+" Valor URL_CP["+x+"] = "+URL_CP[x]);
        }*/

        GridView gv = (GridView)findViewById(R.id.gridViewCP);
        gv.setAdapter(new CounterPickAdapter(getApplicationContext()));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentView, View iv, int position, long id) {
                int aux=0;
                for(int x=0;x<CounterPickActivity.largoCP_SP;x++){
                    if(x==position){
                        aux=Counterpcik_id[x];
                        System.out.println(TAG+" Valor aux"+aux);
                    }
                }

                Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
                i.putExtra("id",aux);
                startActivity(i);
            }
        });
    }



}
