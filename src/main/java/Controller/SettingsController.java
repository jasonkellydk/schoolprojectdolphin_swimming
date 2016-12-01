package Controller;

import Enligthen.Auth;
import Enligthen.Filesystem;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by jasonkelly on 24/11/2016.
 */
public class SettingsController {

    public JFXCheckBox rememberSetting;
    public JFXTextField memberFee;
    public Text saveMessage;
    public Text currency;

    public void initialize() throws Exception {
        Filesystem filesystem = new Filesystem();
        Properties prop = filesystem.Config();
        Boolean rememberMeValue = Boolean.parseBoolean(prop.getProperty("ALLOW_REMEMBER"));
        String memberFeeValue = prop.getProperty("MEMBER_FEE");
        String currencyValue = prop.getProperty("MEMBER_CURRENCY");
        currency.setText(currencyValue);
        memberFee.setText(memberFeeValue);
        rememberSetting.setSelected(rememberMeValue);


    }

    @FXML
    public void update(ActionEvent actionEvent) throws IOException{
        Filesystem filesystem = new Filesystem();
        Properties prop = filesystem.Config();
        Boolean rm = rememberSetting.isSelected();
        prop.setProperty("ALLOW_REMEMBER", rm.toString());
        prop.setProperty("MEMBER_FEE", memberFee.getText());
        prop.store(filesystem.ConfigPath(),null);
        System.out.println("Opdateret filen");
        saveMessage.setVisible(true);
    }
}
