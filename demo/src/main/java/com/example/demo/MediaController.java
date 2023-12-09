package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.lang.Thread.sleep;


public class MediaController implements Initializable {

    @FXML
    private Label NameLabel;
    @FXML
    private Button NextButton,PlayButton,FastForwardButton,PauseButton,SelectFilesButton,RewindButton,StopButton,ResetButton,PlayVideo,ChooseFilesButton;
    @FXML
    private Circle RotatingCircle;
    @FXML
    private AnchorPane PaneForCircle;
    @FXML
    private ProgressBar ProgressBar;
    @FXML
    private MenuButton PlayListShowButton,AudioConvertButton;
    @FXML
    private ArrayList<MenuItem> SongMenu;
   /* @FXML
    private Slider VolumeController;*/
    private File directory;
    private File[] files;
    private ArrayList<File> playlist;
    private int NumberOfSongs;
    private Timer time;
    private TimerTask task;
    private boolean isRunning;

    private EmbeddedMediaPlayer MyPlayer;
    private MediaPlayerFactory mediaPlayerFactory;
    private RotateTransition rt;
    private EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            BeginTiming();

            if(isRunning&&isMidi){
                midiPlayer.stopMIDI();
            }//如果之前播放的不是midi文件的话，那么将原来的播放器停掉
            else if(isRunning&&!isMidi){
                MyPlayer.stop();
            }
            NumberOfSongs= PlayListShowButton.getItems().indexOf((MenuItem)e.getSource());
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
            if(playlist.get(NumberOfSongs).getName().length()>2&&playlist.get(NumberOfSongs).getName().substring(playlist.get(NumberOfSongs).getName().length()-3).equals("mid")){
                try {
                    midiPlayer=new MIDIPlayer(new File(playlist.get(NumberOfSongs).getAbsolutePath()));
                    midiPlayer.midiplay();
                    isMidi=true;
                } catch (InvalidMidiDataException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (MidiUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                //改变了曲目之后要改变isMIDI，不然getPostion出问题
                isMidi=false;
                MyPlayer.startMedia(playlist.get(NumberOfSongs).getAbsolutePath());
            }
            isRunning=true;
            rt.play();  // 开始播放动画
            /***
            NumberOfSongs=SongMenu.indexOf((MenuItem)e.getSource());
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
            MyPlayer.startMedia(playlist.get(NumberOfSongs).getAbsolutePath());***/
        }
    };
    private boolean isMidi;
    private MIDIPlayer midiPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO :for study
        /////////////// for study //////////
        Thread t=Thread.currentThread();
        long id= t.threadId();
        System.out.println("init:"+id);
        // 创建一个旋转的过渡动画，构建方法中，第一个参数指定完成一次旋转所需要的时间，第二个参数是旋转的对象
        rt = new RotateTransition(Duration.seconds(7), PaneForCircle);

        rt.setByAngle(360);  // 设置旋转的角度

        rt.setCycleCount(Animation.INDEFINITE);  // 设置旋转次数，设置旋转无数次

        rt.setInterpolator(Interpolator.LINEAR);  // 控制每个过渡周期的加速和减速时间，设置为匀速

        ProgressBar.setStyle("-fx-accent:#2e4565;");


