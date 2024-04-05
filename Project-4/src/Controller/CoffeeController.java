package Controller;
import Model.Coffee;
import Model.Order;
import Model.Sandwich;
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

    /**
     * Initializes a new coffee order
     */
    public CoffeeController() {
        this.currentOrder = Order.getInstance();
    }

    @FXML
    private Spinner<Integer> addonsSpinner;

    /**
     * Updates subtotal when an add on is selected on the GUI
     */
    @FXML
    private void onAddonsSpinnerClicked() {
        updateSubtotal();
    }
    /**
     * Initializes the coffee page in the GUI when selected from the home page
     */
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


    /**
     * Counts the number of add ons that are selected on the GUI
     * @return the number of add ons selected
     */
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
    /**
     * Uses logic to update the subtotal of the current coffee order
     */
    private void updateSubtotal() {
        String selectedSize = cupSizeComboBox.getValue();
        int addonsCount = countSelectedAddons();
        double sizePriceIncrease = Coffee.calculateSizePriceIncrease(selectedSize);
        double oneCoffeeSubtotal = Coffee.getBasePrice() + sizePriceIncrease + addonsCount * Coffee.ADDON_PRICE;
        int quantity = addonsSpinner.getValue();
        double totalSubtotal = oneCoffeeSubtotal * quantity;

        subtotalTextField.setText(String.format("$%.2f", totalSubtotal));
    }
    /**
     * Updates the subtotal when any checkbox is toggled in the GUI
     */
    @FXML
    private void onCheckboxAction()
    {
        updateSubtotal(); // Update the subtotal when any checkbox is toggled
    }




    /*==BACK=BUTTON===============================================================================================*/

    @FXML
    private ImageView backImageView;
    /**
     * Loads the previous page when the back button is selected in the GUI
     */
    @FXML
    private void onBackImageViewClicked() {
        loadView("/Views/HomePageView.fxml");
    }

    /**
     * Logic for loading the back page in the GUI
     */
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

    /*==AddToOrder=BUTTON===============================================================================================*/

    @FXML
    private Button addToOrderButton;

    public void setOrder() {
        this.currentOrder = Order.getInstance();
    }

    private CurrentOrderController currentOrderController;

    public void setCurrentOrderController(CurrentOrderController controller) {
        this.currentOrderController = controller;
    }
    /**
     * Adds the current coffee to the order when the add button is selected on th GUI
     */
    @FXML
    private void onAddToOrderButtonClicked() {
        setOrder();
        updateSubtotal();
        int addonsCount = addonsSpinner.getValue();
        Coffee coffee = new Coffee(cupSizeComboBox.getValue(), countSelectedAddons());
        currentOrder.addItem(coffee);
        updateSubtotal();
    }

}