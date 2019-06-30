package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.Item;
import model.ItemManager;
import view.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemController implements Initializable {
	@FXML
	private TextField item_name;
	@FXML
	private TextField item_model;
	@FXML
	private TextField item_part_num;
	@FXML
	private TextField item_supply;
	@FXML
	private TextField item_carrier;
	@FXML
	private TextField item_date;
	@FXML
	private TextField item_quantity;
	@FXML
	private TextField item_working;
	@FXML
	private TextField item_location;
	@FXML
	private TextArea item_description;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void confirm(ActionEvent event) {
		if (item_name.getText().isEmpty()) {
			Alerts.createWarningAlert("Please enter item name");
		} else {
			ItemManager.getInstance().addItem(
					new Item(item_name.getText(), item_model.getText(),
							 item_part_num.getText(), item_supply.getText(),
							 item_carrier.getText(), item_date.getText(),
							 item_quantity.getText(), item_working.getText(), 
							 item_location.getText(), item_description.getText()));
			closeStage(event);
		}
	}
	
	private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
