package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class RootLayoutController {
    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
    //Help Menu button
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация о программе");
        alert.setHeaderText("Данная программа позволяет работать с базой данных phone_book");
        alert.show();
    }
    public void handleChangePage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ExtraInformation.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа с дополнительной информацией");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleOldNumbers(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/OldNumbers.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа со старыми номерами");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
