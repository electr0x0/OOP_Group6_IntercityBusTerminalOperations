
package com.busterminal.model;

import javafx.scene.control.Alert;


public class PopUp {
    public static void showMessage(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText("Alert");
        a.setContentText(message);
        a.showAndWait();
    }
    
    public static void showConfirmationMessage( ){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("");
            alert.setTitle("Confirmation");
            alert.setHeaderText("Request Confirmed");
            alert.showAndWait();
    }

}
