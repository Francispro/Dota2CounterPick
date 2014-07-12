package com.francispro.dota2counterpick.ClasesAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.francispro.dota2counterpick.Main;
import com.francispro.dota2counterpick.R;
/**
 * Created by franciscojavier on 11-07-14.
 */
public class ImageAdapterInteligencia extends BaseAdapter {

    int[] imagesInteligencia = {R.drawable.crystal_maiden_full,R.drawable.puck_full,R.drawable.storm_spirit_full,R.drawable.windranger_full
            ,R.drawable.zeus_full,R.drawable.lina_full,R.drawable.shadow_shaman_full,R.drawable.tinker_full
            ,R.drawable.natures_prophet_full,R.drawable.enchantress_full,R.drawable.jakiro_full,R.drawable.chen_full
            ,R.drawable.silencer_full,R.drawable.ogre_magi_full,R.drawable.rubick_full,R.drawable.disruptor_full
            ,R.drawable.keeper_of_the_light_full,R.drawable.skywrath_mage_full,R.drawable.bane_full,R.drawable.lich_full
            ,R.drawable.lion_full,R.drawable.witch_doctor_full,R.drawable.enigma_full,R.drawable.necrophos_full
            ,R.drawable.warlock_full,R.drawable.queenofpain_full,R.drawable.death_prophet_full,R.drawable.pugna_full
            ,R.drawable.dazzle_full,R.drawable.leshrac_full,R.drawable.dark_seer_full,R.drawable.batrider_full
            ,R.drawable.ancient_apparition_full,R.drawable.invoker_full,R.drawable.outworld_devourer_full,R.drawable.shadow_demon_full
            ,R.drawable.visage_full};

    private Context context;

    public ImageAdapterInteligencia(Context applicationContext){
        context = applicationContext;
    }

    public int getCount() {
        //numero de elementos que seran mostras en la grilla
        return imagesInteligencia.length;
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
            if(Main.pixels == 300) {
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
            iv.setPadding(8,4,8,4);//ajusta la separacion entre cada una de las imagenes de la grilla
        }

        iv.setImageResource(imagesInteligencia[position]);

        return iv;
    }
}
