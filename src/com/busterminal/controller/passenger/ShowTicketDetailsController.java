/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.Passenger;
import com.busterminal.model.PopUp;
import com.busterminal.model.SceneSwicth;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ShowTicketDetailsController implements Initializable {

    @FXML
    private AnchorPane anchorpaneShowTicketDetails;
    @FXML
    private TextArea ticketDetailsTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        Passenger.viewTicketDetails(ticketDetailsTextArea);
        
    }
    
        
        
        
     

    @FXML
    private void swicthToPurchaseHistoryScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpaneShowTicketDetails,"/com/busterminal/views/passenger/PurchaseHistory.fxml");
        
               
    
        
        
        
    }

    @FXML
    private void downloadTicketOnClick(ActionEvent event) {
         try {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
                File f = fc.showSaveDialog(null);
                if (f != null) {
                    PdfWriter pw = new PdfWriter(new FileOutputStream(f));
                    PdfDocument pdf = new PdfDocument(pw);
                    pdf.addNewPage();

                    Document document = new Document(pdf);
                    document.setMargins(20, 20, 20, 20);

                    document.add(new Paragraph(ticketDetailsTextArea.getText()).setBold().setTextAlignment(TextAlignment.CENTER));

                    
                    document.close();

                    PopUp.showMessage("Success", "Ticket Downloaded");
                } else {
                    PopUp.showMessage("Cancelled", "Ticket Download Failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
 
    
}
