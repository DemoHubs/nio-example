package com.peachyy.nio.example.util;

import com.peachyy.nio.example.channel.FileChannelExample;

import java.net.URL;

/**
 * Created by xsTao on 2016/8/1.
 */
public class Tools {
    public static String getFileClassPath(String file){
        URL url= FileChannelExample.class.getClassLoader().getResource(file);
        return url.getPath();
    }
    public static String getClassPath(){
        URL url= FileChannelExample.class.getClassLoader().getResource("");
        return url.getPath();
    }
}
