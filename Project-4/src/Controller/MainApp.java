package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomePageController.class.getResource("/Views/HomePageView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 425);

            stage.setTitle("RU Cafe");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}