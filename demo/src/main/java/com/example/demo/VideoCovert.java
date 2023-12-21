package com.example.demo;
import ws.schild.jave.*;

import javax.swing.*;
import java.io.*;
/**
 * 此类调用JAVE的API实现视频部分的格式转换
 */


public class VideoCovert {

    public static int To_mov() throws IOException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".mov");
            System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
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
                target.delete();//如果不成功则删去新创建的target文件
                ex.printStackTrace();
                return -1;
            }
            return 0;

        } else {
            System.out.println("无法获取权限");
            return -1;
        }



    }

    public static int To_avi() throws IOException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".avi");
            //System.out.println(target.getName());
            //System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
            try {
                //生成带有 MPEG 4/DivX 视频和 OGG Vorbis 音频的 AVI：
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec("aac");
                System.out.println("audio");
                VideoAttributes video = new VideoAttributes();
                video.setCodec("mpeg4");
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
                target.delete();
                ex.printStackTrace();
                return -1;

            }
           return 0;

        } else {
            System.out.println("无法获取权限");
            return -1;
        }

    }

    public static int To_asf() throws IOException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".asf");

            System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
            try {

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
                target.delete();
                ex.printStackTrace();
                return -1;
            }
            return 0;

        } else {
            System.out.println("无法获取权限");
            return -1;
        }


    }

    public static int To_flv() throws IOException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".flv");
            System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
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
                target.delete();
                ex.printStackTrace();
                return -1;
            }
            return 0;

        } else {
            System.out.println("无法获取权限");
            return -1;
        }

    }

    public static int To_dvd() throws IOException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".dvd");
            //System.out.println(target.getName());
            //System.out.println(source.getName());
            //只有这个文件是mp4才开始转换
            System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
            try {
                //生成带有 MPEG 4/DivX 视频和 OGG Vorbis 音频的 AVI：
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec("ac3");// 设置音频编解码器为 libvorbis
                System.out.println("audio");
                VideoAttributes video = new VideoAttributes();
                //video.setCodec("dvix");
                video.setCodec("mpeg2video");
                System.out.println("video");
                video.setBitRate(1280000);//设置比特率为1280 kb / s
                video.setFrameRate(30);
                EncodingAttributes attr = new EncodingAttributes();
                attr.setFormat("dvd");
                attr.setAudioAttributes(audio);
                attr.setVideoAttributes(video);
                //encode
                Encoder encoder = new Encoder();
                encoder.encode(new MultimediaObject(source), target, attr);


            } catch (Exception ex) {
                target.delete();
                ex.printStackTrace();
                return -1;
            }
            return 0;

        } else {
            System.out.println("无法获取权限");
            return -1;
        }
    }

    public static int toMP4 () throws EncoderException {
        File source, target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".mp4");

            System.out.println("Converting \"" + source.getName() + "\" to \"" + target.getName() + "\"...");
            try {
                //生成带有h264视频和mp3音频的MP4文件：
                AudioAttributes audio = new AudioAttributes();
                audio.setCodec("mp3");// 设置音频编解码器为mp3
                VideoAttributes video = new VideoAttributes();
                video.setCodec("h264");
                System.out.println("video");
                video.setBitRate(1280000);//设置比特率为1280 kb / s
                video.setFrameRate(30);
                EncodingAttributes attr = new EncodingAttributes();
                attr.setFormat("mp4");
                attr.setAudioAttributes(audio);
                attr.setVideoAttributes(video);
                //encode
                Encoder encoder = new Encoder();
                encoder.encode(new MultimediaObject(source), target, attr);


            } catch (Exception ex) {
                target.delete();
                ex.printStackTrace();
                return -1;
            }
           return 0;

       } else {
        System.out.println("无法获取权限");
        return -1;
        }
    }

}
