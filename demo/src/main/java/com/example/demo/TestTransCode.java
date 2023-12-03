package com.example.demo;
import ws.schild.jave.*;

import javax.swing.*;
import java.io.File;

public class TestTransCode {
    private static class ConversionThread extends Thread{
        private File source;
        //在原来的thread基础上更改使得可以传入文件参数
        public ConversionThread(File source){
            this.source = source;
        }

        public void run(){
            try {
                File target = new File("C:\\Users\\pc\\CS61b\\CS61b\\demo\\video\\月台.mov");
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec("aac");// 设置音频编解码器为 AAC
                VideoAttributes video = new VideoAttributes();
                video.setCodec("mpeg4");
                video.setBitRate(64000000);//设置比特率为64000 kb / s
                video.setFrameRate(30);
                EncodingAttributes attr = new EncodingAttributes();
                attr.setFormat("mov");
                attr.setAudioAttributes(audio);
                attr.setVideoAttributes(video);
                //encode
                Encoder encoder = new Encoder();
                encoder.encode(new MultimediaObject(source), target, attr);
                System.out.println("Coverting is done.");



            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
    public static boolean isMP4(File f) {

        return true;
    }
    public static void mp4_to_mov() {
        File source;
        JFileChooser chooser = new JFileChooser();
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            if (isMP4(source)) {
                //开启转换线程
                ConversionThread conversionThread =new ConversionThread(source) ;
                conversionThread.start();
                // 等待转换线程完成
                try {
                    conversionThread.join();
                    System.out.println("Done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("输入文件格式错误，请检查格式是否为mp4");
            }

        } else {
            System.out.println("无法获取权限");
        }


    }

}
