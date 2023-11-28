package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import javafx.util.Duration;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.*;



public class MediaController implements Initializable {

    @FXML
    private Label NameLabel;
    @FXML
    private Button NextButton,PlayButton,FastForwardButton,PauseButton,SelectFilesButton,RewindButton,StopButton,ResetButton;
    @FXML
    private Circle RotatingCircle;
    @FXML
    private AnchorPane PaneForCircle;
    @FXML
    private ProgressBar ProgressBar;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 创建一个旋转的过渡动画，构建方法中，第一个参数指定完成一次旋转所需要的时间，第二个参数是旋转的对象
        rt = new RotateTransition(Duration.seconds(7), PaneForCircle);

        rt.setByAngle(360);  // 设置旋转的角度

        rt.setCycleCount(Animation.INDEFINITE);  // 设置旋转次数，我们需要旋转无数次

        rt.setInterpolator(Interpolator.LINEAR);  // 控制每个过渡周期的加速和减速时间，设置为匀速

        ProgressBar.setStyle("-fx-accent:#2e4565;");


        playlist=new ArrayList<File>();
        directory=new File( "music");
        isRunning=false;
        files=directory.listFiles();
        if(files!=null){
            playlist.addAll(Arrays.asList(files));

            String path=playlist.get(NumberOfSongs).toURI().getPath();
            path=path.substring(1);
            path=path.replace("/","\\");
            System.out.println(path);


            MediaPlayerFactory mediaPlayerFactory =
                    new MediaPlayerFactory();
            MyPlayer=mediaPlayerFactory.newEmbeddedMediaPlayer();

            MyPlayer.prepareMedia(path);


            NameLabel.setText(playlist.get(NumberOfSongs).getName());
            //MyPlayer.startMedia(path);
        }


    }

    public void RewindMethod(ActionEvent actionEvent) {
        float pos=MyPlayer.getPosition();
        pos-=0.025f;
        if(pos<0){
            pos=0;
        }
        //////////////////////////////////////test//////////////////////////////////////
        BeginTiming();
        MyPlayer.setPosition(pos);
    }


    public void Play(ActionEvent actionEvent) {
        if(!isRunning) {
            /*String path = playlist.get(NumberOfSongs).toURI().getPath();
            path = path.substring(1);
            path = path.replace("/", "\\");*/
            //System.out.println(path);

            //MyPlayer.startMedia(path);
            isRunning=true;
            BeginTiming();
            MyPlayer.play();
            rt.play();  // 开始播放动画

        }
    }

    public void FastForwardMethod(ActionEvent actionEvent) {
        float pos=MyPlayer.getPosition();
        pos+=0.025f;
        if(pos>1){
            pos=1;
        }

        MyPlayer.setPosition(pos);
    }

    public void PauseMethod(ActionEvent actionEvent) {
        if(isRunning) {
            MyPlayer.pause();
            isRunning=false;
            //cancleTiming();
            rt.stop();
        }
    }

    public void NextMethod(ActionEvent actionEvent) {
        if(NumberOfSongs<playlist.size()-1){
            NumberOfSongs+=1;
            MyPlayer.stop();
            if(isRunning){
                isRunning=false;
                rt.stop();
                ///////////////////////////////////
                MyPlayer.setPosition(0);
                cancleTiming();
            }


            String path=playlist.get(NumberOfSongs).toURI().getPath();
            path=path.substring(1);
            path=path.replace("/","\\");
            System.out.println(path);

            if(path.contains("mp4")){

            }
            MyPlayer.prepareMedia(path);
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
        }else{
            NumberOfSongs=0;
            MyPlayer.stop();
            if(isRunning){
                isRunning=false;
                rt.stop();
                ///////////////////////////////////
                MyPlayer.setPosition(0);
                cancleTiming();
            }

            String path=playlist.get(NumberOfSongs).toURI().getPath();
            path=path.substring(1);
            path=path.replace("/","\\");
            System.out.println(path);

            MyPlayer.prepareMedia(path);
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
        time=new Timer();
        task=new TimerTask() {
            public void run() {
                double pos=MyPlayer.getPosition();
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
        MyPlayer.setPosition(0);
        String path = playlist.get(NumberOfSongs).toURI().getPath();
        path = path.substring(1);
        path = path.replace("/", "\\");
        //System.out.println(path);

        MyPlayer.startMedia(path);
        isRunning=true;
        BeginTiming();
        //MyPlayer.play();
        //rt.play();  // 开始播放动画
    }
}