        playlist=new ArrayList<File>();
        directory=new File( "music");
        isRunning=false;
        files=directory.listFiles();
        if(files!=null){
            for(File f:files){
                playlist.add(f);
                MenuItem m=new MenuItem(f.getName());
                //SongMenu.add(m);
                m.setOnAction(event1);
                PlayListShowButton.getItems().add(m);
            }
            String path=playlist.get(NumberOfSongs).getAbsolutePath();
            System.out.println(path);
            isMidi=false;
            //如果后缀名为mid的话，认为是midi文件，设置为true,并且new 一个midiplayer对象(检查后缀不使用contain)
            if(path.substring(path.length()-3).equals("mid")){
                isMidi=true;
                try {
                    midiPlayer=new MIDIPlayer(new File(playlist.get(NumberOfSongs).getAbsolutePath()));
                } catch (InvalidMidiDataException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (MidiUnavailableException e) {
                    throw new RuntimeException(e);
                }
            }

            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
            MyPlayer=mediaPlayerFactory.newEmbeddedMediaPlayer();

            MyPlayer.prepareMedia(path);
            //System.out.println(MyPlayer.getLength());//需要startmedia才可以获取真正的length
            //可以播放rtsp MyPlayer.startMedia("rtsp://127.0.0.1:8554/");

            /*AudioMediaPlayerComponent a=new AudioMediaPlayerComponent();
            a.getMediaPlayer().startMedia(path);*/

            NameLabel.setText(playlist.get(NumberOfSongs).getName());
            //MyPlayer.startMedia(path);
            //VolumeController.setValue(0.5);
        }


    }

    public void RewindMethod(ActionEvent actionEvent) {
        double pos;
        if(isMidi){
            pos=midiPlayer.getMidiPositon();
        }else {
            pos = MyPlayer.getPosition();
        }
        pos-=0.025f;
        if(pos<0){
            pos=0;
        }
        //////////////////////////////////////test//////////////////////////////////////
        BeginTiming();
        if(isMidi){
            midiPlayer.setMidiPositon(pos);
        }else{
            MyPlayer.setPosition((float) pos);
        }
    }


    public void Play(ActionEvent actionEvent) throws InvalidMidiDataException, MidiUnavailableException, IOException {
        // TODO: test
        /////////////// for study //////////
        Thread t=Thread.currentThread();
        long id= t.threadId();
        System.out.println("play:"+id);
        if(!isRunning) {
            /*String path = playlist.get(NumberOfSongs).toURI().getPath();
            path = path.substring(1);
            path = path.replace("/", "\\");*/
            //System.out.println(path);

            //MyPlayer.startMedia(path);
            isRunning=true;
            BeginTiming();
            //如果是midi文件则new 一个新的midiplayer对象，并调用方法播放
            if (isMidi){
                midiPlayer.midiplay();
            }else{
                MyPlayer.play();
            }
            rt.play();  // 开始播放动画

        }
    }

    public void FastForwardMethod(ActionEvent actionEvent) {
        double pos;
        if (isMidi){
            pos=midiPlayer.getMidiPositon();
        }else{
            pos=MyPlayer.getPosition();
        }
        pos+=0.025f;
        if(pos>1){
            pos=1;
        }

        if(isMidi){
            midiPlayer.setMidiPositon(pos);
        }else{
            MyPlayer.setPosition((float) pos);
        }
    }

    public void PauseMethod(ActionEvent actionEvent) {
        if(isRunning) {
            if(isMidi){
                midiPlayer.midiPause();
            }else{
                MyPlayer.pause();
            }
            isRunning=false;
            //cancleTiming();
            rt.stop();
        }
    }

    public void NextMethod(ActionEvent actionEvent) throws InvalidMidiDataException, MidiUnavailableException, IOException {
        //如果正在播放，则关掉对应的播放器
        if(isRunning){
            isRunning=false;
            rt.stop();
            ///////////////////////////////////
            //如果正在播放midi文件，则停止该播放器
            if(isMidi){
                midiPlayer.stopMIDI();
                midiPlayer.setMidiPositon(0);
            }else {
                MyPlayer.stop();
                MyPlayer.setPosition(0);
            }

            cancleTiming();
        }

        if(NumberOfSongs<playlist.size()-1){
            NumberOfSongs+=1;

            String path=playlist.get(NumberOfSongs).getAbsolutePath();

            if(path.length()>2&&path.substring(path.length()-3).equals("mid")){
                isMidi=true;
                midiPlayer=new MIDIPlayer(new File(path));
            }else {
                isMidi=false;
                MyPlayer.prepareMedia(path);
            }
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
        }else{
            NumberOfSongs=0;

            String path=playlist.get(NumberOfSongs).getAbsolutePath();

            if(path.length()>2&&path.substring(path.length()-3).equals("mid")){
                isMidi=true;
                midiPlayer=new MIDIPlayer(new File(path));
            }else {
                isMidi=false;
                MyPlayer.prepareMedia(path);
            }
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
        }
    }

