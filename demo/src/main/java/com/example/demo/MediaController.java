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
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;



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
    private MenuButton PlayListShowButton;
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
            //cancleTiming();
            isRunning=true;
            BeginTiming();
            NumberOfSongs= PlayListShowButton.getItems().indexOf((MenuItem)e.getSource());
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
            MyPlayer.startMedia(playlist.get(NumberOfSongs).getAbsolutePath());

            rt.play();  // 开始播放动画
            /***
            NumberOfSongs=SongMenu.indexOf((MenuItem)e.getSource());
            NameLabel.setText(playlist.get(NumberOfSongs).getName());
            MyPlayer.startMedia(playlist.get(NumberOfSongs).getAbsolutePath());***/
        }
    };
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
            for(File f:files){
                playlist.add(f);
                MenuItem m=new MenuItem(f.getName());
                //SongMenu.add(m);
                m.setOnAction(event1);
                PlayListShowButton.getItems().add(m);
            }
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
            //VolumeController.setValue(0.5);
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

    public void SwitchToPlayVideo(ActionEvent actionEvent) throws IOException {
         MyPlayer.stop();
         MyPlayer.setPosition(0);
         //MyPlayer.release();

         HelloApplication.CloseStage();
         TestVideo.videoPlay(null);
    }

    public void ChooseFilesMethod(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File f=fileChooser.showOpenDialog(HelloApplication.getStage());
        if(f!=null){
            MyPlayer.startMedia(f.getAbsolutePath());
            System.out.println(MyPlayer.isPlaying());
        }
    }

    public void ChangeProgress(MouseEvent mouseEvent) {
        MyPlayer.setPosition((float)(mouseEvent.getX()/ProgressBar.getWidth()));
    }
    /**** volume control
    public void ChangeVolume(MouseEvent mouseEvent) {
        System.out.println(MyPlayer.getVolume());
        MyPlayer.setVolume(200);
        System.out.println(MyPlayer.getVolume());
    }*************/
}