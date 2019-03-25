package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //log in.fxml
        //sample_AR.fxml
        Parent root = FXMLLoader.load(getClass().getResource("log in.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("sample_AR.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("sample_EN.fxml"));

        primaryStage.setTitle("إدارة مركز صيانة");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}