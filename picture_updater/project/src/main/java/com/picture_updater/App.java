package com.picture_updater;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.picture_updater.arg_classes.PictureData;


public class App {
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Map<String,String> pd = PictureData.getInstance().getPicturePathMap();

        HttpGet httpget = new HttpGet("http://10.0.0.18:8080/sprites/all");
        HttpResponse response = fetchSpriteData(httpget, httpclient);
        if (response == null){
            System.exit(0);
        }

        HttpEntity response_entity = response.getEntity();
        printResponse(response_entity);
    }

    private static HttpResponse fetchSpriteData(HttpGet httpget, CloseableHttpClient client){
        HttpResponse res = null;
        try{
            res = client.execute(httpget);
            System.out.println(
                                "Status" + res.getStatusLine().getStatusCode() + 
                                " reason: " + res.getStatusLine().getReasonPhrase()
                            );
            return res;
        } catch(IOException e){
            System.out.println("error occured reaching out to sprite endpoint");
            System.out.println(e.getMessage());
            return res;
        }
    }

    private static void printResponse(HttpEntity response_entity){
        try{
            System.out.println(EntityUtils.toString(response_entity, StandardCharsets.UTF_8));
        } catch(IOException e){
            System.out.println("error could not print response payload");
            System.exit(0);
        }
    }

    private static InputStream getResponseContent(HttpEntity entity){
        InputStream er_stream = null;
        try{
            return entity.getContent();
        } catch (IOException e){
            System.out.println("error creating response content input stream");
            System.out.println("reason: " + e.getMessage());
            return er_stream;
        }
    }
}
