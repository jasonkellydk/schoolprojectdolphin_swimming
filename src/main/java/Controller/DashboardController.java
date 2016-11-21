package Controller;

import Enligthen.Auth;
import boot.sceneBoot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Created by jasonkelly on 17/11/2016.
 */
public class DashboardController {


    @FXML
    public Label loggedInUser;


    @FXML
    private void Logout(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneBoot sceneBoot = new sceneBoot();
        sceneBoot.start(stage, "View/login.fxml");
    }

}
