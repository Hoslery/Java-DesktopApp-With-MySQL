package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.model.Organization;
import sample.model.OrganizationDAO;

public class AllNumController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Organization, String> numFunctionalityColumn;

    @FXML
    private TableColumn<Organization, String> numOfOrgColumn;

    @FXML
    private TableView numbersTable;

    @FXML
    private TableColumn<Organization, String> orgCityColumn;

    @FXML
    private TableColumn<Organization, String> orgCountryColumn;

    @FXML
    private TableColumn<Organization, Integer> orgIdColumn;

    @FXML
    private TableColumn<Organization, String> orgNameColumn;

    @FXML
    private TableColumn<Organization, String> orgStreetColumn;

    //Search all numbers
    @FXML
    private void searchNumbers() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Organization> orgWithNumData = OrganizationDAO.searchNumbers();
            populateOrganizations(orgWithNumData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        searchNumbers();
        orgIdColumn.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        orgNameColumn.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        numOfOrgColumn.setCellValueFactory(cellData ->cellData.getValue().phone_numberProperty());
        numFunctionalityColumn.setCellValueFactory(cellData -> cellData.getValue().phone_functionalityProperty());
        orgCountryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        orgCityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        orgStreetColumn.setCellValueFactory(cellData -> cellData.getValue().streetProperty());

        numbersTable.setRowFactory( tv -> {
            TableRow<Organization> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    String org_name = row.getItem().getOrg_name();
                    String old_num = row.getItem().getPhone_number();
                    Integer idOrg = row.getItem().getOrg_id();
                    try{
                        ChangeNumController controller = new ChangeNumController(org_name,old_num,idOrg);
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../view/ChangeNumber.fxml"));
                        loader.setController(controller);
                        loader.load();
                        Parent root = loader.getRoot();
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Изменение номера организации");
                        Scene scene = new Scene(root);
                        dialogStage.setScene(scene);
                        dialogStage.showAndWait();
                        }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }


    //Populate Organizations for TableView
    @FXML
    private void populateOrganizations (ObservableList<Organization> orgWithNumData) throws ClassNotFoundException {
        numbersTable.setItems(orgWithNumData);
    }

}

