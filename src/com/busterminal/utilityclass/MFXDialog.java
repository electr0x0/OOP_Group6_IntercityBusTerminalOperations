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
import javafx.scene.layout.AnchorPane;
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

    public MFXDialog(String headertext, String contenttext, String ButtonText, AnchorPane parentAnchor) {
        this.headertext = headertext;
        this.contenttext = contenttext;
        this.ButtonText = ButtonText;
        this.parentAnchor = parentAnchor;
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
        
        
        
        dialogContent.setMaxSize(15, 10);
	dialogContent.setHeaderText(headertext);
        dialogContent.setContentText(contenttext);
        dialog.showDialog();
        
	}
}
