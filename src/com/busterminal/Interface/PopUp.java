
package com.busterminal.Interface;

import javafx.scene.control.Alert;


public interface PopUp {
    public static void showMessage(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText("Alert");
        a.setContentText(message);
        a.showAndWait();
    }
  
}