    public void Stop(ActionEvent actionEvent) {
        //System.out.println(MyPlayer.getMediaMeta());
        MyPlayer.stop();

        //mediaPlayerFactory.release();
        if(isRunning){
            cancleTiming();
            isRunning=false;
        }

        ProgressBar.setProgress(0);
        MyPlayer.release();

        System.exit(0);
    }

    public void BeginTiming(){
        // TODO: test
        /////////////// for study //////////
        Thread t=Thread.currentThread();
        long id= t.threadId();
        System.out.println("time:"+id);
        time=new Timer();
        task=new TimerTask() {
            public void run() {
                double pos;
                if (isMidi){
                    pos= midiPlayer.getMidiPositon();
                }else{
                    pos=MyPlayer.getPosition();
                }
                System.out.println(pos);
                if(pos>=0&&pos<1){
                    ProgressBar.setProgress(pos);
                }else{
                    cancleTiming();
                }
            }

        };
        //每秒(1000ms)一次run
        time.scheduleAtFixedRate(task,1000,1000);
    }

    public void cancleTiming() {
        isRunning=false;
        ProgressBar.setProgress(0);
        time.cancel();
    }

    public void Reset(ActionEvent actionEvent) {
        if(isMidi){
            midiPlayer.setMidiPositon(0);
        }else {
            MyPlayer.setPosition(0);
        }
        String path = playlist.get(NumberOfSongs).getAbsolutePath();
        //System.out.println(path);

        MyPlayer.startMedia(path);
        isRunning=true;
        BeginTiming();
        //MyPlayer.play();
        //rt.play();  // 开始播放动画
    }

    public void SwitchToPlayVideo(ActionEvent actionEvent) throws IOException {
        isRunning=false;
        rt.pause();
        if(isMidi){
            midiPlayer.midiPause();
        }else{
            MyPlayer.pause();
        }
         /*MyPlayer.stop();
         MyPlayer.setPosition(0);*/
         //MyPlayer.release();

         HelloApplication.CloseStage();
         TestVideo.videoPlay(null);
    }

    public void ChooseFilesMethod(ActionEvent actionEvent) throws InvalidMidiDataException, MidiUnavailableException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File f=fileChooser.showOpenDialog(HelloApplication.getStage());
        if(f!=null){
            if(f.getName().substring(f.getName().length()-3).equals("mid")){
                isMidi=true;
                //先关掉原来的
                midiPlayer.stopMIDI();
                midiPlayer=new MIDIPlayer(new File(f.getAbsolutePath()));
                midiPlayer.midiplay();
            }else{
                isMidi=false;
                MyPlayer.startMedia(f.getAbsolutePath());
            }
            //标为isRunning
            isRunning=true;
            //切好文件之后开始计时，因为切换之后得到的pos是不正常的数值，会直接canceltiming，所以要重新开始计时才会有进度条显示
            BeginTiming();
            NameLabel.setText(f.getName());

        }
    }

    public void ChangeProgress(MouseEvent mouseEvent) {
        if(isMidi){
            midiPlayer.setMidiPositon((float)(mouseEvent.getX()/ProgressBar.getWidth()));
        }else{
            MyPlayer.setPosition((float)(mouseEvent.getX()/ProgressBar.getWidth()));
        }
    }
    /**** volume control
    public void ChangeVolume(MouseEvent mouseEvent) {
        System.out.println(MyPlayer.getVolume());
        MyPlayer.setVolume(200);
        System.out.println(MyPlayer.getVolume());
    }*************/
}