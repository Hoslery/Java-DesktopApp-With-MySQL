package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.model.Organization;
import sample.model.OrganizationDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeNumController implements Initializable {

    private String new_num;

    private String old_num;

    private String org_name;

    private Integer idOrg;

    @FXML
    private TextField newNumText;

    @FXML
    private Button okBtn;

    @FXML
    private TextField oldNumText;

    @FXML
    private TextField orgNameText;

    public ChangeNumController(String org_name, String old_num, Integer idOrg) {
        this.org_name = org_name;
        this.old_num = old_num;
        this.idOrg = idOrg;
    }

    //Search org by number
    @FXML
    private void changeNumber(Stage stage) throws SQLException, ClassNotFoundException {
        try {
            OrganizationDAO.changeNumber(old_num, new_num, stage, idOrg);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){
        try {
            orgNameText.setText(org_name);
            oldNumText.setText(old_num);

            newNumText.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    char c = event.getCharacter().charAt(0);
                    if ( !((c =='+') || (c =='(') || (c ==' ') || (c ==')') || (c =='-') || ((c>='0') && (c<='9'))) ) {
                        event.consume();
                    }
                }
            });

            okBtn.setOnAction(event -> {
                try {
                    String number = newNumText.getText();
                    char [] numberArr = number.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for(char num:numberArr){
                        if(num >= '0' && num<='9')
                            sb.append(num);
                    }
                    if(sb.length() >= 10 && sb.length()<=15) {
                        new_num = sb.toString();
                        if(new_num.equals(old_num)){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Ошибка!");
                            alert.setHeaderText(null);
                            alert.setContentText("Новый номер совпадает с текущим!\nПопробуйте еще раз");
                            alert.show();
                        } else {
                            Stage stage = (Stage) okBtn.getScene().getWindow();
                            changeNumber(stage);
                            stage.close();
                        }
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Новый номер введен неверно!\nНомер должен содержать минимум 10 цифр(максимум - 15 цифр).\nПопробуйте еще раз");
                        alert.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
