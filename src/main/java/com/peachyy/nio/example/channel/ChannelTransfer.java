package com.peachyy.nio.example.channel;

import com.peachyy.nio.example.util.Tools;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 通道之间的数据传输 以<code>FileChannel</code>为例<br/>
 * Created by xsTao on 2016/8/1.
 */
public class ChannelTransfer {

    @Test
    public void testTransfer() throws IOException {

        RandomAccessFile fromFile = new RandomAccessFile(Tools.getFileClassPath("example"), "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile(Tools.getClassPath()+"/toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        //直接把fromChannel通道的数据传递到toChannel中
        //运行完毕后target/classes/toFile.txt中将会出现和example中一模一样的数据。
        //1、
        // toChannel.transferFrom(fromChannel,position,count);
        //2、
        fromChannel.transferTo(0,count,toChannel);

    }
}
