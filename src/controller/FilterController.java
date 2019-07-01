package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.FilterManager;

public class FilterController implements Initializable {
	@FXML
	private CheckBox name_cb;
	@FXML
	private CheckBox model_cb;
	@FXML
	private CheckBox part_num_cb;
	@FXML
	private CheckBox supply_cb;
	@FXML
	private CheckBox carrier_cb;
	@FXML
	private CheckBox date_cb;
	@FXML
	private CheckBox quantity_cb;
	@FXML
	private CheckBox working_cb;
	@FXML
	private CheckBox location_cb;
	@FXML
	private CheckBox description_cb;
	
	private FilterManager filter_manager;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		filter_manager = FilterManager.getInstance();
		name_cb.setSelected(filter_manager.getNameFilter());
		model_cb.setSelected(filter_manager.getModelFilter());
		part_num_cb.setSelected(filter_manager.getPartNumFilter());
		supply_cb.setSelected(filter_manager.getSupplyFilter());
		carrier_cb.setSelected(filter_manager.getCarrierFilter());
		date_cb.setSelected(filter_manager.getDateFilter());
		quantity_cb.setSelected(filter_manager.getQuantityFilter());
		working_cb.setSelected(filter_manager.getWorkingFilter());
		location_cb.setSelected(filter_manager.getLocationFilter());
		description_cb.setSelected(filter_manager.getDescriptionFilter());
	}

	public void apply(ActionEvent event) {
		filter_manager.setNameFilter(name_cb.isSelected());
		filter_manager.setModelFilter(model_cb.isSelected());
		filter_manager.setPartNumFilter(part_num_cb.isSelected());
		filter_manager.setSupplyFilter(supply_cb.isSelected());
		filter_manager.setCarrierFilter(carrier_cb.isSelected());
		filter_manager.setDateFilter(date_cb.isSelected());
		filter_manager.setQuantityFilter(quantity_cb.isSelected());
		filter_manager.setWorkingFilter(working_cb.isSelected());
		filter_manager.setLocationFilter(location_cb.isSelected());
		filter_manager.setDescriptionFilter(description_cb.isSelected());
		closeStage(event);
	}

	private void closeStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}