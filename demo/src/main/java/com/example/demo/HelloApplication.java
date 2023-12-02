package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;
    @Override
    public  void start(Stage Primarystage) throws IOException {
        stage=Primarystage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Mul-MediaPlayer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 720);
        stage.setTitle("Mul-MediaPlayer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
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