package sample.controller;

import java.io.IOException;
import java.net.URL;
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

public class OldNumController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeBtn;

    @FXML
    private Button lookBtn;

    @FXML
    private TextField numberText;

    @FXML
    private Button searchBtn;

    @FXML
    void initialize() {
        numberText.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if ( !((c =='+') || (c =='(') || (c ==' ') || (c ==')') || (c =='-') || ((c>='0') && (c<='9'))) ) {
                    event.consume();
                }
            }
        });

        searchBtn.setOnAction(event -> {
            try {
                String number = numberText.getText();
                char [] numberArr = number.toCharArray();
                StringBuilder sb = new StringBuilder();
                for(char num:numberArr){
                    if(num >= '0' && num<='9')
                        sb.append(num);
                }
                if(sb.length() >= 10) {
                    SearchByOldNumController controller = new SearchByOldNumController(sb.toString());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../view/SearchByOldNumber.fxml"));
                    loader.setController(controller);
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Поиск организации по старому номеру");
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

        changeBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/AllNumbers.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Выберите номер организации, который хотите изменить(сделайте двойной клик по строчке таблицы)");
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        lookBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/NumbersHistory.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("История смены номеров");
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
