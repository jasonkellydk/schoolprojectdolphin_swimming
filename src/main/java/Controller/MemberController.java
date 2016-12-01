package Controller;


import Enligthen.Validation;
import Model.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by jasonkelly on 24/11/2016.
 */
public class MemberController {

    public JFXTextField memberName;
    public JFXTextField memberEmail;
    public JFXPasswordField memberPassword;
    public JFXPasswordField memberRepeatPassword;

    public void initialize() throws Exception {

    }


    @FXML
    public boolean create(ActionEvent actionEvent) {


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

        return true;
    }
}
