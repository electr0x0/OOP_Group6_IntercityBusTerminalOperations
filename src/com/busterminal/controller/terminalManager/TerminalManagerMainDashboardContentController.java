/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Bus;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.Passenger;
import com.busterminal.model.Ticket;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.DataLoader;
import com.busterminal.utilityclass.TransitionUtility;
import com.busterminal.utilityclass.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerMainDashboardContentController implements Initializable {

    @FXML
    private Pane notifTileFirst;
    @FXML
    private Label labelBusCount;
    @FXML
    private Pane notifTileSecond;
    @FXML
    private Label labelRouteCount;
    @FXML
    private Pane notifTileThird;
    @FXML
    private Label labelPassengerCount;
    @FXML
    private Pane clockTileContainer;
    @FXML
    private Pane tileContainerFirst;
    @FXML
    private Pane tileContainerSecond;
    @FXML
    private Pane tileContainerThird;
    @FXML
    private Pane tileContainerFourth;

    private ArrayList<BusTripSchedule> allAvailableTripSchedule;
    private ArrayList<Ticket> allTicketList;
    private ArrayList<Bus> allAvailableBus;
    
    private ArrayList<Passenger> allPassengerList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(clockTileContainer);
        TransitionUtility.materialScale(tileContainerFirst);
        TransitionUtility.materialScale(tileContainerSecond);
        TransitionUtility.materialScale(tileContainerThird);
        TransitionUtility.materialScale(tileContainerFourth);
        TransitionUtility.materialScale(notifTileFirst);
        TransitionUtility.materialScale(notifTileSecond);
        TransitionUtility.materialScale(notifTileThird);
        setupClockTile();

        if (RelationshipDatabaseClass.getInstance().getAllAvailableBuses() != null) {
            labelBusCount.setText(String.valueOf(RelationshipDatabaseClass.getInstance().getAllAvailableBuses().size()));
            allAvailableBus = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
            setupBusTypePieChart();
        }

        if (RelationshipDatabaseClass.getInstance().getAllLocations() != null) {
            labelRouteCount.setText(String.valueOf(RelationshipDatabaseClass.getInstance().getAllLocations().size()));
        }

        if (RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules() != null) {
            allAvailableTripSchedule = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
            setupTripChart();
            setupBarScheduleInfo();
        }

        allTicketList = DataLoader.loadTicketsFromFile();
        
        allPassengerList = DataLoader.loadPassengersFromFile();
        
        if(allPassengerList != null){
            labelPassengerCount.setText(String.valueOf(allPassengerList.size()));
        }
        
        CalcTicketValues();

    }

    private void CalcTicketValues() {
        if (allTicketList != null) {
            ArrayList<String> datesOfPurchase = new ArrayList<>();
            int[] volumeOfSaleByDate;

            for (Ticket ticketobj : allTicketList) {
                if (!datesOfPurchase.contains(ticketobj.getPurchaseDate()) && ticketobj.getBookingStatus().equals("Confirmed")) {
                    datesOfPurchase.add(ticketobj.getPurchaseDate());
                }
            }
            volumeOfSaleByDate = new int[datesOfPurchase.size()];

            XYChart.Series<String, Number> series1 = new XYChart.Series();
            series1.setName("Dates");

            for (int i = 0; i < datesOfPurchase.size(); i++) {
                for (Ticket ticketobj : allTicketList) {
                    if (ticketobj.getPurchaseDate().equals(datesOfPurchase.get(i))) {
                        volumeOfSaleByDate[i] += ticketobj.getDummy().getAdultFare() * ticketobj.getTicketQty();
                    }
                }
                series1.getData().add(new XYChart.Data(datesOfPurchase.get(i), volumeOfSaleByDate[i]));
            }

            Tile ticketSalesLineChart = TileBuilder.create()
                    .skinType(SkinType.SMOOTHED_CHART)
                    .prefSize(tileContainerFourth.getPrefWidth(), tileContainerFourth.getPrefHeight())
                    .title("Ticket Sale Volume by Date")
                    .titleAlignment(TextAlignment.CENTER)
                    .animationDuration(50000)
                    .animated(true)
                    .dataPointsVisible(true)
                    .smoothing(true)
                    .backgroundColor(Color.TRANSPARENT)
                    .series(series1)
                    .build();
            tileContainerFourth.getChildren().clear();
            tileContainerFourth.getChildren().setAll(ticketSalesLineChart);
        }

    }

    public void setupTripChart() {
        int fr = 0, sa = 0, su = 0, mo = 0, tu = 0, we = 0, th = 0;

        for (BusTripSchedule tripObj : allAvailableTripSchedule) {
            if (Validator.isDateInCurrentWeek(tripObj.getScheduleDate())) {
                switch (Validator.getDayOfWeek(tripObj.getScheduleDate())) {
                    case ("Friday"):
                        fr++;
                        break;
                    case ("Saturday"):
                        sa++;
                        break;
                    case ("Sunday"):
                        su++;
                        break;
                    case ("Monday"):
                        mo++;
                        break;
                    case ("Tuesday"):
                        tu++;
                        break;
                    case ("Wednesday"):
                        we++;
                        break;
                    case ("Thursday"):
                        th++;
                        break;
                }
            }
        }

        XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.setName("Dates");
        series1.getData().add(new XYChart.Data("FR", fr));
        series1.getData().add(new XYChart.Data("SA", sa));
        series1.getData().add(new XYChart.Data("SU", su));
        series1.getData().add(new XYChart.Data("MO", mo));
        series1.getData().add(new XYChart.Data("TU", tu));
        series1.getData().add(new XYChart.Data("WE", we));
        series1.getData().add(new XYChart.Data("TH", th));

        Tile smoothAreaChartTile = TileBuilder.create()
                .skinType(SkinType.SMOOTHED_CHART)
                .prefSize(tileContainerFirst.getPrefWidth(), tileContainerFirst.getPrefHeight())
                .title("Bus Schedule Count Set For Current Week")
                .titleAlignment(TextAlignment.CENTER)
                .animationDuration(50000)
                .animated(true)
                .dataPointsVisible(true)
                .smoothing(true)
                .backgroundColor(Color.TRANSPARENT)
                .series(series1)
                .build();

        tileContainerFirst.getChildren().setAll(smoothAreaChartTile);
    }

    public void setupBarScheduleInfo() {
        ArrayList<String> routeList = new ArrayList<>();

        for (BusTripSchedule tripObj : allAvailableTripSchedule) {
            if (!routeList.contains(tripObj.getSourceDestination())) {
                routeList.add(tripObj.getSourceDestination());
            }
        }

        int[] countValues = new int[routeList.size()];

        for (int i = 0; i < routeList.size(); i++) {
            for (BusTripSchedule tripObj : allAvailableTripSchedule) {
                if (tripObj.getSourceDestination().equals(routeList.get(i))) {
                    countValues[i]++;
                }
            }
        }

        ArrayList<BarChartItem> chartItemsRouteCounts = new ArrayList<>();

        for (int i = 0; i < routeList.size(); i++) {
            chartItemsRouteCounts.add(new BarChartItem(routeList.get(i), countValues[i], Tile.GREEN));
        }

        Tile barChartTile = TileBuilder.create()
                .skinType(SkinType.BAR_CHART)
                .prefSize(tileContainerSecond.getPrefWidth(), tileContainerSecond.getPrefHeight())
                .animated(true)
                .title("Active Routes with Schedule Count")
                .barChartItems(chartItemsRouteCounts)
                .decimals(0)
                .autoScale(true)
                .backgroundColor(Color.TRANSPARENT)
                .textColor(Color.WHITE)
                .titleColor(Color.WHITE)
                .sortedData(true)
                .titleAlignment(TextAlignment.CENTER)
                .build();

        tileContainerSecond.getChildren().setAll(barChartTile);

    }

    public void setupBusTypePieChart() {
        int ac = 0, nonac = 0;

        for (Bus busObj : allAvailableBus) {
            if (busObj.getBusType().equals("Non AC")) {
                nonac++;
            } else {
                ac++;
            }
        }

        ChartData chartData1 = new ChartData("AC", ac, Tile.BLUE);
        ChartData chartData2 = new ChartData("Non-AC", nonac, Tile.RED);

        Tile donutChartTile = TileBuilder.create()
                .skinType(SkinType.DONUT_CHART)
                .animated(true)
                .prefSize(tileContainerThird.getPrefWidth(), tileContainerThird.getPrefHeight())
                .title("Bus Types")
                .titleAlignment(TextAlignment.CENTER)
                .chartData(chartData1, chartData2)
                .backgroundColor(Color.TRANSPARENT)
                .titleColor(Color.WHITE)
                .textColor(Color.WHITE)
                .smoothing(true)
                .autoScale(true)
                .build();

        tileContainerThird.getChildren().setAll(donutChartTile);
    }

    public void setupClockTile() {
        Tile clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(clockTileContainer.getPrefWidth(), clockTileContainer.getPrefHeight())
                .dateVisible(true)
                .backgroundColor(Color.TRANSPARENT)
                .locale(Locale.getDefault())
                .running(true)
                .build();

        clockTileContainer.getChildren().setAll(clockTile);
    }

}
