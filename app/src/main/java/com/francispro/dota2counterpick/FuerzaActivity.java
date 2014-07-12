package com.francispro.dota2counterpick;


import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import com.francispro.dota2counterpick.ClasesAdapter.ImageAdapterAgilidad;
import com.francispro.dota2counterpick.ClasesAdapter.ImageAdapterFuerza;

import javax.microedition.khronos.opengles.GL10Ext;


public class FuerzaActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza);

        GridView gv = (GridView)findViewById(R.id.gridViewFuerza);
        gv.setAdapter(null);
        gv.setAdapter(new ImageAdapterFuerza(getApplicationContext()));



        gv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentView, View iv, int position, long id) {
                Intent i = new Intent(getApplicationContext(), CounterPickActivity.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        FuerzaActivity.this.finish();
    }

}
