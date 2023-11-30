/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.PurchaseEntry;
import com.busterminal.model.accountant.RefundRequest;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import java.time.LocalTime;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantDashboardMainContentController implements Initializable {


    private ArrayList<Transaction> allAvailableTransactions;

    private double paid;
    private double unpaid;

    private Tile donutChartTile;
    @FXML
    private Pane tilesContainerSecond;
    @FXML
    private Text labelTotalTxnCount;
    @FXML
    private Pane tilesContainerPie;
    @FXML
    private Pane thirdTileContainer;
    @FXML
    private Pane secondTileContainer;
    @FXML
    private Text labelTotalRefundRequest;

    private Color mfxGreen = Color.web("#4caf50");
    private Color white = Color.web("#ffffff");

    // Inventory Bar Chart Parameters
    private ArrayList<PurchaseEntry> currentInventory;
    private double totalInventoryVal;
    private double totalPurchases;
    private int totalBusParts, totalMtSupplies, totalFuelAndEneryStock, totalTerminalInfraEquipment, totalMisc;
    @FXML
    private Label labelTotalPurchases;
    @FXML
    private Pane thirdTileContainer1;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Text labelTotalReimbursement;
    
    private ArrayList<RefundRequest> allRefundRequest;
    
    //Refund Req params
    private int pending,approved,rejected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (RelationshipDatabaseClass.getInstance().getAllAvailableTransactions() != null) {
            allAvailableTransactions = RelationshipDatabaseClass.getInstance().getAllAvailableTransactions();
            setPaidUnpaidInvoice();
            labelTotalTxnCount.setText(String.valueOf(allAvailableTransactions.size()));
        }

        if (RelationshipDatabaseClass.getInstance().getCurrentInventory() != null) {
            currentInventory = RelationshipDatabaseClass.getInstance().getCurrentInventory();
            totalPurchases = currentInventory.size();
            labelTotalPurchases.setText("Total Purchase Transactions : "+totalPurchases);
            setupInvStats();
        }
        
        if(RelationshipDatabaseClass.getInstance().getAllRefundRequest() != null){
            labelTotalRefundRequest.setText(String.valueOf(RelationshipDatabaseClass.getInstance().getAllRefundRequest().size()));
            allRefundRequest = RelationshipDatabaseClass.getInstance().getAllRefundRequest();
            calcRefundStats();
            
        }
        
        if(RelationshipDatabaseClass.getInstance().getReimbursementList() != null){
            labelTotalReimbursement.setText(String.valueOf(RelationshipDatabaseClass.getInstance().getReimbursementList().size()));
        }

        //Tiles FX Objects Instantiate
        setupClockTile();
        createInvoicePie();
        setupInvBarChart();
        setupRefundPieChart();

    }
    
    private void calcRefundStats(){
        for (RefundRequest refObj: allRefundRequest){
            switch( refObj.getStatus() ){
                case "Rejected":
                   rejected += 1;
                   break;
                case "Approved":
                    approved += 1;
                    break;
                case "Pending":
                    pending +=1;
                    break;
            }
        }
    }
    
    private void setupRefundPieChart(){
        ChartData approvedCount = new ChartData("Approved", approved, Tile.BLUE);
        ChartData rejectedCount = new ChartData("Rejected", rejected, Tile.RED);
        ChartData pendingCount = new ChartData("Pending", pending, Tile.YELLOW);

        Tile refundPieChart = TileBuilder.create()
                .skinType(SkinType.DONUT_CHART)
                .animated(true)
                .prefSize(thirdTileContainer.getPrefWidth(), thirdTileContainer.getPrefHeight())
                .title("Refund Request Information")
                .titleAlignment(TextAlignment.CENTER)
                .chartData(approvedCount, rejectedCount,pendingCount)
                .backgroundColor(Color.TRANSPARENT)
                .titleColor(Color.BLACK)
                .textColor(Color.BLACK)
                .smoothing(true)
                .autoScale(true)
                .build();

        thirdTileContainer.getChildren().addAll(refundPieChart);
    }

    private void setupInvStats() {
        for (PurchaseEntry purchaseObj : currentInventory) {
            switch (purchaseObj.getItem()) {
                case "Bus Parts":
                    totalBusParts += purchaseObj.getQty();
                    break;
                case "Maintenance Supplies":
                    totalMtSupplies += purchaseObj.getQty();
                    break;
                case "Fuel and Energy":
                    totalFuelAndEneryStock += purchaseObj.getQty();
                    break;
                case "Terminal Infrastructure":
                    totalTerminalInfraEquipment += purchaseObj.getQty();
                    break;
                case "Miscellaneous":
                    totalMisc += purchaseObj.getQty();
                    break;
            }
            totalInventoryVal += purchaseObj.getTotalAmount();
        }
        
    }
    
    private void setupInvBarChart(){
        BarChartItem busParts              = new BarChartItem("Bus Parts", totalBusParts, Tile.BLUE);
        BarChartItem terminalInfraSupplies = new BarChartItem("Terminal Infrastructure Supplies", totalTerminalInfraEquipment, Tile.RED);
        BarChartItem mtSupplies            = new BarChartItem("Maintenance Supplies", totalMtSupplies, Tile.YELLOW);
        BarChartItem fuelAndEnergySupplies = new BarChartItem("Fuel and Energy Supplies", totalFuelAndEneryStock, Tile.GREEN);
        BarChartItem misItems              = new BarChartItem("Miscellaneous", totalMisc, Tile.ORANGE);
        
        
        Tile barChartTile = TileBuilder.create()
                                  .skinType(SkinType.BAR_CHART)
                                  .prefSize(500, 300)
                                  .animated(true)
                                  .title("Total Inventory Value : "+ totalInventoryVal)
                                  .barChartItems(busParts, terminalInfraSupplies, mtSupplies, fuelAndEnergySupplies,misItems)
                                  .decimals(0)
                                  .autoScale(true)
                                  .backgroundColor(Color.TRANSPARENT)
                                  .textColor(Color.BLACK)
                                  .titleColor(Color.BLACK)
                                  .sortedData(true)
                                  .titleAlignment(TextAlignment.CENTER)
                                  .build();
        
        secondTileContainer.getChildren().add(barChartTile);
    }

    private void setPaidUnpaidInvoice() {
        for (Transaction txnObj : allAvailableTransactions) {
            if (txnObj.getTxnStatus().equals("Paid") && txnObj.getTxnType().equals("INV")) {
                paid += txnObj.getTxnAmount();
                System.out.println(txnObj.getTxnAmount());
            }
            if(txnObj.getTxnStatus().equals("Unpaid") && txnObj.getTxnType().equals("INV")) {
                unpaid += txnObj.getTxnAmount();
                System.out.println(txnObj.getTxnAmount());
            }
        }
    }


