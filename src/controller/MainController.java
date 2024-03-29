package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import view.EditingCell;
import model.CSVFileManager;
import model.DatabaseManager;
import model.Item;
import model.ItemManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class MainController implements Initializable {
	@FXML
	private TableColumn<Item, Integer> number_column;
	@FXML
	private TableColumn<Item, String> name_column;
	@FXML
	private TableColumn<Item, String> model_column;
	@FXML
	private TableColumn<Item, String> part_column;
	@FXML
	private TableColumn<Item, String> supply_column;
	@FXML
	private TableColumn<Item, String> quantity_column;
	@FXML
	private TableColumn<Item, String> working_column;
	@FXML
	private TableColumn<Item, String> location_column;

	@FXML
	private TableView<Item> items_table;
	
	@FXML
	private TextField search_field;
	
	private ItemManager item_manager;

	public MainController() {
		DatabaseManager.getInstance().prepareTables();
		item_manager = ItemManager.getInstance();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Callback<TableColumn<Item, String>, TableCell<Item, String>> editableFactory 
				= new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
            @Override
            public TableCell<Item, String> call(TableColumn<Item, String> i) {
                return new EditingCell();
            }
        };
        
		number_column.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
		name_column.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		model_column.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
		part_column.setCellValueFactory(new PropertyValueFactory<Item, String>("part_num"));
		supply_column.setCellValueFactory(new PropertyValueFactory<Item, String>("supply"));
		quantity_column.setCellValueFactory(new PropertyValueFactory<Item, String>("quantity"));
		quantity_column.setCellFactory(editableFactory);
		quantity_column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
            	t.getRowValue().setQuantity(t.getNewValue());
            }
        });
		
		working_column.setCellValueFactory(new PropertyValueFactory<Item, String>("working"));
		working_column.setCellFactory(editableFactory);
		working_column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
            	t.getRowValue().setQuantity(t.getNewValue());
            }
        });
		
		location_column.setCellValueFactory(new PropertyValueFactory<Item, String>("location"));
		location_column.setCellFactory(editableFactory);
		location_column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
            	t.getRowValue().setQuantity(t.getNewValue());
            }
        });
		
		// detect delete button press
		items_table.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent ke) {
	            if (ke.getCode() == KeyCode.DELETE) {
	            	deleteSelected();
	            }
	        }
	    });
		
		// detect item double click
		items_table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                showSelectedItemDetails();
            }
        });
		
		updateTable();

        // Listen for text changes in the filter text field
		search_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
            	item_manager.setFilterText(search_field.getText());
                item_manager.updateFilteredData();
                reapplyTableSortOrder();
            }
        });
	}
	
	/******************************** Buttons *************************************/
	public void filterItems(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FilterDialog.fxml"));
			Parent parent = fxmlLoader.load();
	        Scene scene = new Scene(parent);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Filters");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		item_manager.updateFilteredData();
		reapplyTableSortOrder();
	}
	
	/******************************* Edit Menu ************************************/
	public void itemDetails(ActionEvent event) {
		showSelectedItemDetails();
	}
	
	public void addItem(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddItemDialog.fxml"));
			Parent parent = fxmlLoader.load();
	        Scene scene = new Scene(parent);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Add Item");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateTable();
	}
	
	public void editItem(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/EditItemDialog.fxml"));
			Parent parent = fxmlLoader.load();
	        Scene scene = new Scene(parent);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Edit Item");
   			
   			EditItemController controller = fxmlLoader.getController();
   			controller.setSelectedItem(items_table.getSelectionModel().getSelectedItem());
			
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateTable();
	}
	
	public void deleteItem(ActionEvent event) {
		deleteSelected();
	}

	/******************************* File Menu ************************************/
	
	public void commit(ActionEvent event) {
		DatabaseManager.getInstance().deleteItems();
		DatabaseManager.getInstance().insertItems(item_manager.getItemsArrayList());
	}

	public void importFromCSV(ActionEvent event) {
		Stage stage = (Stage) items_table.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Import From CSV File");
		ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"Excel File (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			item_manager.clearItemsList();
			ArrayList<Item> items = CSVFileManager.getInstance().loadCSVFile(file.getPath());
			item_manager.addItems(items);
			updateTable();
		}
	}

	public void exportToCSV(ActionEvent event) {
		Stage stage = (Stage) items_table.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Export To CSV File");
		ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"Excel File (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			CSVFileManager.getInstance().saveCSVFile(file.getPath(), item_manager.getItemsArrayList());
		}
	}

	public void close(ActionEvent event) {
		System.exit(0);
	}
	
	/******************************* Private ************************************/
	private void showSelectedItemDetails() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DetailWindow.fxml"));
			Parent parent = fxmlLoader.load();
	        Scene scene = new Scene(parent);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Item Details");
	        
	        DetailController controller = fxmlLoader.getController();
   			controller.setSelectedItem(items_table.getSelectionModel().getSelectedItem());
   			
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteSelected() {
		Item item = null;
		if((item = items_table.getSelectionModel().getSelectedItem()) != null) {
			item_manager.deleteItem(item);
			updateTable();
		}
	}
	
	private void updateTable() {
		items_table.setItems(item_manager.getItemsObservableList());
		items_table.refresh();
	}
	
	private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Item, ?>> sortOrder = new ArrayList<>(items_table.getSortOrder());
        items_table.getSortOrder().clear();
        items_table.getSortOrder().addAll(sortOrder);
    }
}
