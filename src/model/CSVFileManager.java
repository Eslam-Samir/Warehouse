package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFileManager {

	private static CSVFileManager csv_fm;

	private CSVFileManager() {
	}

	public static CSVFileManager getInstance() {
		if (csv_fm == null) {
			csv_fm = new CSVFileManager();
		}
		return csv_fm;
	}

	public ArrayList<Item> loadCSVFile(String file_path) {
		ArrayList<Item> items = new ArrayList<>();
		BufferedReader csv_reader = null;
		String row;
		try {
			csv_reader = new BufferedReader(new FileReader(file_path));
			// Skip headers
			csv_reader.readLine();
			while ((row = csv_reader.readLine()) != null) {
				String[] data = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				if (data.length == 10) {
					items.add(new Item(data[0], data[1], data[2], data[3],
							data[4], data[5], data[6], data[7], data[8],
							data[9]));
				} else {
					System.out.println("Wrong CSV File Format");
				}
			}
			csv_reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}

	public void saveCSVFile(String file_path, ArrayList<Item> items) {
		FileWriter csvWriter = null;
		// write columns
		try {
			csvWriter = new FileWriter(file_path);
			csvWriter
					.append("Name,Model,Part Number,Supply,Carrier,Date,Quantity,Working,Location,Description\n");
			for (Item item : items) {
				csvWriter.append(item.toString());
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
