package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.ImageView;

public class HomePageController {

    @FXML
    private ImageView coffeeOrderImageView;
    @FXML
    private ImageView donutOrderImageView;
    @FXML
    private ImageView sandwichOrderImageView;
    @FXML
    private ImageView currentOrderImageView;
    @FXML
    private ImageView orderHistoryImageView;

    @FXML
    public void initialize() {}

    @FXML
    private void onCoffeeOrderClicked() {
        loadView("/Views/CoffeeOrderView.fxml");
    }

    @FXML
    private void onDonutOrderClicked() {
        loadView("/Views/DonutOrderView.fxml");
    }

    @FXML
    private void onSandwichOrderClicked() {
        loadView("/Views/SandwichOrderView.fxml");
    }

    @FXML
    private void onCurrentOrderClicked() {
        loadView("/Views/CurrentOrderView.fxml");
    }

    @FXML
    private void onOrderHistoryClicked() {
        loadView("/Views/AllOrdersView.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Object root = loader.load();
            Stage stage = (Stage) coffeeOrderImageView.getScene().getWindow(); // Use any image view here
            stage.setScene(new Scene((Parent) root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Here you could log the error or display an alert to the user
        }
    }

}