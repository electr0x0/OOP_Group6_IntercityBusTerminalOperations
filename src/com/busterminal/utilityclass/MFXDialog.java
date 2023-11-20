/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.utilityclass;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;

/**
 *
 * @author electr0
 */
public class MFXDialog {
    
    private MFXStageDialog dialog;
    private MFXGenericDialog dialogContent;
    String headertext, contenttext, ButtonText;
    AnchorPane parentAnchor;
    private String alertType;
    private ImageView alertIcon;

    public MFXDialog(String headertext, String contenttext, String ButtonText, AnchorPane parentAnchor) {
        this.headertext = headertext;
        this.contenttext = contenttext;
        this.ButtonText = ButtonText;
        this.parentAnchor = parentAnchor;
    }
    
    public MFXDialog(String headertext, String contenttext, String ButtonText, String alertType, AnchorPane parentAnchor) {
        this.headertext = headertext;
        this.contenttext = contenttext;
        this.ButtonText = ButtonText;
        this.alertType = alertType;
        this.parentAnchor = parentAnchor;
        initializeIcon();
    }
    
    
    private void initializeIcon() {
        if (alertType.equals("success")) {
            alertIcon = new ImageView(new Image("/drawables/sucess-altert.gif"));
        } else if (alertType.equals("alert")) {
            alertIcon = new ImageView(new Image("/drawables/error_alert.gif"));
        }
        
        if (alertIcon != null) {
            alertIcon.setSmooth(true);
            alertIcon.setFitHeight(100);
            alertIcon.setFitWidth(100);
        }
    }

    public void openMFXDialog() {
        this.dialogContent = MFXGenericDialogBuilder.build().makeScrollable(true).get();
        
        this.dialog = MFXGenericDialogBuilder.build(dialogContent)
					.toStageDialogBuilder()
					.initModality(Modality.APPLICATION_MODAL)
					.setDraggable(true)
					.setTitle(headertext)
					.setOwnerNode(parentAnchor)
					.setScrimPriority(ScrimPriority.WINDOW)
					.setScrimOwner(true)
					.get();
        
        MFXButton cancelButton = new MFXButton(ButtonText);
        
        cancelButton.setOnAction(event -> {
            dialog.close();
        });
        
        dialogContent.addActions(cancelButton);
        
        if (alertIcon != null) {
             cancelButton.setGraphic(alertIcon);
             cancelButton.setContentDisplay(ContentDisplay.TOP);
        }
        
        
        
        dialogContent.setMaxSize(15, 10);
	dialogContent.setHeaderText(headertext);
        dialogContent.setContentText(contenttext);
        dialog.showDialog();
        
	}
}
