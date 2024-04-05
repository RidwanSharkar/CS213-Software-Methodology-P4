package Controller;

import Model.Sandwich;
import Model.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SandwichController
{
    @FXML
    private RadioButton bagelRadioButton;
    @FXML
    private RadioButton wheatToastRadioButton;
    @FXML
    private RadioButton sourDoughRadioButton;
    @FXML
    private RadioButton beefRadioButton;
    @FXML
    private RadioButton fishRadioButton;
    @FXML
    private RadioButton chickenRadioButton;
    @FXML
    private CheckBox lettuceCheckBox;
    @FXML
    private CheckBox tomatoCheckBox;
    @FXML
    private CheckBox cheeseCheckBox;
    @FXML
    private CheckBox onionCheckBox;
    @FXML
    private TextField subtotalTextField;
    @FXML


    private ToggleGroup meatToggleGroup;
    private ToggleGroup breadToggleGroup;
    private Order currentOrder;

    private Button addToOrderButton;

    public SandwichController() {
        currentOrder = new Order(); // Initialize a new order
    }

    @FXML
    public void initialize()
    {
        subtotalTextField.setEditable(false);
        meatToggleGroup = new ToggleGroup();
        breadToggleGroup = new ToggleGroup();

        // Add meat choice RadioButtons to their ToggleGroup
        beefRadioButton.setToggleGroup(meatToggleGroup);
        fishRadioButton.setToggleGroup(meatToggleGroup);
        chickenRadioButton.setToggleGroup(meatToggleGroup);

        // Add bread choice RadioButtons to their ToggleGroup
        bagelRadioButton.setToggleGroup(breadToggleGroup);
        wheatToastRadioButton.setToggleGroup(breadToggleGroup);
        sourDoughRadioButton.setToggleGroup(breadToggleGroup);

        beefRadioButton.setOnAction(e -> updateSubtotal());
        fishRadioButton.setOnAction(e -> updateSubtotal());
        chickenRadioButton.setOnAction(e -> updateSubtotal());
        lettuceCheckBox.setOnAction(e -> updateSubtotal());
        tomatoCheckBox.setOnAction(e -> updateSubtotal());
        cheeseCheckBox.setOnAction(e -> updateSubtotal());
        onionCheckBox.setOnAction(e -> updateSubtotal());
        backImageView.setOnMouseClicked(event -> onBackImageViewClicked());
    }
    @FXML
    private void onBreadSelection() {
        updateSubtotal();
    }

    @FXML
    private void onProteinSelection() {
        updateSubtotal();
    }

    @FXML
    private void onAddOnSelection() {
        updateSubtotal();
    }
    private void updateSubtotal() {
        Sandwich tempSandwich = createTempSandwich();
        double subtotal = 0.00;
        if (tempSandwich != null) {
            subtotal = tempSandwich.price();
        }
        subtotalTextField.setText(String.format("$%.2f", subtotal));
    }

    private Sandwich createTempSandwich() {
        String meatChoice = getSelectedMeatChoice();
        if (meatChoice == null) {
            return null;
        }
        String breadChoice = getSelectedBreadChoice();
        List<String> addOnsList = getSelectedAddOns();

        return new Sandwich(meatChoice, breadChoice, addOnsList);
    }

    private String getSelectedMeatChoice() {
        if (beefRadioButton.isSelected()) return Sandwich.MeatChoice.BEEF.name();
        if (fishRadioButton.isSelected()) return Sandwich.MeatChoice.FISH.name();
        if (chickenRadioButton.isSelected()) return Sandwich.MeatChoice.CHICKEN.name();
        return null;
    }

    private String getSelectedBreadChoice() {
        if (bagelRadioButton.isSelected()) return Sandwich.BreadChoice.BAGEL.name();
        if (wheatToastRadioButton.isSelected()) return Sandwich.BreadChoice.WHEAT.name();
        if (sourDoughRadioButton.isSelected()) return Sandwich.BreadChoice.SOUR_DOUGH.name();
        return null;
    }

    private List<String> getSelectedAddOns() {
        List<String> addOns = new ArrayList<>();
        if (lettuceCheckBox.isSelected()) addOns.add("LETTUCE");
        if (tomatoCheckBox.isSelected()) addOns.add("TOMATOES");
        if (cheeseCheckBox.isSelected()) addOns.add("CHEESE");
        if (onionCheckBox.isSelected()) addOns.add("ONIONS");
        return addOns;
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