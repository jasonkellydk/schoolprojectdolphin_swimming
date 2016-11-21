package Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jasonkelly on 20/11/2016.
 */
public class HomeController {


    // root element of home.fxml, injected as usual with fx:id="root"
    @FXML
    private BorderPane root;

    public void initialize() throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/menu.fxml"));
        HBox userSubMenu = (HBox) loader.load();
        root.setTop(userSubMenu);

        }
    }
