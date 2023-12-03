package com.busterminal.views;

import com.busterminal.model.BulletinMassage;
import com.busterminal.model.Resignation;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class NoticiationController implements Initializable {

    @FXML
    private ListView<BulletinMassage> notificationList;
    @FXML
    private TextArea showMassageTA;
    private String email;
    private ArrayList<BulletinMassage> massagelist;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Alif
        email = RelationshipDatabaseClass.getInstance().getCurrentUserEmail();
        System.out.println(email);

        massagelist = new ArrayList<>();
        massagelist = BulletinMassage.showIndivisualMessage(email);
        System.out.println(email);
        for (BulletinMassage m : massagelist) {
            notificationList.getItems().add(m);
        }

    }

    // View Bulletin Massage
    @FXML
    private void vewMassageOnMouseClick(ActionEvent event) {
        BulletinMassage selectedMassage = notificationList.getSelectionModel().getSelectedItem();
        showMassageTA.setText(selectedMassage.getMassage());
        System.out.println(selectedMassage.getMassage());
        BulletinMassage.readMassage(selectedMassage);
    }

    @FXML
    private void deleteMassageOnMouseClick(ActionEvent event) {
        BulletinMassage selectedMassage = notificationList.getSelectionModel().getSelectedItem();
        ObservableList<BulletinMassage> allPeople;
        allPeople = notificationList.getItems();
        allPeople.remove(selectedMassage);
        BulletinMassage.deleteMassage(selectedMassage);

    }

}
