package Controller;


import Enligthen.Auth;
import Enligthen.Validation;
import Model.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * Created by jasonkelly on 24/11/2016.
 */
public class MemberController {

    public JFXTextField memberName;
    public JFXTextField memberEmail;
    public JFXPasswordField memberPassword;
    public JFXPasswordField memberRepeatPassword;
    public VBox membersVbox;

    public void initialize() throws Exception {

        GetMembers();

    }

    public void GetMembers() throws Exception{
        final Task<List> data = getData();

        data.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                membersVbox.getChildren().clear();
                // This handler will be called if Task succesfully executed login code
                // disregarding result of login operation

                // and here we act according to result of login code
                List <User> users = (List<User>) data.getValue();

                for (Model user:users
                        ) {
                    Label labels = new Label();
                    labels.setText(user.get("name").toString());
                    membersVbox.getChildren().add(labels);
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
            protected List<User> call() {
                Base.open();
                List <User> users = User.findAll();
                return users;
            }
        };
    }

    @FXML
    public boolean create(ActionEvent actionEvent) throws Exception {


        /*
         * Run the validation class and validate the input
         */
        Validation validator = new Validation();
        if(!validator.vEmail(memberEmail.getText())){return false;}
        if(!validator.vMinLength(memberName.getText(),4)){return false;}
        if(!validator.vPassword(memberPassword.getText(),memberRepeatPassword.getText())){return false;}

        /*
         * Encrypt the users password using bcrypt
         */
        String hashed = BCrypt.hashpw(memberPassword.getText(), BCrypt.gensalt());
        User p = new User();
        p.set("name", memberName.getText());
        p.set("email", memberEmail.getText());
        p.set("password", hashed);
        p.set("role_id", "1");
        p.saveIt();
        GetMembers();
        return true;
    }
}
