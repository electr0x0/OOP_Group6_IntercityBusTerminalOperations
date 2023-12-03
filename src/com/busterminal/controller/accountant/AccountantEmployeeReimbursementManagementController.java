/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.ReimbursementInfo;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantEmployeeReimbursementManagementController implements Initializable {

    @FXML
    private TableColumn<ReimbursementInfo, String> colEmployeeDesignation;
    @FXML
    private TableColumn<ReimbursementInfo, LocalDate> colDateOfSubmission;
    @FXML
    private TableColumn<ReimbursementInfo, String> colCauseForReimb;
    @FXML
    private TableColumn<ReimbursementInfo, Integer> colExpenseAmount;
    @FXML
    private TableColumn<ReimbursementInfo, String> colReimbState;
    @FXML
    private TableColumn<ReimbursementInfo, String> colRecevingMethod;
    @FXML
    private TableColumn<ReimbursementInfo, String> colFirstName;
    @FXML
    private TableColumn<ReimbursementInfo, String> colLastName;
    @FXML
    private TableColumn<ReimbursementInfo, String> colReimID;
    @FXML
    private TextField txtFieldSearch;

    @FXML
    private MFXLegacyTableView<ReimbursementInfo> reimbTableView;

    private ObservableList<ReimbursementInfo> reimbTableViewList = FXCollections.observableArrayList();

    private ObservableList<ReimbursementInfo> reimbTableViewFilteredList = FXCollections.observableArrayList();

    private ArrayList<ReimbursementInfo> allAvailableReimbList;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXRadioButton radioSearchName;
    @FXML
    private MFXRadioButton radioSearchDesignation;
    @FXML
    private MFXRadioButton radioSearchDate;
    @FXML
    private MFXButton markPaidButton;

    private ToggleGroup searchToggleGroup;

    private static final String TXN_TYPE = "REIMB";
    @FXML
    private TableColumn<ReimbursementInfo, String> colEmployeeID;
    @FXML
    private HBox hboxSearch;
    @FXML
    private Pane paneTopEmployeeReim;
    @FXML
    private Pane paneReimByDepartment;
    @FXML
    private MFXRadioButton radioSearchExpense;
    @FXML
    private ImageView caretImageView;

    private boolean isCaretExapanded = false;
    @FXML
    private MFXRadioButton radioSearchState;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        setupTable();

        if (RelationshipDatabaseClass.getInstance().getReimbursementList() != null) {
            allAvailableReimbList = RelationshipDatabaseClass.getInstance().getReimbursementList();
            reimbTableViewList.setAll(allAvailableReimbList);
            reimbTableView.setItems(reimbTableViewList);
        } else {
            showErrorDialog("Info", "There are currently zero Reimbursement Request");
        }

        searchToggleGroup = new ToggleGroup();
        radioSearchDate.setToggleGroup(searchToggleGroup);
        radioSearchDesignation.setToggleGroup(searchToggleGroup);
        radioSearchName.setToggleGroup(searchToggleGroup);
        radioSearchExpense.setToggleGroup(searchToggleGroup);
        radioSearchState.setToggleGroup(searchToggleGroup);
    }

    public void setupTable() {
        colReimID.setCellValueFactory(new PropertyValueFactory<>("reimbursementID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colEmployeeDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colDateOfSubmission.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        colCauseForReimb.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        colExpenseAmount.setCellValueFactory(new PropertyValueFactory<>("expenseAmount"));
        colReimbState.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRecevingMethod.setCellValueFactory(new PropertyValueFactory<>("prefPaymentMethod"));

    }

    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void onClickTableUpdateSelectedItem(MouseEvent event) {
        markPaidButton.setDisable(false);
    }

    @FXML
    private void onClickDeleteSelectedItem(ActionEvent event) {
        ReimbursementInfo selected = reimbTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (selected.getStatus().equals("Paid")) {
                MFXDialog.showErrorDialog("Already Paid!", "Selected Entry is already marked as paid!", rootPane);
                return;
            }
            int txnAmount = 0;
            String txnParticular = "";
            for (ReimbursementInfo reimObj : allAvailableReimbList) {
                if (reimObj.equals(selected)) {
                    reimObj.setStatus("Paid");
                    txnAmount = reimObj.getExpenseAmount();
                    txnParticular = reimObj.getExpenseType();
                    MFXDialog.showSuccessDialog("Sucess", "Successfully Marked the Request As Paid", rootPane);
                    break;
                }
            }

            reimbTableViewList.clear();
            reimbTableViewList.setAll(allAvailableReimbList);

            reimbTableView.setItems(reimbTableViewList);

            RelationshipDatabaseClass.getInstance().setReimbursementList(allAvailableReimbList);

            Transaction reimTxn = new Transaction(LocalDate.now(), TXN_TYPE, txnAmount, "Paid", txnParticular);

            RelationshipDatabaseClass.getInstance().addItemToAllAvailableTransactions(reimTxn);

        } else {
            MFXDialog.showErrorDialog("No Selection", "Please select an item first!", rootPane);
        }
    }

    @FXML
    private void searchActionTableView(KeyEvent event) {

        String searchCriteria = txtFieldSearch.getText().toLowerCase();

        if (searchToggleGroup.getSelectedToggle() == null) {
            MFXDialog.showErrorDialog("No Fitler Category!", "Please select a filter category before trying to perform search!", rootPane);
            return;
        }

        if (!searchCriteria.isEmpty()) {
            reimbTableViewFilteredList.clear();
            Toggle selectedToggle = searchToggleGroup.getSelectedToggle();
            MFXRadioButton selectedRadioButton = (MFXRadioButton) selectedToggle; // Cast RadioButton as Toggle is a superClass of RadioButton

            switch (selectedRadioButton.getText()) {

                case ("Name"):
                    for (ReimbursementInfo reimObj : allAvailableReimbList) {
                        String empName = reimObj.getFirstName() + reimObj.getLastName();
                        if (empName.toLowerCase().contains(searchCriteria)) {
                            reimbTableViewFilteredList.add(reimObj);
                        }
                    }
                    reimbTableView.setItems(reimbTableViewFilteredList);
                    break;
                case ("Expense Type"):
                    for (ReimbursementInfo reimObj : allAvailableReimbList) {
                        if (reimObj.getExpenseType().toLowerCase().contains(searchCriteria)) {
                            reimbTableViewFilteredList.add(reimObj);
                        }
                    }
                    reimbTableView.setItems(reimbTableViewFilteredList);
                    break;
                case ("Date of Submission"):
                    for (ReimbursementInfo reimObj : allAvailableReimbList) {
                        if (reimObj.getSubmissionDate().toString().toLowerCase().contains(searchCriteria)) {
                            reimbTableViewFilteredList.add(reimObj);
                        }
                    }
                    reimbTableView.setItems(reimbTableViewFilteredList);
                    break;
                case ("Designation"):
                    for (ReimbursementInfo reimObj : allAvailableReimbList) {
                        if (reimObj.getDesignation().toLowerCase().contains(searchCriteria)) {
                            reimbTableViewFilteredList.add(reimObj);
                        }
                    }
                    reimbTableView.setItems(reimbTableViewFilteredList);
                    break;
                case ("State"):
                    for (ReimbursementInfo reimObj : allAvailableReimbList) {
                        if (reimObj.getStatus().toLowerCase().contains(searchCriteria)) {
                            reimbTableViewFilteredList.add(reimObj);
                        }
                    }
                    reimbTableView.setItems(reimbTableViewFilteredList);
                    break;
            }
        } else {
            reimbTableView.setItems(reimbTableViewList);
        }
    }

    @FXML
    private void onClickCaret(MouseEvent event) {
        Image caretDownImage = new Image(getClass().getResourceAsStream("/drawables/down_arrow.png"));
        Image caretUpImage = new Image(getClass().getResourceAsStream("/drawables/up_arrow.png"));
        if (!isCaretExapanded) {
            caretImageView.setImage(caretUpImage); // modify this
            isCaretExapanded = true;
            hboxSearch.setVisible(true);
            TransitionUtility.materialScale(hboxSearch);
        } else {
            if (searchToggleGroup.getSelectedToggle() != null) {
                searchToggleGroup.getSelectedToggle().setSelected(false);
            }

            caretImageView.setImage(caretDownImage);
            isCaretExapanded = false;
            TransitionUtility.materialScaleOpposite(hboxSearch);
        }
    }

    @FXML
    private void updateReimChartsAndValues(Event event) {
        TransitionUtility.materialScale(paneTopEmployeeReim);
        TransitionUtility.materialScale(paneReimByDepartment);

        if (allAvailableReimbList != null) {
            setupTopEmployeeChart();
            setupReimByDepartment();
        }
    }

    private void setupTopEmployeeChart() {
        ArrayList<String> employeeNameUnique = new ArrayList<>();

        for (ReimbursementInfo reimObj : allAvailableReimbList) {
            String fullName = reimObj.getFirstName() + " " + reimObj.getLastName();
            if (!employeeNameUnique.contains(fullName)) {
                employeeNameUnique.add(fullName);
            }
        }

        int[] totalReimAmountForUniqueEmployee = new int[employeeNameUnique.size()];

        ArrayList<BarChartItem> itemsForChart = new ArrayList<>();

        for (int i = 0; i < employeeNameUnique.size(); i++) {
            for (ReimbursementInfo reimObj : allAvailableReimbList) {
                String fullName = reimObj.getFirstName() + " " + reimObj.getLastName();
                if (fullName.equals(employeeNameUnique.get(i))) {
                    totalReimAmountForUniqueEmployee[i] += reimObj.getExpenseAmount();
                }
            }
            itemsForChart.add(new BarChartItem(employeeNameUnique.get(i), totalReimAmountForUniqueEmployee[i], Tile.YELLOW));
        }

        Tile barChartTile = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .prefSize(paneTopEmployeeReim.getPrefWidth(), paneTopEmployeeReim.getPrefHeight())
                .animated(true)
                .title("Top Employees")
                .barChartItems(itemsForChart)
                .decimals(0)
                .autoScale(true)
                .backgroundColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .titleColor(Color.BLACK)
                .sortedData(true)
                .titleAlignment(TextAlignment.CENTER)
                .build();
        
        paneTopEmployeeReim.getChildren().clear();
        paneTopEmployeeReim.getChildren().setAll(barChartTile);
    }
    
    private void setupReimByDepartment(){
        int managerReim = 0, accReim = 0, hrReim = 0, mtReim = 0, driverReim = 0, adminReim = 0;
        for (ReimbursementInfo reimObj: allAvailableReimbList){
            switch(reimObj.getDesignation()){
                case "Accountant":
                    accReim += reimObj.getExpenseAmount();
                    break;
                case "Human Resource":
                    hrReim += reimObj.getExpenseAmount();
                    break;
                case "Maintainence Staff":
                    mtReim += reimObj.getExpenseAmount();
                    break;
                case "Driver":
                    driverReim += reimObj.getExpenseAmount();
                    break;
                case "Administrator":
                    adminReim += reimObj.getExpenseAmount();
                    break;
                case "Terminal Manager":
                    managerReim += reimObj.getExpenseAmount();
                    break;
            }
        }
        
        ChartData managerOt = new ChartData("Manager", managerReim, Tile.BLUE);
        ChartData accOt = new ChartData("Accountant", accReim, Tile.GREEN);
        ChartData hrOt = new ChartData("Human Resource", hrReim, Tile.MAGENTA);
        ChartData mtOt = new ChartData("Maintainence Staff", mtReim, Tile.RED);
        ChartData adminOt = new ChartData("Administrator", driverReim, Tile.PINK);
        ChartData driverOt = new ChartData("Driver", adminReim, Tile.DARK_BLUE);
        
        Tile overTimepieChart = TileBuilder.create()
                .skinType(Tile.SkinType.DONUT_CHART)
                .animated(true)
                .prefSize(paneReimByDepartment.getPrefWidth(), paneReimByDepartment.getPrefHeight())
                .title("Reimbursement Data by Department")
                .titleAlignment(TextAlignment.CENTER)
                .chartData(managerOt, accOt,hrOt,mtOt,adminOt,driverOt)
                .backgroundColor(Color.TRANSPARENT)
                .titleColor(Color.WHITE)
                .textColor(Color.WHITE)
                .smoothing(true)
                .autoScale(true)
                .build();
        
        paneReimByDepartment.getChildren().clear();
        
        paneReimByDepartment.getChildren().setAll(overTimepieChart);
    }

}
