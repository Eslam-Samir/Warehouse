package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
	private static int last_index = 0;
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty model;
	private SimpleStringProperty part_num;
	private SimpleStringProperty supply;
	private String carrier;
	private String date;
	private SimpleStringProperty quantity;
	private SimpleStringProperty working;
	private SimpleStringProperty location;
	private String description;

	public Item(String name, String model, String part_num, String supply,
			String carrier, String date, String quantity, String working,
			String location, String description) {
		this.id = new SimpleIntegerProperty(++last_index);
		this.name = new SimpleStringProperty(name);
		this.model = new SimpleStringProperty(model);
		this.part_num = new SimpleStringProperty(part_num);
		this.supply = new SimpleStringProperty(supply);
		this.carrier = carrier;
		this.date = date;
		this.quantity = new SimpleStringProperty(quantity);
		this.working = new SimpleStringProperty(working);
		this.description = description;
		this.location = new SimpleStringProperty(location);
	}
	
	public Item(int id, String name, String model, String part_num, String supply,
			String carrier, String date, String quantity, String working,
			String location, String description) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.model = new SimpleStringProperty(model);
		this.part_num = new SimpleStringProperty(part_num);
		this.supply = new SimpleStringProperty(supply);
		this.carrier = carrier;
		this.date = date;
		this.quantity = new SimpleStringProperty(quantity);
		this.working = new SimpleStringProperty(working);
		this.description = description;
		this.location = new SimpleStringProperty(location);
		
		if (this.id.get() > last_index) {
			last_index = this.id.get();
		}
	}
	
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
		if (this.id.get() > last_index) {
			last_index = this.id.get();
		}
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getModel() {
		return model.get();
	}

	public void setModel(String model) {
		this.model = new SimpleStringProperty(model);
	}

	public String getPartNum() {
		return part_num.get();
	}

	public void setPartNum(String part_num) {
		this.part_num = new SimpleStringProperty(part_num);
	}

	public String getSupply() {
		return supply.get();
	}

	public void setSupply(String supply) {
		this.supply = new SimpleStringProperty(supply);
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQuantity() {
		return quantity.get();
	}

	public void setQuantity(String quantity) {
		this.quantity = new SimpleStringProperty(quantity);
	}

	public String getWorking() {
		return working.get();
	}

	public void setWorking(String working) {
		this.working = new SimpleStringProperty(working);
	}

	public String getLocation() {
		return location.get();
	}

	public void setLocation(String location) {
		this.location = new SimpleStringProperty(location);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return name.get() + "," + model.get() + "," + part_num.get() + ","
				+ supply.get() + "," + carrier + "," + date + ","
				+ quantity.get() + "," + working.get() + "," + location.get()
				+ "," + description;
	}

}
