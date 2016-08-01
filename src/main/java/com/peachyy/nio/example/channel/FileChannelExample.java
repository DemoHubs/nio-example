package com.peachyy.nio.example.channel;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 从文件中读写数据。 using nio
 * Created by xsTao on 2016/8/1.
 */

public class FileChannelExample {
    public final static int SIZE=1024;
    @Test
    public void readChannel() throws IOException {
       URL url= FileChannelExample.class.getClassLoader().getResource("example");
        System.out.println(url.getPath());
        RandomAccessFile aFile = new RandomAccessFile(url.getPath(), "rw");
        FileChannel inChannel = aFile.getChannel();//get fileChannel

        ByteBuffer buf = ByteBuffer.allocate(SIZE);//创建一个缓冲区
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("通道的位置：" + inChannel.position());
            System.out.println("Read " + bytesRead);
            buf.flip();//切换到读模式
            System.out.println("通道的位置---：" + inChannel.position());
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());

            }
            System.out.println("通道的位置：" + inChannel.position());

            buf.clear();
            System.out.println("通道的位置：" + inChannel.position());
            bytesRead = inChannel.read(buf);
            System.out.println("清空缓冲区后 缓冲区的数据为:"+bytesRead);
        }
        aFile.close();
    }
    @Test
    public void writeChannel() throws IOException {
        RandomAccessFile aFile=null;
        FileChannel inChannel=null;
        try{
            URL url= FileChannelExample.class.getClassLoader().getResource("example");
            System.out.println(url.getPath());
            aFile = new RandomAccessFile(url.getPath(), "rw");
            inChannel = aFile.getChannel();//get fileChannel

            ByteBuffer buf = ByteBuffer.allocate(SIZE);//创建一个缓冲区
            buf.clear();
            buf.put("hello word  using noi write...".getBytes());
            buf.flip();//切换模式
            while(buf.hasRemaining()){
               inChannel.write(buf);
            }

            System.out.println(inChannel.size());
        }finally {
            aFile.close();
            inChannel.close();
        }

    }
    @Test
    public void Truncate_forceTest() throws IOException {
        RandomAccessFile aFile=null;
        FileChannel inChannel=null;
        try{
            URL url= FileChannelExample.class.getClassLoader().getResource("example");
            System.out.println(url.getPath());
            aFile = new RandomAccessFile(url.getPath(), "rw");
            inChannel = aFile.getChannel();//get fileChannel

            ByteBuffer buf = ByteBuffer.allocate(SIZE);//创建一个缓冲区
            buf.clear();
            inChannel.read(buf);
            buf.flip();//
            inChannel.truncate(10);
            //从第10个字节处开始截断
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());

            }

            System.out.println();
            System.out.println("截断后的长度为"+inChannel.size());
            //将通道里尚未写入磁盘的数据强制写到磁盘
            inChannel.force(true);
            System.out.println("强制写到磁盘后的长度为"+inChannel.size());
        }finally {
            aFile.close();
            inChannel.close();
        }
    }


}
