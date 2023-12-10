package com.example.demo;

import javafx.scene.control.Alert;
import ws.schild.jave.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AudioConvert {

    public static int ToWav() throws EncoderException {
        File source,target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".wav");
            if (true) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("adpcm_ima_wav");
                    audio.setBitRate(128000);
                    audio.setChannels(2);
                    audio.setSamplingRate(44100);

                    //Encoding attributes
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setFormat("wav");
                    attrs.setAudioAttributes(audio);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attrs);
                }catch (Exception ex) {
                    ex.printStackTrace();
                    return -1;
                }
                System.out.println("Done");
                return 0;
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
                return -1;
            }

        } else {
            System.out.println("无法获取权限");
            return -1;
        }

    }


    public static void ToMP3() throws EncoderException {
        File source,target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0,source.getAbsolutePath().indexOf("."))+".mp3") ;
            if (true) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("mp3");
                    audio.setBitRate(128000);
                    audio.setChannels(2);
                    audio.setSamplingRate(44100);

                    //Encoding attributes
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setFormat("mp3");
                    attrs.setAudioAttributes(audio);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attrs);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
            }

        } else {
            System.out.println("无法获取权限");
        }



    }

    public static void ToAiff() throws EncoderException {
        File source,target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0,source.getAbsolutePath().indexOf("."))+".aiff") ;
            if (true) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("pcm_alaw");
                    audio.setBitRate(128000);
                    audio.setChannels(2);
                    audio.setSamplingRate(44100);

                    //Encoding attributes
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setFormat("aiff");
                    attrs.setAudioAttributes(audio);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attrs);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
            }

        } else {
            System.out.println("无法获取权限");
        }



    }

    public static void ToAc3() throws EncoderException {
        File source,target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".ac3");
            if (true) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("ac3");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("ac3");
            attrs.setAudioAttributes(audio);
            //encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);}catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
            }

        } else {
            System.out.println("无法获取权限");
        }
    }
    public static void ToAU() throws EncoderException {
        //不支持midi转au,支持flac和mp3（目前）
        File source,target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".au");
            if (true) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("pcm_alaw");
                    audio.setBitRate(128000);
                    audio.setChannels(2);
                    audio.setSamplingRate(44100);

                    //Encoding attributes
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setFormat("au");
                    attrs.setAudioAttributes(audio);
                    //encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attrs);}catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");;
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
            }

        } else {
            System.out.println("无法获取权限");
        }
    }
    public static void ToAMR() throws EncoderException {
        //不支持midi转au
        File source,target;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        int v = chooser.showOpenDialog(null);
        if (v == JFileChooser.APPROVE_OPTION) {
            source = chooser.getSelectedFile();
            target = new File(source.getAbsolutePath().substring(0, source.getAbsolutePath().indexOf(".")) + ".amr");
            if (true) {
                System.out.println("Converting \""+source.getName()+"\" to \""+target.getName()+"\"...");
                try {
                    //Audio Attributes
                    AudioAttributes audio = new AudioAttributes();
                    audio.setCodec("libvo_amrwbenc");
                    //这里的值不可以设置过高，否则会报错，ffmpeg返回1
                    /*audio.setBitRate(128000);
                    audio.setChannels(1);
                    audio.setSamplingRate(44100);*/
                    audio.setBitRate(16000);
                    audio.setChannels(1);
                    audio.setSamplingRate(16000);

                    //Encoding attributes
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setFormat("amr");
                    attrs.setAudioAttributes(audio);
                    //Encode
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attrs);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
            }

        } else {
            System.out.println("无法获取权限");
        }

    }


    public static void main(String[] args) throws EncoderException, IOException, InterruptedException {


    }
}

