package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import sample.model.Organization;
import sample.model.OrganizationDAO;
import javafx.collections.ObservableList;

public class SearchByNumController implements Initializable{


    private String number;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Organization,String> numFunctionalityColumn;

    @FXML
    private TableColumn<Organization,String> numOfOrgColumn;

    @FXML
    private TableView numbersTable;

    @FXML
    private TableColumn<Organization,String> orgCityColumn;

    @FXML
    private TableColumn<Organization,String> orgCountryColumn;

    @FXML
    private TableColumn<Organization,Integer> orgIdColumn;

    @FXML
    private TableColumn<Organization,String> orgNameColumn;

    @FXML
    private TableColumn<Organization,String> orgStreetColumn;

    @FXML
    private TableColumn<Organization,String> orgActivityColumn;

    public SearchByNumController(String number)  {
        this.number = number;
    }

    //Search org by number
    @FXML
    private void searchOrgByNumber() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Organization> orgWithNumData = OrganizationDAO.searchOrgByNumber(number);
            populateOrganizations(orgWithNumData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        try {
            searchOrgByNumber();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        orgIdColumn.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        orgNameColumn.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        numOfOrgColumn.setCellValueFactory(cellData ->cellData.getValue().phone_numberProperty());
        numFunctionalityColumn.setCellValueFactory(cellData -> cellData.getValue().phone_functionalityProperty());
        orgActivityColumn.setCellValueFactory(cellData -> cellData.getValue().activityProperty());
        orgCountryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        orgCityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        orgStreetColumn.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
    }

    //Populate Organizations for TableView
    @FXML
    private void populateOrganizations (ObservableList<Organization> orgWithNumData) throws ClassNotFoundException {
        numbersTable.setItems(orgWithNumData);
    }


    public String getNum() {
        return number;
    }

}

