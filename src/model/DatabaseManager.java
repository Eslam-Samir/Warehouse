package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utility.Constants;
import utility.Constants.ItemsTable;

public class DatabaseManager {

	private static DatabaseManager dbm;

	private DatabaseManager() {
	}

	public static DatabaseManager getInstance() {
		if (dbm == null) {
			dbm = new DatabaseManager();
		}
		return dbm;
	}

	public Connection getConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName(Constants.HSQLDB_DRIVER);
		Connection con = DriverManager.getConnection(Constants.DB_URL,
				"Systest", "");
		return con;
	}

	public void prepareTables() {
		Connection con = null;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();

			String query = "CREATE TABLE IF NOT EXISTS "
					+ ItemsTable.TB_NAME
					+ " ("
					+ ItemsTable.ID
					+ " INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
					+ ItemsTable.NAME + " VARCHAR(100)," + ItemsTable.MODEL
					+ " VARCHAR(30)," + ItemsTable.PART_NUM + " VARCHAR(30),"
					+ ItemsTable.SUPPLY + " VARCHAR(30)," + ItemsTable.CARRIER
					+ " VARCHAR(30)," + ItemsTable.DATE + " VARCHAR(30),"
					+ ItemsTable.QUANTITY + " VARCHAR(20),"
					+ ItemsTable.WORKING + " VARCHAR(20),"
					+ ItemsTable.LOCATION + " VARCHAR(30),"
					+ ItemsTable.DESCRIPTION + " VARCHAR(1000))";

			int result = stmt.executeUpdate(query);
			if (result == 0) {
				System.out.println("Table " + ItemsTable.TB_NAME
						+ " has been created successfully");
			} else {
				System.out.println("Table " + ItemsTable.TB_NAME
						+ " is not created");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertItems(ArrayList<Item> items) {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			for (Item item : items) {
				String query = "INSERT INTO " + ItemsTable.TB_NAME + " VALUES (\'"
						+ String.valueOf(item.getId()) + "\', \'"
						+ item.getName() + "\', \'" + item.getModel()
						+ "\', \'" + item.getPartNum() + "\', \'"
						+ item.getSupply() + "\', \'" + item.getCarrier()
						+ "\', \'" + item.getDate() + "\', \'"
						+ item.getQuantity() + "\', \'" + item.getWorking()
						+ "\', \'" + item.getLocation() + "\', \'"
						+ item.getDescription() + "\') ";
				int result = stmt.executeUpdate(query);

				if (result == 0) {
					System.out.println("Failed: " + query);
				} else {
					System.out.println("Insert into table "
							+ ItemsTable.TB_NAME + " executed successfully");
				}
			}
			con.commit();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteItems() {
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			String query = "DELETE FROM " + ItemsTable.TB_NAME;
			stmt.executeUpdate(query);
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<>();
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet item = stmt.executeQuery("SELECT * FROM "
					+ ItemsTable.TB_NAME);
			while (item.next()) {
				items.add(new Item(Integer.valueOf(item.getString(ItemsTable.ID)), item
						.getString(ItemsTable.NAME), item
						.getString(ItemsTable.MODEL), item
						.getString(ItemsTable.PART_NUM), item
						.getString(ItemsTable.SUPPLY), item
						.getString(ItemsTable.CARRIER), item
						.getString(ItemsTable.DATE), item
						.getString(ItemsTable.QUANTITY), item
						.getString(ItemsTable.WORKING), item
						.getString(ItemsTable.LOCATION), item
						.getString(ItemsTable.DESCRIPTION)));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return items;
	}
}