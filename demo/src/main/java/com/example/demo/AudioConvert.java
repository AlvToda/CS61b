package com.example.demo;

import ws.schild.jave.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static com.example.demo.MP4ToElse.isMP4;

public class AudioConvert {

    public static void FlacToWav() throws EncoderException {
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
                }
                System.out.println("Done");
            }else {
                System.out.println("输入文件格式错误，请检查格式是否支持");
            }

        } else {
            System.out.println("无法获取权限");
        }

    }


    public static void FlacToMP3() throws EncoderException {
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

    public static void FlacToAiff() throws EncoderException {
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
    public static void main(String[] args) throws EncoderException, IOException {


    }


}

