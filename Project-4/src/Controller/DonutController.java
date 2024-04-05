package Controller;

import Model.Donut;
import Model.Order;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DonutController {

    @FXML
    private ComboBox<String> donutTypeComboBox;
    @FXML
    private ListView<String> availableFlavorsListView;
    @FXML
    private ListView<String> selectedFlavorsListView;
    @FXML
    private Spinner<Integer> quantitySpinner;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private TextField subtotalTextField;
    @FXML
    private ImageView donutImageView;

    private Order currentOrder;
    private Map<String, Double> donutPrices;
    private Map<String, Image> donutImages;

    public DonutController() {
        currentOrder = new Order();
        donutImages = new HashMap<>(); // Initialize the HashMap here
        loadDonutImages();
    }

    private void updateDonutImage(String donutType) {
        Image image = donutImages.get(donutType.toUpperCase());
        if (image != null) {
            donutImageView.setImage(image);
        } else {
            // Handle the case where the image is not found
            System.err.println("Image not found for donut type: " + donutType);
        }
    }
    private void loadDonutImages() {
        donutImages = new HashMap<>();
        donutImages.put("YEAST", new Image("/Resources/YeastDonut.PNG"));
        donutImages.put("CAKE", new Image("/Resources/CakeDonut.PNG"));
        donutImages.put("DONUT HOLE", new Image("/Resources/DonutHole.PNG"));
    }

    private Image loadImage(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException("Cannot find image: " + path);
            }
            return new Image(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @FXML
    public void initialize()
    {
        subtotalTextField.setEditable(false);
        donutTypeComboBox.getItems().addAll("Yeast", "Cake", "Donut Hole");
        donutTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            updateDonutImage(newValue);
            updateFlavorList(newValue);
            updateSubtotal(); // Update subtotal when donut type changes
        });

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        quantitySpinner.setValueFactory(valueFactory);
        updateSubtotal(); // Initial subtotal update

    }

    private void updateFlavorList(String type) {
        availableFlavorsListView.getItems().clear();
        switch (type) {
            case "Yeast":
                for (Donut.YeastDonutFlavor flavor : Donut.YeastDonutFlavor.values()) {
                    availableFlavorsListView.getItems().add(flavor.toString());
                }
                break;
            case "Cake":
                for (Donut.CakeDonutFlavor flavor : Donut.CakeDonutFlavor.values()) {
                    availableFlavorsListView.getItems().add(flavor.toString());
                }
                break;
            case "Donut Hole":
                for (Donut.DonutHoleFlavor flavor : Donut.DonutHoleFlavor.values()) {
                    availableFlavorsListView.getItems().add(flavor.toString());
                }
                break;
        }
        updateSubtotal();
    }

    @FXML
    private void onAddButtonClicked() {
        String selectedType = donutTypeComboBox.getValue().toUpperCase();
        String selectedFlavor = availableFlavorsListView.getSelectionModel().getSelectedItem();
        if (selectedFlavor == null) {
            return;
        }
        else {
            int quantity = quantitySpinner.getValue();
            String displayText = quantity + "x " + selectedFlavor;
            selectedFlavorsListView.getItems().add(displayText);
            availableFlavorsListView.getItems().remove(selectedFlavor);
            for (int i = 0; i < quantity; i++) {
                Donut donut = new Donut(selectedType, selectedFlavor); // Use the transformed string
                currentOrder.addItem(donut);
            }
            updateSubtotal();
        }
    }

    @FXML
    private void onRemoveButtonClicked()
    {
        String selected = selectedFlavorsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedFlavorsListView.getItems().remove(selected);
            int quantity = Integer.parseInt(selected.substring(0, selected.indexOf('x')).trim());
            String flavor = selected.substring(selected.indexOf(' ') + 1);
            String selectedType = donutTypeComboBox.getValue().toUpperCase(); // Convert to uppercase


            for (int i = 0; i < quantity; i++) {
                currentOrder.removeItem(new Donut(selectedType, flavor));
            }

            boolean stillSelected = selectedFlavorsListView.getItems().stream()
                    .anyMatch(item -> item.contains(flavor));
            if (!stillSelected) {
                availableFlavorsListView.getItems().add(flavor);
            }

            updateSubtotal();
        }
    }

    private void updateOrder()
    {
        currentOrder.getItems().clear();
        for (String item : selectedFlavorsListView.getItems()) {
            int quantity = Integer.parseInt(item.substring(0, item.indexOf('x')).trim());
            String flavor = item.substring(item.indexOf(' ') + 1);
            Donut.DonutType donutType = Donut.DonutType.valueOf(donutTypeComboBox.getValue().toUpperCase());
            for (int i = 0; i < quantity; i++)
            {
                Donut donut = new Donut(donutTypeComboBox.getValue(), flavor);
                currentOrder.addItem(donut);
            }
        }
        updateSubtotal();
    }


    private void updateSubtotal() {
        double subtotal = currentOrder.calculateTotal();
        subtotalTextField.setText(String.format("$%.2f", subtotal));
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