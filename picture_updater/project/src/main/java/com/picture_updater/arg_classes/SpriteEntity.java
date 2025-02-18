package com.picture_updater.arg_classes;

import java.io.UnsupportedEncodingException;

public class SpriteEntity {

    private String name;
    private byte[] data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(String data) throws UnsupportedEncodingException {
        this.data = data.getBytes(data);
    }

    public String toString(){
        return "name: " + this.name;
    }
}
