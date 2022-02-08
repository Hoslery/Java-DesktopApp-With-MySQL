package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.model.Organization;
import sample.model.OrganizationDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchByExtraInfController implements Initializable {
    private List<String> list;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Organization, String> basicCityCol;

    @FXML
    private TableColumn<Organization, String> basicCountryCol;

    @FXML
    private TableColumn<Organization, String> basicDirSurCol;

    @FXML
    private TableColumn<Organization, String> basicEmailCol;

    @FXML
    private TableView basicInfTable;

    @FXML
    private TableColumn<Organization, String> basicOrgActivCol;

    @FXML
    private TableColumn<Organization, Integer> basicOrgIdCol;

    @FXML
    private TableColumn<Organization, String> basicOrgNameCol;

    @FXML
    private TableColumn<Organization, String> basicStreetCol;

    @FXML
    private TableColumn<Organization, String> numbersFuncCol;

    @FXML
    private TableColumn<Organization, String> numbersNumCol;

    @FXML
    private TableColumn<Organization, Integer> numbersOrgIdCol;

    @FXML
    private TableColumn<Organization, String> numbersOrgNameCol;

    @FXML
    private TableView numbersTable;

    @FXML
    private TableView priceListTable;

    @FXML
    private TableColumn<Organization, Integer> priceOrgIdCol;

    @FXML
    private TableColumn<Organization, String> priceOrgNameCol;

    @FXML
    private TableColumn<Organization, String> ServiceCol;

    @FXML
    private TableColumn<Organization,Double> priceOfServiceCol;

    @FXML
    private TableView timeOfWorkTable;

    @FXML
    private TableColumn<Organization, Integer> workOrgIdCol;

    @FXML
    private TableColumn<Organization, String> workOrgNameCol;

    @FXML
    private TableColumn<Organization, String> workDayCol;

    @FXML
    private TableColumn<Organization, String> workStartCol;

    @FXML
    private TableColumn<Organization, String> workEndCol;

    @FXML
    private TableColumn<Organization, String> workWeekendCol;



    public SearchByExtraInfController(List<String> list)  {
        this.list = list;
    }


    //Search org by number
    @FXML
    private void searchOrgWithNum() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Organization> organizations = OrganizationDAO.searchBasicInf(list);
            ObservableList<Organization> phones = OrganizationDAO.searchPhonesWithBasicInf();
            ObservableList<Organization> priceList = OrganizationDAO.searchServiceAndPriceWithBasicInf();
            ObservableList<Organization> graphic = OrganizationDAO.searchGraphicWithBasicInf();
            populateTables(organizations,phones,priceList,graphic);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        try {
            searchOrgWithNum();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        basicOrgIdCol.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        basicOrgNameCol.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        basicOrgActivCol.setCellValueFactory(cellData -> cellData.getValue().activityProperty());
        basicEmailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        basicDirSurCol.setCellValueFactory(cellData -> cellData.getValue().dir_surnameProperty());
        basicCountryCol.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        basicCityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        basicStreetCol.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
        numbersOrgIdCol.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        numbersOrgNameCol.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        numbersNumCol.setCellValueFactory(cellData -> cellData.getValue().phone_numberProperty());
        numbersFuncCol.setCellValueFactory(cellData -> cellData.getValue().phone_functionalityProperty());
        priceOrgIdCol.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        priceOrgNameCol.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        ServiceCol.setCellValueFactory(cellData -> cellData.getValue().serviceProperty());
        priceOfServiceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        workOrgIdCol.setCellValueFactory(cellData -> cellData.getValue().org_idProperty().asObject());
        workOrgNameCol.setCellValueFactory(cellData -> cellData.getValue().org_nameProperty());
        workDayCol.setCellValueFactory(cellData -> cellData.getValue().dayOfWeekProperty());
        workWeekendCol.setCellValueFactory(cellData -> cellData.getValue().weekendProperty());
        workStartCol.setCellValueFactory(cellData -> cellData.getValue().fromTimeProperty());
        workEndCol.setCellValueFactory(cellData -> cellData.getValue().upTimeProperty());
    }

    //Populate Tables
    @FXML
    private void populateTables (ObservableList<Organization> organizations, ObservableList<Organization> phones,
                                 ObservableList<Organization> priceList,ObservableList<Organization> graphic) throws ClassNotFoundException {
        basicInfTable.setItems(organizations);
        numbersTable.setItems(phones);
        priceListTable.setItems(priceList);
        timeOfWorkTable.setItems(graphic);
    }
}
