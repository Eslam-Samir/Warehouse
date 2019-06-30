package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemManager {
	private static ItemManager im;
	
	private ObservableList<Item> items_list;

	private ItemManager() {
		items_list = FXCollections.observableArrayList();
		items_list.addAll(DatabaseManager.getInstance().getItems());
	}

	public static ItemManager getInstance() {
		if (im == null) {
			im = new ItemManager();
		}
		return im;
	}
	
	public ObservableList<Item> getItemsObservableList() {
		return items_list;
	}
	
	public ArrayList<Item> getItemsArrayList() {
		ArrayList<Item> array_list = new ArrayList<>();
		array_list.addAll(items_list);
		return array_list;
	}
	
	public void addItem(Item item) {
		items_list.add(item);
	}
	
	public void addItems(ArrayList<Item> items) {
		items_list.addAll(items);
	}
	
	public void deleteItem(Item item) {
		items_list.remove(item);
		resetItemsIDs();
	}
	
	public void clearItemsList() {
		items_list.clear();
	}
	
	/*********************** Private Methods ************************/
	private void resetItemsIDs() {
		int i = 0;
		for (Item item : items_list) {
			item.setId(++i);
		}
	}
}
