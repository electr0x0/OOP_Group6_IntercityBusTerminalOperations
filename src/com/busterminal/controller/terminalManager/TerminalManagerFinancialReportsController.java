/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.accountant.PurchaseEntry;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.TransitionUtility;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.ChartType;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerFinancialReportsController implements Initializable {

    private Label dataBaseTestResult;

    private ArrayList<Transaction> allAvailableTxns;
    @FXML
    private Pane transactionPane;

    @FXML
    private Pane tilePaneSecond;

    @FXML
    private Pane tilePaneFourth;

    @FXML
    private MFXDatePicker filterDatePicker;

    private ArrayList<PurchaseEntry> currentInvetory;

    int totalInvValue = 0;
    int totalInvValueUpUntilFilterDate;

    int purchaseQty, purchaseAmnt, reimbQty, reimbAmnt, invQty, invAmnt, refundQty, refundAmnt, paidQty, unPaidQty, paidAmount, unPaidAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(transactionPane);
        TransitionUtility.materialScale(tilePaneSecond);
        TransitionUtility.materialScale(tilePaneFourth);
        if (RelationshipDatabaseClass.getInstance().getAllAvailableTransactions() != null) {
            allAvailableTxns = RelationshipDatabaseClass.getInstance().getAllAvailableTransactions();
            setupTxnsParams(false, null);
        }

        if (RelationshipDatabaseClass.getInstance().getCurrentInventory() != null) {
            currentInvetory = RelationshipDatabaseClass.getInstance().getCurrentInventory();
            inventoryValueCalc(false, null);
        }
    }

    private void setupTxnsParams(boolean byDate, LocalDate filterDate) {
        purchaseQty = 0;
        purchaseAmnt = 0;
        reimbQty = 0;
        reimbAmnt = 0;
        invQty = 0;
        invAmnt = 0;
        refundQty = 0;
        refundAmnt = 0;
        paidQty = 0;
        unPaidQty = 0;
        paidAmount = 0;
        unPaidAmount = 0;
        if (byDate == false) {
            for (Transaction txnObj : allAvailableTxns) {
                switch (txnObj.getTxnStatus()) {
                    case ("Paid"):
                        paidAmount += txnObj.getTxnAmount();
                        paidQty += 1;
                        break;
                    case ("Unpaid"):
                        unPaidAmount += txnObj.getTxnAmount();
                        unPaidQty += 1;
                        break;
                }
                switch (txnObj.getTxnType()) {
                    case ("Purchase"):
                        purchaseQty += 1;
                        System.out.println(purchaseQty);
                        purchaseAmnt += txnObj.getTxnAmount();
                        break;
                    case ("INV"):
                        invQty += 1;
                        invAmnt += txnObj.getTxnAmount();
                        break;
                    case ("REIMB"):
                        reimbQty += 1;
                        reimbAmnt += txnObj.getTxnAmount();
                        break;
                    case ("REFUND"):
                        refundQty += 1;
                        refundAmnt += txnObj.getTxnAmount();
                        break;
                }
            }
            setupTxnChart();
            paidUnpaidPie();
        }

        if (byDate == true) {
            for (Transaction txnObj : allAvailableTxns) {
                if (txnObj.getTxnDate().equals(filterDate)) {
                    switch (txnObj.getTxnStatus()) {
                        case ("Paid"):
                            paidAmount += txnObj.getTxnAmount();
                            paidQty += 1;
                            break;
                        case ("Unpaid"):
                            unPaidAmount += txnObj.getTxnAmount();
                            unPaidQty += 1;
                            break;
                    }
                    switch (txnObj.getTxnType()) {
                        case ("Purchase"):
                            purchaseQty += 1;
                            purchaseAmnt += txnObj.getTxnAmount();
                            break;
                        case ("INV"):
                            invQty += 1;
                            invAmnt += txnObj.getTxnAmount();
                            break;
                        case ("REIMB"):
                            reimbQty += 1;
                            reimbAmnt += txnObj.getTxnAmount();
                            break;
                        case ("REFUND"):
                            refundQty += 1;
                            refundAmnt += txnObj.getTxnAmount();
                            break;
                    }
                }
            }
            setupTxnChart();
            paidUnpaidPie();
        }
    }

    private void paidUnpaidPie() {
        ChartData paidCount = new ChartData("Paid", paidAmount, Tile.BLUE);
        ChartData unPaidCount = new ChartData("Unpaid", unPaidAmount, Tile.RED);

        Tile paidUnpaidPie = TileBuilder.create()
                .skinType(Tile.SkinType.DONUT_CHART)
                .animated(true)
                .prefSize(tilePaneSecond.getPrefWidth(), tilePaneSecond.getPrefHeight())
                .title("Paid and Due Transactions, Total Paid : " + paidQty + " Due: " + unPaidQty)
                .titleAlignment(TextAlignment.CENTER)
                .chartData(paidCount, unPaidCount)
                .backgroundColor(Color.TRANSPARENT)
                .titleColor(Color.WHITE)
                .textColor(Color.WHITE)
                .smoothing(true)
                .autoScale(true)
                .build();

        tilePaneSecond.getChildren().clear();
        tilePaneSecond.getChildren().addAll(paidUnpaidPie);
    }

    private void inventoryValueCalc(boolean byDate, LocalDate filterDate) {
        totalInvValueUpUntilFilterDate = 0;
        if (!byDate) {
            totalInvValue = 0;
            for (PurchaseEntry peObj : currentInvetory) {
                totalInvValue += peObj.getTotalAmount();
            }
            inventoryValueChart(totalInvValue,totalInvValueUpUntilFilterDate);
        } else {
            for (PurchaseEntry peObj : currentInvetory) {
                if (peObj.getPurchaseDate().equals(filterDate)) {
                    totalInvValueUpUntilFilterDate += peObj.getTotalAmount();
                }
            }
            inventoryValueChart(totalInvValueUpUntilFilterDate, totalInvValue);
        }

    }

    private void inventoryValueChart(int valToShow,int valToCompare) {
        ChartData compareValue = new ChartData("Filtered Date Inventory", valToCompare, Tile.BLUE);
        ChartData showingValue = new ChartData("Current Inventory", valToShow, Tile.BLUE);
        

        Tile inventoryValueChart = TileBuilder.create().skinType(SkinType.SMOOTH_AREA_CHART)
                .prefSize(tilePaneFourth.getPrefWidth(), tilePaneFourth.getPrefHeight())
                .minValue(0)
                .maxValue(totalInvValue)
                .title("Inventory Value")
                .titleColor(Color.WHITE)
                .unit("BDT")
                .textAlignment(TextAlignment.CENTER)
                .backgroundColor(Color.TRANSPARENT)
                .chartType(ChartType.LINE)
                .dataPointsVisible(true)
                .chartData(compareValue, showingValue)
                .animated(true)
                .build();

        tilePaneFourth.getChildren().clear();
        tilePaneFourth.getChildren().setAll(inventoryValueChart);
    }

    private void setupTxnChart() {
        BarChartItem Purchase = new BarChartItem("Purchases Worth", purchaseAmnt, Tile.BLUE);
        BarChartItem Invoice = new BarChartItem("Invoices Made Worth", invAmnt, Tile.GREEN);
        BarChartItem Refund = new BarChartItem("Refund Approved Worth", refundAmnt, Tile.RED);
        BarChartItem Reimb = new BarChartItem("Reimbursement Granted Worth", reimbAmnt, Tile.YELLOW);

        Tile barChartTile = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .prefSize(transactionPane.getPrefWidth(), transactionPane.getPrefHeight())
                .animated(true)
                .title("Total Transaction Made : " + (invQty + reimbQty + purchaseQty + refundQty))
                .barChartItems(Purchase, Invoice, Refund, Reimb)
                .decimals(0)
                .autoScale(true)
                .backgroundColor(Color.TRANSPARENT)
                .textColor(Color.WHITE)
                .titleColor(Color.WHITE)
                .sortedData(true)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        transactionPane.getChildren().clear();
        transactionPane.getChildren().add(barChartTile);
    }

    @FXML
    private void filterByDate(ActionEvent event) {
        // Reset Values on Filter

        setupTxnsParams(true, filterDatePicker.getValue());
        inventoryValueCalc(true,filterDatePicker.getValue() );
    }

    @FXML
    private void onClickClearFilter(ActionEvent event) {
        filterDatePicker.clear();
        setupTxnsParams(false, null);
        inventoryValueCalc(false,null);
        
    }

}
