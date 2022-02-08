package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.model.Organization;
import sample.model.OrganizationDAO;

public class AllOrgController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Organization, String> orgActivityColumn;

    @FXML
    private TableColumn<Organization, String> orgCityColumn;

    @FXML
    private TableColumn<Organization, String> orgCountryColumn;

    @FXML
    private TableColumn<Organization, String> orgDirSurColumn;

    @FXML
    private TableColumn<Organization, String> orgEmailColumn;

    @FXML
    private TableColumn<Organization, Integer> orgIdColumn;

    @FXML
    private TableColumn<Organization, String> orgNameColumn;

    @FXML
    private TableColumn<Organization, String> orgStreetColumn;

    @FXML
    private TableView organizationsTable;


    //Search all organizations
    @FXML
    private void searchOrganizations() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Organization> orgData = OrganizationDAO.searchOrganizations();
            populateOrganizations(orgData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        searchOrganizations();
        orgIdColumn.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        orgNameColumn.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        orgActivityColumn.setCellValueFactory(cellData -> cellData.getValue().activityProperty());
        orgEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        orgDirSurColumn.setCellValueFactory(cellData -> cellData.getValue().dir_surnameProperty());
        orgCountryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        orgCityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        orgStreetColumn.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
    }


    //Populate Organizations for TableView
    @FXML
    private void populateOrganizations (ObservableList<Organization> orgData) throws ClassNotFoundException {
        organizationsTable.setItems(orgData);
    }


}
