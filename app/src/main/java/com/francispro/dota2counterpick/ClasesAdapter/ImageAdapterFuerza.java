package com.francispro.dota2counterpick.ClasesAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.francispro.dota2counterpick.FuerzaActivity;
import com.francispro.dota2counterpick.Main;
import com.francispro.dota2counterpick.R;
/**
 * Created by franciscojavier on 11-07-14.
 */
public class ImageAdapterFuerza extends BaseAdapter {

    //imageAdapterFuerza
    private Context context;
    public static final String TAG = "--ImageAdapterFuerza ";

    int[] imagesFuerza = {R.drawable.earthshaker_full,R.drawable.sven_full,R.drawable.tiny_full,R.drawable.kunkka_full
            ,R.drawable.beastmaster_full,R.drawable.dragon_knight_full,R.drawable.clockwerk_full,R.drawable.omniknight_full
            ,R.drawable.huskar_full,R.drawable.alchemist_full,R.drawable.brewmaster_full,R.drawable.treant_full
            ,R.drawable.io_full,R.drawable.centaur_full,R.drawable.timbersaw_full,R.drawable.bristleback_full
            ,R.drawable.tusk_full,R.drawable.elder_titan_full,R.drawable.legion_commander_full,R.drawable.earth_spirit_full
            ,R.drawable.axe_full,R.drawable.pudge_full,R.drawable.sand_king_full,R.drawable.slardar_full
            ,R.drawable.tidehunter_full,R.drawable.wraith_king_full,R.drawable.life_stealer_full,R.drawable.night_stalker_full
            ,R.drawable.doom_bringer_full,R.drawable.spirit_breaker_full,R.drawable.lycan_full,R.drawable.chaos_knight_full
            ,R.drawable.undying_full,R.drawable.magnus_full,R.drawable.abaddon_full,R.drawable.phoenix_full};



    public ImageAdapterFuerza(Context applicationContext){
        context = applicationContext;
    }

    public int getCount() {
        //numero de elementos que seran mostras en la grilla
        return imagesFuerza.length;
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

        iv.setImageResource(imagesFuerza[position]);
        return iv;
    }


}
