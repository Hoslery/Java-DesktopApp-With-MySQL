package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

public class ExtraInfController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dirSurnameText;

    @FXML
    private TextField orgEmailText;

    @FXML
    private TextField orgServiceText;

    @FXML
    private TextField priceText;

    @FXML
    private Button searchOrgWithNumBtn;

    @FXML
    private TextField fromTimeText;

    @FXML
    private TextField upTimeText;

    @FXML
    private ComboBox<String> dayOfWeekComboBox;

    @FXML
    private CheckBox weekendCheckBox;

    private String dayOfWeek;

    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    @FXML
    void initialize() throws ParseException {

        fromTimeText.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("09:00")));
        upTimeText.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("21:00")));

        priceText.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if ( !((c =='.') || ((c>='0') && (c<='9'))) ) {
                    event.consume();
                }
            }
        });

        dayOfWeekComboBox.setStyle("-fx-font: 12px \"System Italic\";");
        dayOfWeekComboBox.getItems().setAll(
                "Понедельник",
                "Вторник",
                "Среда",
                "Четверг",
                "Пятница",
                "Суббота",
                "Воскресенье"
        );

        dayOfWeekComboBox.setOnAction(event1 -> {
            dayOfWeek = dayOfWeekComboBox.getSelectionModel().getSelectedItem();
        });

        searchOrgWithNumBtn.setOnAction(event -> {
            List<String> list = new ArrayList<>();
            if(!orgEmailText.getText().equals("")){
                list.add("organizations.email LIKE '%" + orgEmailText.getText() +"%'");
            }
            if(!dirSurnameText.getText().equals("")){
                list.add("organizations.director_sur LIKE '%" + dirSurnameText.getText() +"%'");
            }
            if(!orgServiceText.getText().equals(""))
                list.add("price_list.service LIKE '%" + orgServiceText.getText() +"%'");
            if(!priceText.getText().equals(""))
                list.add("price_list.price = '" + Double.parseDouble(priceText.getText()) +"'");
            if(dayOfWeek!=null)
                list.add("time_work.day_week = '" + dayOfWeek +"'");
            if(!fromTimeText.getText().equals("") && dayOfWeek!=null)
                list.add("time_work.from_time = '" + fromTimeText.getText() +"'");
            if(!upTimeText.getText().equals("") && dayOfWeek!=null)
                list.add("time_work.up_time = '" + upTimeText.getText() +"'");
            if(upTimeText.getText().equals("") && fromTimeText.getText().equals("") && dayOfWeek!=null)
                list.add("time_work.weekend = '1'");
            else if(dayOfWeek!=null)
                list.add("time_work.weekend = '0'");
            try{
                if(true){
                    if((orgServiceText.getText().equals("") && !priceText.getText().equals(""))){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Цена не может идти без услуги, попробуйте еще раз");
                        alert.show();
                    }
                    else {
                        SearchByExtraInfController controller = new SearchByExtraInfController(list);
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../view/SearchByExtraInformation.fxml"));
                        loader.setController(controller);
                        loader.load();
                        Parent root = loader.getRoot();
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Поиск организации и ее номеров по дополнительной информации");
                        Scene scene = new Scene(root);
                        dialogStage.setScene(scene);
                        dialogStage.showAndWait();
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Выберите день недели и заполните оставшиеся поля по желанию!");
                    alert.show();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void changeTimeTextFields(ActionEvent actionEvent) throws ParseException {
        if(weekendCheckBox.isSelected()){
            fromTimeText.setText("");
            upTimeText.setText("");
            fromTimeText.setVisible(false);
            upTimeText.setVisible(false);
        } else {
            fromTimeText.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("08:00")));
            upTimeText.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("22:00")));
            fromTimeText.setVisible(true);
            upTimeText.setVisible(true);
        }
    }
}
