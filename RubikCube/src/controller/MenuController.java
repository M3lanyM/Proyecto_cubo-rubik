/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amendoza, mmendoza, mjimenez, khernandez, jcastro
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

        if (result.isPresent() && !result.get().isEmpty()) { 
            String playerName = result.get();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
                Parent root = loader.load();

                // Obtener una referencia al controlador del cubo
                GameController gameController = loader.getController();

                // Asignar el nombre del jugador al controlador del cubo
                gameController.setPlayerName(playerName);

                // Inicia el cronómetro
                gameController.startTimer();

                Stage stage = new Stage();
                stage.setTitle("Juego del Cubo Rubik");
                stage.setScene(new Scene(root));
                stage.show();

                Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                menuStage.close();

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

    //nuevo
    @FXML
    private void continueGame(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cargar partida");
        dialog.setHeaderText(null);
        dialog.setContentText("Por favor, ingrese su nombre:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && !result.get().isEmpty()) {
            String playerName = result.get();
            String filePath = "C:\\Users\\melan\\Pictures\\Screenshots\\" + playerName + ".txt"; // Ruta y nombre del archivo personalizado

            File file = new File(filePath);

            if (file.exists()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
                    Parent root = loader.load();

                    GameController gameController = loader.getController();
                    gameController.setPlayerName(playerName);
                    gameController.loadGameMovesFromFile(filePath); // Cargar los movimientos desde el archivo
                    gameController.continueCubeSolve(); // Aplicar los movimientos guardados

                    
                    // numero de movimientos
                    gameController.setMovementCount(gameController.getGameMoves().size() - 1);

                    // Cargar el tiempo de la partida y actualizar el cronómetro
                    gameController.loadGameTimeFromFile(filePath);

                    // Inicia el cronómetro
                    gameController.startTimer();

                    Stage stage = new Stage();
                    stage.setTitle("Juego del Cubo Rubik");
                    stage.setScene(new Scene(root));
                    stage.show();

                    Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    menuStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("No se encontró una partida guardada con ese nombre.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El campo de nombre no puede estar vacío. Por favor, ingrese su nombre.");
            alert.showAndWait();
        }
    }

    @FXML
    private void instructionMessage(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instrucciones para Jugar con el Cubo Rubik");
        alert.setHeaderText(null);
        alert.setContentText("Instrucciones para jugar con el Cubo Rubik:\n\n"
                + "1. Objetivo: El objetivo del juego es resolver el Cubo Rubik, de modo que cada cara del cubo tenga un solo color.\n"
                + "2. Girar las caras: Utiliza los botones para cambiar los colores de las caras del cubo. Cada botón representa un color diferente.\n"
                + "3. Girar verticalmente: Utiliza los botones de giro vertical para rotar las filas del cubo hacia arriba o abajo.\n"
                + "4. Girar horizontalmente: Utiliza los botones de giro horizontal para rotar las columnas del cubo hacia la izquierda o la derecha.\n"
                + "5. Resolución: Intenta resolver el cubo haciendo coincidir los colores en cada cara. Puede ser un desafío, ¡así que ten paciencia!\n"
                + "6. ¡Diviértete! Jugar con el Cubo Rubik es un desafío divertido y satisfactorio.");

        alert.showAndWait();
    }

    @FXML
    private void Records(ActionEvent event) {
        try {
            String fileRecord = "C:\\Users\\melan\\Pictures\\Screenshots\\Record.txt";
            File recordsFile = new File(fileRecord);

            if (!recordsFile.exists()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Records");
                alert.setHeaderText("No se encontraron records");
                alert.setContentText("Aún no se han guardado records");
                alert.showAndWait();
                return;
            }

            List<String> records = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileRecord))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    records.add(line);
                }
            }

            String lowestPlayer = null;
            int lowestMovements = Integer.MAX_VALUE;
            String lowestTime = null;

            for (int i = 0; i < records.size(); i += 3) {
                String playerName = records.get(i);
                int movements = Integer.parseInt(records.get(i + 1).replace("Número de Movimientos: ", ""));
                String time = records.get(i + 2).replace("Tiempo de la Partida: ", "");

                if (movements < lowestMovements) {
                    lowestPlayer = playerName;
                    lowestMovements = movements;
                    lowestTime = time;
                }
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Records");
            alert.setHeaderText("Récord del movimiento y tiempo más bajos:");
            alert.setContentText(lowestPlayer + "\nMovimientos: " + lowestMovements + "\nTiempo: " + lowestTime);
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se produjo un error al leer los records.");
            alert.showAndWait();
        }
    }
}
