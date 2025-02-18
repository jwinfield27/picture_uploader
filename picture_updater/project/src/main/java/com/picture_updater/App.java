package com.picture_updater;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.picture_updater.arg_classes.PictureData;
import com.picture_updater.arg_classes.SpriteEntity;


public class App {
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Map<String,String> pd = PictureData.getInstance().getPicturePathMap();

        HttpGet httpget = new HttpGet("http://10.0.0.19:8080/sprites/all");
        HttpResponse response = fetchSpriteData(httpget, httpclient);
        if (response == null){
            System.exit(0);
        }

        HttpEntity response_entity = response.getEntity();
        String content_string = getContentString(response_entity);
        if (content_string == null){
            System.exit(0);
        }

        Gson gson = new Gson();
        Type typeT = new TypeToken<List<SpriteEntity>>(){}.getType();
        List<SpriteEntity> sprite_list = gson.fromJson(content_string, typeT);
        for(SpriteEntity se : sprite_list){
            System.out.println(se.toString());
            System.out.println(se.getData());
        }
    }

    private static HttpResponse fetchSpriteData(HttpGet httpget, CloseableHttpClient client){
        HttpResponse res = null;
        try{
            res = client.execute(httpget);
            System.out.println("Status " + res.getStatusLine().getStatusCode());
            return res;
        } catch(IOException e){
            System.out.println("error occured reaching out to sprite endpoint");
            System.out.println(e.getMessage());
            return res;
        }
    }

    private static String getContentString(HttpEntity response_entity){
        try{
            return EntityUtils.toString(response_entity);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
