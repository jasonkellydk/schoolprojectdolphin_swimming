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
import Seeds.*;

public class Bootstrap extends Application{

    private static Stage stage;

    public static Stage getstage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Base is the database connection
        Base.open();


        /*
         * This is the seeder classes (run when application database is empty)
         */
        /*roleTableSeeder roleSeed = new roleTableSeeder();
        roleSeed.Seed();

        userTableSeeder userSeed = new userTableSeeder();
        userSeed.Seed();
        */

        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getClassLoader().getResource("View/login.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dolphin Swimming");
        stage.toFront();
        stage.sizeToScene();
        stage.show();

        Bootstrap.stage = stage;
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
