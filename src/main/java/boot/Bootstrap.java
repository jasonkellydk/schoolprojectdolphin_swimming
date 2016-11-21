package boot; /**
 * Created by jasonkelly on 14/11/2016.
 */

import Controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.javalite.activejdbc.Base;


public class Bootstrap extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        //Base is the database connection
        Base.open();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getClassLoader().getResource("View/login.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dolphin Swimming");
        stage.toFront();
        stage.sizeToScene();
        stage.show();


        //http://stackoverflow.com/questions/23627340/login-application-with-1-stage-and-multiple-scene-in-javafx

    }

    public static void main(String[] args) throws Exception{

        /*
         * @param args
         * @return Login instance
         */

        launch(args);

    }







}
