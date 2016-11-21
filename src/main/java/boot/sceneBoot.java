package boot;

import insidefx.undecorator.UndecoratorScene;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.javalite.activejdbc.Base;

/**
 * Created by jasonkelly on 18/11/2016.
 */
public class sceneBoot {

public void start(Stage stage, String Resource) throws Exception{
    Base.open();
     /*
     * Final so that the variable cannot be assigned to something else
     */
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(Resource));
    Region root = fxmlLoader.load();
   // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    final UndecoratorScene undecoratorScene = new UndecoratorScene(stage, root);
    undecoratorScene.addStylesheet("/css/window_style.css");
    stage.setScene(undecoratorScene);
    stage.toFront();
    stage.sizeToScene();
    stage.show();

    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(javafx.stage.WindowEvent event) {
            System.exit(0); // exit program when custom exit button is pressed
        }
    });



    //  stage.getIcons().add(new javafx.scene.image.Image("icons/Dolphin.png"));
    // stage.setTitle("Dolphin Swimming login");
    // stage.initStyle(StageStyle.TRANSPARENT);
    // stage.setScene(scene);
    //  stage.show();

}

}