// JAVA FX properties which I'm not using (might need later)
//    private void setupPieChart() {
//        // Instantiate the PieChart
//
//        System.out.println(paid + " " + unpaid);
//
//        // Populate the chart with data
//        PieChart.Data slice1 = new PieChart.Data("Paid", paid);
//        PieChart.Data slice2 = new PieChart.Data("Due", unpaid);
//        pieChartPaidAndUnpaid.getData().addAll(slice1, slice2);
//    }
//    public void setupBarChart() {
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        series.setName("Sample Bar Data");
//
//        series.getData().add(new XYChart.Data<>("Paid", paid));
//        series.getData().add(new XYChart.Data<>("Unpaid", unpaid));
//
//        barChartTest.getData().add(series);
//    }
    private void createInvoicePie() {
        ChartData chartData1 = new ChartData("Received", paid, Tile.BLUE);
        ChartData chartData2 = new ChartData("Due", unpaid, Tile.RED);

        donutChartTile = TileBuilder.create()
                .skinType(SkinType.DONUT_CHART)
                .animated(true)
                .prefSize(tilesContainerPie.getPrefWidth(), tilesContainerPie.getPrefHeight())
                .title("Payment Received vs Due")
                .titleAlignment(TextAlignment.CENTER)
                .chartData(chartData1, chartData2)
                .backgroundColor(Color.TRANSPARENT)
                .titleColor(Color.BLACK)
                .textColor(Color.BLACK)
                .smoothing(true)
                .autoScale(true)
                .build();

        tilesContainerPie.getChildren().clear();
        tilesContainerPie.getChildren().addAll(donutChartTile);

    }

    private void setupClockTile() {

        TimeSection timeSection = TimeSectionBuilder.create()
                .start(LocalTime.now().plusSeconds(20))
                .stop(LocalTime.now().plusHours(1))
                .color(Tile.BLUE)
                .highlightColor(Tile.BLUE)
                .build();

        Tile timerControlTile = TileBuilder.create()
                .skinType(SkinType.TIMER_CONTROL)
                .prefSize(tilesContainerSecond.getPrefWidth(), tilesContainerSecond.getPrefHeight())
                .backgroundColor(Color.TRANSPARENT)
                .secondsVisible(true)
                .dateVisible(true)
                .timeSections(timeSection)
                .running(true)
                .build();

        tilesContainerSecond.getChildren().add(timerControlTile);
    }

}
