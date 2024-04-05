package Controller;

import Model.MenuItem;
import Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CurrentOrderController {
    @FXML
    private ListView<MenuItem> orderListView;
    @FXML
    private TextField subtotalTextField, salesTaxTextField, totalTextField;
    @FXML
    private Button removeItemButton, placeOrderButton;
    private Order currentOrder;

    public CurrentOrderController() {
    }

    public void setOrder(Order order) {
        this.currentOrder = order;
        updateOrderListView(); // This will populate the ListView
        updateOrderSummary(); // Update the order summary as well
    }

    /*=================================================================================================*/

    @FXML
    private void onRemoveItemButtonClicked()
    {
        MenuItem selectedItem = orderListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            currentOrder.removeItem(selectedItem);
            updateOrderListView();
            updateOrderSummary();
        }
    }

    public void updateOrderListView() {
        ObservableList<MenuItem> itemList = FXCollections.observableArrayList(currentOrder.getItems());
        orderListView.setItems(itemList);
        updateOrderSummary();
    }


    /*=================================================================================================*/


    @FXML
    public void initialize() {
        currentOrder = Order.getInstance();
        updateOrderListView();
        updateOrderSummary();

    }

    public void updateOrder()
    {
        updateOrderListView();
        updateOrderSummary();
    }

    // Updates the subtotal, sales tax, and total fields
    private void updateOrderSummary()
    {
        double subtotal = currentOrder.calculateTotal();
        double SALES_TAX_RATE = 0.06625;                        // New Jersey sales tax
        double salesTax = subtotal * SALES_TAX_RATE;
        double total = subtotal + salesTax;

        subtotalTextField.setText(String.format("%.2f", subtotal));
        salesTaxTextField.setText(String.format("%.2f", salesTax));
        totalTextField.setText(String.format("%.2f", total));
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