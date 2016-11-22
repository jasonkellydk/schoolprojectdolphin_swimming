package Controller;

import Enligthen.Auth;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by jasonkelly on 15/11/2016.
 */
public class LoginController {

    public Text invalidCredentials;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField userPassword;


    @FXML
    private void Submit(ActionEvent event) throws Exception
    {

        Auth auth = new Auth();

        String email = userName.getText();
        String password = userPassword.getText();

        boolean authenticated = auth.Login(email,password);

        if(authenticated){
            System.out.println("The user has been authenticated");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/home.fxml"));
            BorderPane login = loader.load();
            Scene scene = new Scene(login);
            stage.setScene(scene);
            stage.setFullScreen(stage.isFullScreen());

        }

        if(!authenticated){
            invalidCredentials.setText("Your credentials dosen´t match!!");
        }

    }


}
