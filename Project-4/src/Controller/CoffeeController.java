package Controller;
import Model.Coffee;
import Model.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CoffeeController
{
    @FXML
    private ComboBox<String> cupSizeComboBox;
    @FXML
    private TextField subtotalTextField;
    @FXML
    private CheckBox frenchVanillaCheckBox;
    @FXML
    private CheckBox irishCreamCheckBox;
    @FXML
    private CheckBox mochaCheckBox;
    @FXML
    private CheckBox sweetCreamCheckBox;
    @FXML
    private CheckBox caramelCheckBox;

    private Order currentOrder;

    public CoffeeController()
    {
        currentOrder = new Order();
    }

    @FXML
    private Spinner<Integer> addonsSpinner;
    @FXML
    private void onAddonsSpinnerClicked() {
        updateSubtotal();
    }

    @FXML
    public void initialize()
    {
        subtotalTextField.setEditable(false);
        cupSizeComboBox.getItems().addAll("SHORT", "TALL", "GRANDE", "VENTI");
        cupSizeComboBox.getSelectionModel().selectFirst(); // Select "SHORT" by default

        addonsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));

        cupSizeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());
        addonsSpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());

        updateSubtotal();
    }

    @FXML
    private void onAddToOrderClicked()
    {
        int addonsCount = addonsSpinner.getValue();
        Coffee coffee = new Coffee(cupSizeComboBox.getValue(), countSelectedAddons());
        currentOrder.addItem(coffee);
        updateSubtotal();
    }

    private int countSelectedAddons()
    {
        int count = 0;
        if (frenchVanillaCheckBox.isSelected()) count++;
        if (irishCreamCheckBox.isSelected()) count++;
        if (mochaCheckBox.isSelected()) count++;
        if (sweetCreamCheckBox.isSelected()) count++;
        if (caramelCheckBox.isSelected()) count++;
        return count;
    }

    private void updateSubtotal() {
        String selectedSize = cupSizeComboBox.getValue();
        int addonsCount = countSelectedAddons();
        double sizePriceIncrease = Coffee.calculateSizePriceIncrease(selectedSize);
        double oneCoffeeSubtotal = Coffee.getBasePrice() + sizePriceIncrease + addonsCount * Coffee.ADDON_PRICE;
        int quantity = addonsSpinner.getValue();
        double totalSubtotal = oneCoffeeSubtotal * quantity;

        subtotalTextField.setText(String.format("$%.2f", totalSubtotal));
    }

    @FXML
    private void onCheckboxAction()
    {
        updateSubtotal(); // Update the subtotal when any checkbox is toggled
    }




    /*==BACK=BUTTON===============================================================================================*/

    @FXML
    private ImageView backImageView;

    @FXML
    private void onBackImageViewClicked() {
        loadView("/Views/HomePageView.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Object root = loader.load();
            Stage stage = (Stage) backImageView.getScene().getWindow();
            stage.setScene(new Scene((Parent) root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();}
    }
}