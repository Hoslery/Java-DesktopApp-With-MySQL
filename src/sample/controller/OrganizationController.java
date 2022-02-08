package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class OrganizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField activityText;

    @FXML
    private TextField cityNameText;

    @FXML
    private TextField countryNameText;

    @FXML
    private TextField numberText;

    @FXML
    private TextField orgNameText;

    @FXML
    private Button searchNumberBtn;

    @FXML
    private Button searchOrgBtn;

    @FXML
    private Button showAllOrgBtn;

    @FXML
    private Button showAllNumBtn;

    @FXML
    private TextField streetNameText;

    @FXML
    void initialize() {
        showAllOrgBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/AllOrganizations.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Все организации");
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        showAllNumBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/AllNumbers.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Все номера организаций");
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        numberText.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if ( !((c =='+') || (c =='(') || (c ==' ') || (c ==')') || (c =='-') || ((c>='0') && (c<='9'))) ) {
                    event.consume();
                }
            }
        });


        searchOrgBtn.setOnAction(event -> {
            try {
                String number = numberText.getText();
                char [] numberArr = number.toCharArray();
                StringBuilder sb = new StringBuilder();
                for(char num:numberArr){
                    if(num >= '0' && num<='9')
                        sb.append(num);
                }
                if(sb.length() >= 10) {
                    SearchByNumController controller = new SearchByNumController(sb.toString());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../view/SearchByNumber.fxml"));
                    loader.setController(controller);
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Поиск организации по номеру");
                    Scene scene = new Scene(root);
                    dialogStage.setScene(scene);
                    dialogStage.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Номер введен неверно!\nНомер должен содержать минимум 10 цифр.\nПопробуйте еще раз");
                    alert.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        searchNumberBtn.setOnAction(event -> {
            List<String> list = new ArrayList<>();
            if(!orgNameText.getText().equals("")){
                list.add("organizations.name_org LIKE '%" + orgNameText.getText() +"%'");
            }
            if(!activityText.getText().equals("")){
                list.add("organizations.activity LIKE '%" + activityText.getText() +"%'");
            }
            if(!countryNameText.getText().equals("")){
                list.add("organizations.country LIKE '%" + countryNameText.getText() +"%'");
            }
            if(!cityNameText.getText().equals("")){
                list.add("organizations.city LIKE '%" + cityNameText.getText() +"%'");
            }
            if(!streetNameText.getText().equals("")){
                list.add("organizations.street_and_house LIKE '%" + streetNameText.getText() +"%'");
            }
            try{
                if(list.size() > 0){
                    SearchByOrgController controller = new SearchByOrgController(list);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../view/SearchByOrganization.fxml"));
                    loader.setController(controller);
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Поиск номера/номеров по информации об организации");
                    Scene scene = new Scene(root);
                    dialogStage.setScene(scene);
                    dialogStage.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Для поиска заполните минимум 1 поле!\nПопробуйте еще раз");
                    alert.show();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}

