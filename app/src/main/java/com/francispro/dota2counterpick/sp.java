package com.francispro.dota2counterpick;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.francispro.dota2counterpick.ClasesAdapter.StrongerPickAdapter;

public class sp extends ActionBarActivity {

    public static final String TAG = "--cp ";
    public static int[] URL_SP, Strongerpcik_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        Bundle b=this.getIntent().getExtras();
        URL_SP = b.getIntArray("MiArraySp");
        Strongerpcik_id = b.getIntArray("MiArrayIDSP");

        GridView gv = (GridView)findViewById(R.id.gridViewSP);
        gv.setAdapter(new StrongerPickAdapter(getApplicationContext()));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentView, View iv, int position, long id) {
                int aux=0;
                for(int x=0;x<CounterPickActivity.largoCP_SP;x++){
                    if(x==position){
                        aux=Strongerpcik_id[x];
                        System.out.println(TAG+" Valor aux"+aux);
                    }
                }

                Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
                i.putExtra("id", aux);
                startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent imain = new Intent(getApplicationContext(), Main.class);
        startActivity(imain);
    }

}
