package com.example.demo;
import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class MIDIPlayer {
    private File MidiFile;
    private Sequence sequence;
    private static Sequencer sequencer;
    public MIDIPlayer(File MidiFile) throws InvalidMidiDataException, IOException, MidiUnavailableException {
        this.MidiFile=MidiFile;
        this.sequence= MidiSystem.getSequence(MidiFile);
        this.sequencer=MidiSystem.getSequencer();
    }
    public void midiplay() throws IOException, MidiUnavailableException, InvalidMidiDataException {

        if (sequencer == null) {
            throw new IOException("未找到可用序列化音序器");
        }
        //将播放器通电
        sequencer.open();
        //将唱片放入播放器
        sequencer.setSequence(sequence);
        //按下播放键
        sequencer.start();
    }
    public void midiPause(){
        sequencer.stop();
    }
    public double getMidiPositon(){
        return (double) sequencer.getMicrosecondPosition() /sequencer.getMicrosecondLength();
    }
    public void setMidiPositon(double pos){
        sequencer.setMicrosecondPosition((long) (pos*sequencer.getMicrosecondLength()));
    }
    public void stopMIDI(){
        sequencer.close();
    }
    public static void main(String[] args) {
            //try {
                File f=new File("C:\\Users\\pc\\CS61b\\CS61b\\demo\\music\\9.");
        System.out.println(f.getName());
                //在文件的名字大于等于3的情况下
        if(f.getName().contains("mid"))
              System.out.println(f.getName().substring(f.getName().length()-3));
        System.out.println("e");
                /*MIDIPlayer p =new MIDIPlayer(f);


                p.midiplay();
                sleep(10000);
                p.midiPause();
                //System.out.println(sequencer.getMicrosecondPosition());
                sleep(10000);
                p.midiplay();
                sleep(10000);
                p.setMidiPositon(0);
                //循环播放
                //sequencer.setLoopCount(2);

            } catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }

}
