package com.example.signature;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.AllUtilsForSIg.RSAUtils;
import com.example.AllUtilsForSIg.HashUtils;
import com.example.AllUtilsForSIg.AesUtils;

import static com.example.AllUtilsForSIg.RSAUtils.*;

public class SigController implements initializable{

    @FXML
    private TextArea textForM;
    @FXML
    private Button ChooseOriginalFileButton,SigButton;
    @FXML
    private TextField FilePath1;
    @FXML
    private Label welcomeText;

    private File f_plaintext;
    private String plaintext;
    private RSAUtils.Keys k;
    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       //初始化时为用户分配密钥
        BigInteger[] pq = getRandomPQ();
        k = generateKey(pq[0], pq[1]);
    }

     */

    public void ChooseOriginalFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        f_plaintext=fileChooser.showOpenDialog(HelloApplication.retStage());
        if(f_plaintext!=null){
            FilePath1.setText(f_plaintext.getAbsolutePath());
        }

    }

    public void Sig(ActionEvent actionEvent) {

        if(FilePath1.getText()==null){
            plaintext = textForM.getText();
        }
        if(plaintext!=null){
            String hash=HashUtils.getSha(plaintext);
            BigInteger sig=RSA_signature(new BigInteger(hash,16),k.getPrivateKey());
            System.out.println(sig.toString(16));

        }
    }
}