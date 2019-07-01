package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ItemManager {
	private static ItemManager im;
	
	private ObservableList<Item> items_list;
	private ObservableList<Item> filtered_list;
	private String filter_text;

	private ItemManager() {
		items_list = FXCollections.observableArrayList();
		items_list.addAll(DatabaseManager.getInstance().getItems());
		filtered_list = FXCollections.observableArrayList();
		filtered_list.addAll(items_list);
		
		// Listen for changes in items_list.
        // Whenever items_list changes we must also update the filtered_list
		items_list.addListener(new ListChangeListener<Item>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Item> change) {
                updateFilteredData();
            }
        });
	}

	public static ItemManager getInstance() {
		if (im == null) {
			im = new ItemManager();
		}
		return im;
	}
	
	public ObservableList<Item> getItemsObservableList() {
		return filtered_list;
	}
	
	public void setFilterText(String filter_text) {
		this.filter_text = filter_text;
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
	
	/**
     * Updates the filtered_list to contain all data from the items_list that
     * matches the current filter.
     */
	public void updateFilteredData() {
        filtered_list.clear();
            
        for (Item item : items_list) {
            if (FilterManager.getInstance().matchesFilter(item, filter_text)) {
            	filtered_list.add(item);
            }
        }
    }
	
	/*********************** Private Methods ************************/
	private void resetItemsIDs() {
		int i = 0;
		for (Item item : items_list) {
			item.setId(++i);
		}
	}
}
