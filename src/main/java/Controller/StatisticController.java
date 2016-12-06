package Controller;


import Enligthen.Auth;
import Enligthen.Validation;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.mindrot.jbcrypt.BCrypt;
import Model.Diciplin;
import java.util.List;

/**
 * Created by jasonkelly on 24/11/2016.
 */
public class StatisticController {


    public HBox Hboxdiciplins;
    public HBox HboxUsers;

    public void initialize() throws Exception {

        GetDiciplins();
        GetUsers();

    }

    public void GetDiciplins() throws Exception{
        final Task<List> data = getData();

        data.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Hboxdiciplins.getChildren().clear();
                List <Diciplin> diciplins = (List<Diciplin>) data.getValue();

                ToggleGroup group = new ToggleGroup();

                for (Model diciplin:diciplins
                        ) {
                    JFXRadioButton javaRadio = new JFXRadioButton(diciplin.get("title").toString());
                    javaRadio.setPadding(new Insets(5));
                    Hboxdiciplins.getChildren().add(javaRadio);
                    javaRadio.setToggleGroup(group);
                }


            }
        });

        Thread t = new Thread(data);
        t.setDaemon(true); // thread will not prevent application shutdown
        t.start();
    }
    private Task<List> getData(){
        return new Task<List>() {
            @Override
            protected List<Diciplin> call() {
                Base.open();
                List <Diciplin> diciplin = Diciplin.findAll();
                return diciplin;
            }
        };
    }


    public void GetUsers() throws Exception{
        final Task<List> data = getUserData();

        data.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                HboxUsers.getChildren().clear();
                List <User> users = (List<User>) data.getValue();

                ToggleGroup group = new ToggleGroup();

                for (Model user:users
                        ) {
                    JFXRadioButton javaRadio = new JFXRadioButton(user.get("name").toString()+" ("+user.get("id").toString()+")");
                    javaRadio.setPadding(new Insets(5));
                    HboxUsers.getChildren().add(javaRadio);
                    javaRadio.setToggleGroup(group);
                }


            }
        });

        Thread t = new Thread(data);
        t.setDaemon(true); // thread will not prevent application shutdown
        t.start();
    }


    private Task<List> getUserData(){
        return new Task<List>() {
            @Override
            protected List<User> call() {
                Base.open();
                List <User> users = User.findAll();
                return users;
            }
        };
    }


}
