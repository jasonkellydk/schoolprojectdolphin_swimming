package Controller;


import Enligthen.Auth;
import Enligthen.Validation;
import Model.News;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * Created by jasonkelly on 24/11/2016.
 */
public class NewsController {


    public VBox newsVbox;
 
    public JFXTextField newsHeading;
    public JFXTextArea newsBody;

    public void initialize() throws Exception {
        GetNews();
    }

    public void GetNews() throws Exception{
        final Task<List> data = getData();

        data.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                newsVbox.getChildren().clear();
                // This handler will be called if Task succesfully executed login code

                List <News> Allnews = (List<News>) data.getValue();

                for (Model news:Allnews
                        ) {
                    Label labels = new Label();
                    labels.setText(news.get("heading").toString());
                    Label texts = new Label();
                    texts.setText(news.get("body").toString());
                    newsVbox.getChildren().add(labels);
                    newsVbox.getChildren().add(texts);
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
            protected List<News> call() {
                Base.open();
                List <News> news = News.findAll();
                return news;
            }
        };
    }

    @FXML
    public boolean create(ActionEvent actionEvent) throws Exception {

        /*
         * Run the validation class and validate the input
         */
        Validation validator = new Validation();

        if(!validator.vMinLength(newsHeading.getText(),4)){return false;}
        if(!validator.vMinLength(newsBody.getText(),4)){return false;}

        News p = new News();
        p.set("heading", newsHeading.getText());
        p.set("body", newsBody.getText());
        p.set("user_id", Auth.user().getInteger("id"));
        p.saveIt();
        /*
         * Reload the list and clear values
         */
        GetNews();
        newsBody.setText("");
        newsHeading.setText("");

        return true;
    }
}
