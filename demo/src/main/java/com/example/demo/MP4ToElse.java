package com.example.demo;
import ws.schild.jave.*;

import javax.swing.*;
import java.io.*;

public class MP4ToElse {
    /*private static class ConversionThread extends Thread{
        private File source;
        private File target;
        //在原来的thread基础上更改使得可以传入文件参数
        public ConversionThread(File source,File target){
            this.source = source;
            this.target = target;
        }

        public void run(){
            try {
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

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }*/
    private static StringBuffer mp4FileHead=new StringBuffer("000000206674");
    //检验文件真实类型是否为MP4
    public static boolean isMP4(File f) throws IOException {
        //用于读取文件
        FileReader reader=new FileReader(f);
        BufferedReader br=new BufferedReader(reader);
        StringBuffer fileHead = new StringBuffer();
        //读取前六个字节并且将结果保存在fileHead中，与mp4FileHead作比较
        for(int i=0;i<6;i++){
            //read会返回一个字节
            int tmp=br.read();
            //如果在文件中读到的字节长度为1，即范围在0~F内的数字，那么要在它的前面加上0，这样才能最后凑成如
            if(Integer.toHexString(tmp).length()==1){
                fileHead.append(0);
            }
            fileHead.append(Integer.toHexString(tmp));
        }
        //释放
        br.close();
        System.out.println(fileHead);
        return fileHead.compareTo(mp4FileHead) == 0;

    }
    public static void mp4_to_mov() throws IOException {
        File source,target;
        JFileChooser chooser = new JFileChooser();
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0,source.getAbsolutePath().indexOf("."))+".mov") ;
            //System.out.println(target.getName());
            //System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            if (isMP4(source)) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
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

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
                //开启转换线程
                //ConversionThread conversionThread =new ConversionThread(source,target) ;
                //conversionThread.start();
                // 等待转换线程完成
                /*try {
                    conversionThread.join();
                    System.out.println("Done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }else {
                System.out.println("输入文件格式错误，请检查格式是否为mp4");
            }

        } else {
            System.out.println("无法获取权限");
        }


    }

    public static void mp4_to_avi() throws IOException {
        File source,target;
        JFileChooser chooser = new JFileChooser();
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0,source.getAbsolutePath().indexOf("."))+".avi") ;
            //System.out.println(target.getName());
            //System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            if (isMP4(source)) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    //生成带有 MPEG 4/DivX 视频和 OGG Vorbis 音频的 AVI：
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("libvorbis");// 设置音频编解码器为 libvorbis
                    System.out.println("audio");
                    VideoAttributes video = new VideoAttributes();
                    video.setCodec("mjpeg");
                    System.out.println("video");
                    video.setBitRate(1280000);//设置比特率为1280 kb / s
                    video.setFrameRate(30);
                    EncodingAttributes attr = new EncodingAttributes();
                    attr.setFormat("avi");
                    attr.setAudioAttributes(audio);
                    attr.setVideoAttributes(video);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attr);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否为mp4");
            }

        } else {
            System.out.println("无法获取权限");
        }

    }

    public static void mp4_to_asf() throws IOException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".asf");
            //System.out.println(target.getName());
            //System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            if (isMP4(source)) {
                System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
                try {
                    //生成带有 MPEG 4/DivX 视频和 OGG Vorbis 音频的 AVI：
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("aac");// 设置音频编解码器为 libvorbis
                    System.out.println("audio");
                    VideoAttributes video = new VideoAttributes();
                    //video.setCodec("dvix");
                    video.setCodec("mpeg4");
                    System.out.println("video");
                    video.setBitRate(1280000);//设置比特率为1280 kb / s
                    video.setFrameRate(30);
                    EncodingAttributes attr = new EncodingAttributes();
                    attr.setFormat("asf");
                    attr.setAudioAttributes(audio);
                    attr.setVideoAttributes(video);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attr);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            } else {
                System.out.println("输入文件格式错误，请检查格式是否为mp4");
            }

        } else {
            System.out.println("无法获取权限");
        }

    }
    public static void mp4_to_flv() throws IOException {
        File source,target;
        JFileChooser chooser = new JFileChooser( System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0,source.getAbsolutePath().indexOf("."))+".flv") ;
            //System.out.println(target.getName());
            //System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            if (isMP4(source)) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    //生成带有 MPEG 4/DivX 视频和 OGG Vorbis 音频的 AVI：
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("aac");// 设置音频编解码器为 libvorbis
                    System.out.println("audio");
                    VideoAttributes video = new VideoAttributes();
                    //video.setCodec("dvix");
                    video.setCodec("flv");
                    System.out.println("video");
                    video.setBitRate(1280000);//设置比特率为1280 kb / s
                    video.setFrameRate(30);
                    EncodingAttributes attr = new EncodingAttributes();
                    attr.setFormat("flv");
                    attr.setAudioAttributes(audio);
                    attr.setVideoAttributes(video);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attr);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否为mp4");
            }

        } else {
            System.out.println("无法获取权限");
        }

    }

}
