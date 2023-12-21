package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;
    @Override
    public  void start(Stage Primarystage) throws IOException {
        /////////////// for study //////////
        Thread t=Thread.currentThread();
        long id= t.threadId();
        System.out.println("start:"+id);
        stage=Primarystage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Mul-MediaPlayer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 720);
        stage.setTitle("Mul-MediaPlayer");
        stage.setScene(scene);
        stage.show();

        //设置监听窗口关闭
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                /////////////// for study //////////
                Thread t=Thread.currentThread();
                long id= t.threadId();
                System.out.println("tuichu:"+id);
                System.exit(0);
            }
        });


    }

    public static void main(String[] args) {
        /////////////// for study //////////
        Thread t=Thread.currentThread();
        long id= t.threadId();
        System.out.println("main:"+id);

        launch();

    }
    public static void CloseStage(){
        Platform.setImplicitExit(false);
        stage.hide();
    }
    public static void ReloadStage() throws IOException {
        stage.show();
    }
    public static Stage getStage(){
        return stage;
    }
}