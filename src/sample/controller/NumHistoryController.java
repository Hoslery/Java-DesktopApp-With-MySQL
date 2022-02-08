package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.model.Organization;
import sample.model.OrganizationDAO;

public class NumHistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Organization, Integer> idColumn;

    @FXML
    private TableColumn<Organization, String> nameOrgColumn;

    @FXML
    private TableColumn<Organization, String> newNumColumn;

    @FXML
    private TableColumn<Organization, String> oldNumColumn;

    @FXML
    private TableColumn<Organization, String> dateColumn;

    @FXML
    private TableView historyTable;

    @FXML
    private void searchNumbers() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Organization> orgWithNumData = OrganizationDAO.lookHistory();
            populateOrganizations(orgWithNumData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException{
        searchNumbers();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        nameOrgColumn.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        newNumColumn.setCellValueFactory(cellData ->cellData.getValue().phone_numberProperty());
        oldNumColumn.setCellValueFactory(cellData -> cellData.getValue().old_numberProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().date_addProperty());
    }

    @FXML
    private void populateOrganizations (ObservableList<Organization> orgWithNumData) throws ClassNotFoundException {
        historyTable.setItems(orgWithNumData);
    }

}
