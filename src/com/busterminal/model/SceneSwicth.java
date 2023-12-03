/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import com.busterminal.MainTerminalApplication;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author UseR
public class SceneSwicth {
    public SceneSwicth (AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull (MainTerminalApplication.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
    
}
*/
public class SceneSwicth {
    /*

    public SceneSwicth(AnchorPane currentAnchorPane, String fxml) throws IOException {
        /*
        URL resource = MainTerminalApplication.class.getResource(fxml);

        if (resource == null) {
            throw new IOException("FXML resource not found: " + fxml);
        }

        AnchorPane nextAnchorPane = FXMLLoader.load(resource);
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
*/
    public SceneSwicth (AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull (MainTerminalApplication.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);}
        
}
