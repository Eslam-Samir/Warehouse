package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	public static void createWarningAlert(String warnings)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Wrong input");
		alert.setHeaderText(warnings);
		alert.show();
	}
	public static void createWarningAlertWithContent(String warnings, String content)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Wrong input");
		alert.setHeaderText(warnings);
		alert.setContentText(content);
		alert.show();
	}
}
