package Controller;
import Model.Coffee;
import Model.Order;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CoffeeController
{
    @FXML
    private ComboBox<String> cupSizeComboBox;
    @FXML
    private Spinner<Integer> addonsSpinner;
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
    public void initialize()
    {
        cupSizeComboBox.getItems().addAll("SHORT", "TALL", "GRANDE", "VENTI");
        cupSizeComboBox.getSelectionModel().selectFirst(); // Select "SHORT" by default

        addonsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));

        cupSizeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());
        addonsSpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateSubtotal());

        updateSubtotal(); // Update the subtotal on initialization
    }

    @FXML
    private void onAddToOrderClicked() {
        // Construct a Coffee object and add it to the current order
        Coffee coffee = new Coffee(cupSizeComboBox.getValue(), countSelectedAddons());
        currentOrder.addItem(coffee);
        updateSubtotal(); // Update the subtotal
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

    private void updateSubtotal()
    {
        String selectedSize = cupSizeComboBox.getValue();
        int addonsCount = countSelectedAddons();

        // Use the Coffee class to calculate price
        double sizePriceIncrease = Coffee.calculateSizePriceIncrease(selectedSize);
        double subtotal = Coffee.getBasePrice() + sizePriceIncrease + addonsCount * Coffee.ADDON_PRICE;

        subtotalTextField.setText(String.format("$%.2f", subtotal));
    }

    @FXML
    private void onCheckboxAction()
    {
        updateSubtotal(); // Update the subtotal when any checkbox is toggled
    }
}