package com.francispro.dota2counterpick.Connect;

/**
 * Created by franciscojavier on 11-07-14.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import java.util.Scanner;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by franciscojavier on 23-06-14.
 */
public class httpHandler {

    public String TAG = "--httpHandler : ";

    public String post(String posturl){

        try{

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(posturl);

            HttpResponse responde = httpClient.execute(httpPost);
            HttpEntity entity = responde.getEntity();

            String text  = EntityUtils.toString(entity);
            text = text.substring(0,5);


            System.out.println(TAG+""+text);
            return text;

        }catch (Exception e)
        {
            System.out.println(TAG+" ERROR >"+e);
            return "error";
        }


    }

    public String post2(String posturl){

        try{

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(posturl);

            HttpResponse responde = httpClient.execute(httpPost);
            HttpEntity entity = responde.getEntity();

            String text  = EntityUtils.toString(entity);
            text = text.substring(0,5);
            text = get_cadena(text);

            System.out.println(TAG+" Popularity "+text);
            return text;

        }catch (Exception e)
        {
            System.out.println(TAG+" ERROR2 >"+e);
            return "error";
        }


    }

    public String get_cadena(String valor){

        String num = null;
        String vfinal = null;
        int aux, aux2;

        for(int i=0; i < valor.length();i++){
            num = valor.substring(i,i+1);
            try{
                aux = Integer.parseInt(num);

            }catch (Exception e){
                vfinal = valor.substring(0,i);
                System.out.println(TAG+" ccc Entro coto! "+vfinal);
                break;
            }
            aux2 = aux;
            System.out.println(TAG+" ccc Valor num "+num);
        }
        return vfinal;
    }

}
