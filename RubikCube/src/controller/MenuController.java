/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marti
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void startGame(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingrese su nombre");
        dialog.setHeaderText(null);
        dialog.setContentText("Por favor, ingrese su nombre:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && !result.get().isEmpty()) { // Verificar si se ingresó un nombre no vacío
            String playerName = result.get();

            try {
                // Cargar la vista del cubo desde su archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
                Parent root = loader.load();

                // Obtener una referencia al controlador del cubo
                GameController gameController = loader.getController();

                // Asignar el nombre del jugador al controlador del cubo
                gameController.setPlayerName(playerName);

                // Mostrar la vista del cubo en una nueva ventana
                Stage stage = new Stage();
                stage.setTitle("Juego del Cubo Rubik");
                stage.setScene(new Scene(root));
                stage.show();
                Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                gameStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Muestra una advertencia si el campo de nombre está vacío
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El campo de nombre no puede estar vacío. Por favor, ingrese su nombre.");
            alert.showAndWait();
        }
    }

    @FXML
    private void continueGame(ActionEvent event) {
    }
    
}
