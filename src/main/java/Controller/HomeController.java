package Controller;

import Enligthen.Auth;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by jasonkelly on 20/11/2016.
 */
public class HomeController {


    public JFXButton Members;
    public JFXButton Statistic;
    public JFXButton Settings;

    // root element of home.fxml, injected as usual with fx:id="rootElement"
    public BorderPane rootElement;
    public Label loggedInUser;
    public JFXButton News;


    /**
     * Runs at the init of the class
     * @throws Exception
     */
    public void initialize() throws Exception {

        if(Auth.user().getInteger("role_id") != 1){
            Settings.setVisible(false);
        }

        loggedInUser.setText(Auth.user().getString("name"));

        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getClassLoader().getResource("View/pages/news.fxml"));
        AnchorPane view = fxmlLoader.load();
        rootElement.setCenter(view);


    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void handleLogout(ActionEvent actionEvent) throws IOException{
        Auth auth = new Auth();
        auth.Logout();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getClassLoader().getResource("View/login.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = boot.Bootstrap.getstage();

        Boolean fullscreen = stage.isFullScreen() ? true : false;
            /*
             * It seems like theres a bug in javafx for Mac that allows the fullscreen mode to flash
             * https://bugs.openjdk.java.net/browse/JDK-8089209
             */
        stage.setFullScreen(false);
        stage.setScene(scene);
        stage.setFullScreen(fullscreen);
    }

    public void handleMenuAction(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == Members) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/pages/members.fxml"));
            AnchorPane view = loader.load();
            rootElement.setCenter(view);
        }
        if(actionEvent.getSource() == Statistic)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/pages/statistic.fxml"));
            AnchorPane view = loader.load();
            rootElement.setCenter(view);
        }
        if(actionEvent.getSource() == Settings)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/pages/settings.fxml"));
            AnchorPane view = loader.load();
            rootElement.setCenter(view);
        }
        if(actionEvent.getSource() == News)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/pages/news.fxml"));
            AnchorPane view = loader.load();
            rootElement.setCenter(view);
        }

    }
}


