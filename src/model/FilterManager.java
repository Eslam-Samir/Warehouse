package model;

public class FilterManager {
	private static FilterManager fm;
	
	private boolean name_filter;
	private boolean model_filter;
	private boolean part_num_filter;
	private boolean supply_filter;
	private boolean carrier_filter;
	private boolean date_filter;
	private boolean quantity_filter;
	private boolean working_filter;
	private boolean location_filter;
	private boolean description_filter;

	private FilterManager() {
		name_filter = true;
		model_filter = true;
		part_num_filter = true;
		supply_filter = true;
		carrier_filter = true;
		date_filter = true;
		quantity_filter = true;
		working_filter = true;
		location_filter = true;
		description_filter = true;
	}

	public static FilterManager getInstance() {
		if (fm == null) {
			fm = new FilterManager();
		}
		return fm;
	}

	public boolean getNameFilter() {
		return name_filter;
	}

	public void setNameFilter(boolean name_filter) {
		this.name_filter = name_filter;
	}

	public boolean getModelFilter() {
		return model_filter;
	}

	public void setModelFilter(boolean model_filter) {
		this.model_filter = model_filter;
	}

	public boolean getPartNumFilter() {
		return part_num_filter;
	}

	public void setPartNumFilter(boolean part_num_filter) {
		this.part_num_filter = part_num_filter;
	}

	public boolean getSupplyFilter() {
		return supply_filter;
	}

	public void setSupplyFilter(boolean supply_filter) {
		this.supply_filter = supply_filter;
	}

	public boolean getCarrierFilter() {
		return carrier_filter;
	}

	public void setCarrierFilter(boolean carrier_filter) {
		this.carrier_filter = carrier_filter;
	}

	public boolean getDateFilter() {
		return date_filter;
	}

	public void setDateFilter(boolean date_filter) {
		this.date_filter = date_filter;
	}

	public boolean getQuantityFilter() {
		return quantity_filter;
	}

	public void setQuantityFilter(boolean quantity_filter) {
		this.quantity_filter = quantity_filter;
	}

	public boolean getWorkingFilter() {
		return working_filter;
	}

	public void setWorkingFilter(boolean working_filter) {
		this.working_filter = working_filter;
	}

	public boolean getLocationFilter() {
		return location_filter;
	}

	public void setLocationFilter(boolean location_filter) {
		this.location_filter = location_filter;
	}

	public boolean getDescriptionFilter() {
		return description_filter;
	}

	public void setDescriptionFilter(boolean description_filter) {
		this.description_filter = description_filter;
	}
	
	/**
     * Returns true if the item matches the current filter. Lower/Upper case
     * is ignored.
     * 
     * @param item
     * @return
     */
    public boolean matchesFilter(Item item, String filter_text) {
        if (filter_text == null || filter_text.isEmpty()) {
            // No filter --> Add all.
            return true;
        }
        String lowercase_filter_text = filter_text.toLowerCase();
        
        if (name_filter && item.getName().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (model_filter && item.getModel().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (part_num_filter && item.getPartNum().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (supply_filter && item.getSupply().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (carrier_filter && item.getCarrier().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (date_filter && item.getDate().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (quantity_filter && item.getQuantity().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (working_filter && item.getWorking().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (location_filter && item.getLocation().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        } else if (description_filter && item.getDescription().toLowerCase().indexOf(lowercase_filter_text) != -1) {
            return true;
        }
        return false; // Does not match
    }
}
