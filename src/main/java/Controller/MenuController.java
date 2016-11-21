package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jasonkelly on 20/11/2016.
 */
public class MenuController {

    public JFXButton Members;

    @FXML
    void switchMembers(ActionEvent event) {

      try {

          FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/members.fxml"));
          AnchorPane scene = (AnchorPane) loader.load();
          BorderPane border = (BorderPane) ((Node) scene.getParent());
          //BorderPane border = (BorderPane) HomeController.class.getClassLoader().getResources("View/Home.fxml");
          border.setCenter(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
