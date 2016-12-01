package Controller;

import Enligthen.Auth;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;


import Enligthen.Filesystem;
import com.jfoenix.controls.*;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.javalite.activejdbc.Base;


import java.io.IOException;



import java.util.Properties;



/**
 * Created by jasonkelly on 15/11/2016.
 */
public class LoginController {

    public Text invalidCredentials;
    public JFXButton submit;
    public JFXSpinner loginLoader;
    public JFXCheckBox rememberMe;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField userPassword;

    private String email;
    private String password;

    private Boolean authenticated;



    public void initialize() throws Exception {
        Filesystem filesystem = new Filesystem();
        Properties prop = filesystem.Config();
        Boolean rememberMeValue = Boolean.parseBoolean(prop.getProperty("ALLOW_REMEMBER"));
        rememberMe.setVisible(rememberMeValue);

    }


    @FXML
    private void Submit(ActionEvent event) throws Exception
    {

        submit.setText("");
        loginLoader.setVisible(true);
        submit.setDisable(true);

        this.email = userName.getText();
        this.password = userPassword.getText();

        //https://codedump.io/share/7vH4CuuUY34/1/javafx-2-background-and-platformrunlater-vs-taskservice
        final Task<Boolean> data = getData();

        data.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                // This handler will be called if Task succesfully executed login code
                // disregarding result of login operation

                // and here we act according to result of login code
                authenticated = data.getValue();

                if(authenticated){
                    System.out.println("The user has been authenticated");
                    // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    goToHome();
                }

                if(!authenticated){
                    invalidCredentials.setText("Your credentials dosen´t match!!");
                    loginLoader.setVisible(false);
                    submit.setText("Login");
                    submit.setDisable(false);
                }

            }
        });


        Thread t = new Thread(data);
        t.setDaemon(true); // thread will not prevent application shutdown
        t.start();




    }

    private Task<Boolean> getData(){
        return new Task<Boolean>() {
            @Override
            protected Boolean call() {
                Base.open();
                Auth auth = new Auth();
                return auth.Login(email, password);
            }
        };
    }


    private void goToHome(){
        Stage stage = boot.Bootstrap.getstage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/home.fxml"));
            BorderPane login = null;
            login = loader.load();
            Scene scene = new Scene(login);
            Boolean fullscreen = stage.isFullScreen() ? true : false;
                    /*
                     * It seems like theres a bug in javafx for Mac that allows the fullscreen mode to flash
                     * https://bugs.openjdk.java.net/browse/JDK-8089209
                     */
            //stage.setFullScreen(false);
            stage.setScene(scene);
            stage.setFullScreen(fullscreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
