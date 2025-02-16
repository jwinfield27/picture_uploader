package com.picture_updater.arg_classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class PictureArgs {
    BufferedImage bi;
    String picture_name;

    public PictureArgs(String picture_path, String picture_name) throws IOException {
        bi = ImageIO.read(new File(picture_path)); 
    }

}
