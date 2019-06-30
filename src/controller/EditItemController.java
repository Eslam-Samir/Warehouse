package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Item;

public class EditItemController implements Initializable {
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
	
	private Item selectedItem;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void setSelectedItem(Item item) {
		selectedItem = item;
		item_name.setText(selectedItem.getName());
		item_model.setText(selectedItem.getModel());
		item_part_num.setText(selectedItem.getPartNum());
		item_supply.setText(selectedItem.getSupply());
		item_carrier.setText(selectedItem.getCarrier());
		item_date.setText(selectedItem.getDate());
		item_quantity.setText(selectedItem.getQuantity());
		item_working.setText(selectedItem.getWorking());
		item_location.setText(selectedItem.getLocation());
		item_description.setText(selectedItem.getDescription());
	}

	public void confirm(ActionEvent event) {
		selectedItem.setDate(item_date.getText());
		selectedItem.setQuantity(item_quantity.getText());
		selectedItem.setWorking(item_working.getText());
		selectedItem.setLocation(item_location.getText());
		selectedItem.setDescription(item_description.getText());
		closeStage(event);
	}
	
	private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
