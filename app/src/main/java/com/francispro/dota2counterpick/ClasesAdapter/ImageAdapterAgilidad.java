package com.francispro.dota2counterpick.ClasesAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.francispro.dota2counterpick.FuerzaActivity;
import com.francispro.dota2counterpick.Main;
import com.francispro.dota2counterpick.R;
/**
 * Created by franciscojavier on 11-07-14.
 */
public class ImageAdapterAgilidad extends BaseAdapter {

    private Context context;
    public static final String TAG = "--ImageAdapterAgility ";

    int[] imagesAgilidad = {R.drawable.antimage_full,R.drawable.drow_ranger_full,R.drawable.juggernaut_full,R.drawable.mirana_full
            ,R.drawable.morphling_full,R.drawable.phantom_lancer_full,R.drawable.vengefulspirit_full,R.drawable.riki_full
            ,R.drawable.sniper_full,R.drawable.templar_assassin_full,R.drawable.luna_full,R.drawable.bounty_hunter_full
            ,R.drawable.ursa_full,R.drawable.gyrocopter_full,R.drawable.lone_druid_full,R.drawable.naga_siren_full
            ,R.drawable.troll_warlord_full,R.drawable.ember_spirit_full,R.drawable.bloodseeker_full,R.drawable.shadow_fiend_full
            ,R.drawable.razor_full,R.drawable.venomancer_full,R.drawable.faceless_void_full,R.drawable.phantom_assassin_full
            ,R.drawable.viper_full,R.drawable.clinkz_full,R.drawable.broodmother_full,R.drawable.weaver_full
            ,R.drawable.spectre_full,R.drawable.meepo_full,R.drawable.nyx_assassin_full,R.drawable.slark_full
            ,R.drawable.medusa_full,R.drawable.terrorblade_full};

    public ImageAdapterAgilidad(Context applicationContext){
        context = applicationContext;
    }

    public int getCount() {
        //numero de elementos que seran mostras en la grilla
        return imagesAgilidad.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv;

        if(convertView!=null){
            iv=(ImageView) convertView;
        }
        else
        {
            iv = new ImageView(context);
            if(Main.pixels <= 300) {
                //HDPI  = 300  480x800
                iv.setLayoutParams(new GridView.LayoutParams(100, 80));//ajusta el (ancho,alto) general de las imagenes de la grilla
            }else if(Main.pixels == 400) {
                //XHDPI < 400  720x1280
                iv.setLayoutParams(new GridView.LayoutParams(150, 120));
            }else if(Main.pixels == 600) {
                //XHDPI < 400  1080x1920
                iv.setLayoutParams(new GridView.LayoutParams(220, 160));
            }

            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8,4,8,4);
        }


        iv.setImageResource(imagesAgilidad[position]);
        return iv;
    }



}
