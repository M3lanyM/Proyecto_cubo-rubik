/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Face;
import model.PaneCube;
import model.PositionCube;

/**
 * FXML Controller class
 *
 * @author amendoza, mmendoza, mjimenez, khernandez, jcastro
 */
public class GameController implements Initializable {

    @FXML
    private ToggleButton btnYellow;
    @FXML
    private ToggleButton btnWhite;
    @FXML
    private ToggleButton btnGreen;
    @FXML
    private ToggleButton btnBlue;
    @FXML
    private ToggleButton btnRed;
    @FXML
    private ToggleButton btnOrange;
    @FXML
    private RadioButton radioBtn1;
    @FXML
    private ToggleGroup rowColumnToggleGroup;
    @FXML
    private RadioButton radioBtn2;
    @FXML
    private RadioButton radioBtn3;
    @FXML
    private RadioButton radioBtn4;
    @FXML
    private RadioButton radioBtn5;
    @FXML
    private RadioButton radioBtn6;
    @FXML
    private StackPane faceCuboBackground;
    @FXML
    private Button btnSave;
    @FXML
    private ToggleGroup colorToggleGroup;
    @FXML
    private Button btnLeftBottom;
    @FXML
    private Button btnRightTop;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;
    @FXML
    private Label lab5;
    @FXML
    private Label lab6;
    @FXML
    private TextField playerName;
    @FXML
    private TextField numberMoves;

    private Face face;
    private PaneCube paneCube;
    private PositionCube positionCube;
    Face auxFace;
    private int sides;

    int movementCount = 0;
    private List<String> gameMoves = new ArrayList<>();
    int firtsList = 0;
    int endList;
    int auxPos = 1;

    @FXML
    private TextField gameTime;

    private int seconds = 0;
    private int minutes = 0;
    private Timeline timeline;

    public List<String> getGameMoves() {
        return gameMoves;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initilizeFaceCube();
        changeFaceCube();
        cuboMovement();
        //loadGameMovesFromFile("C:\\Users\\marti\\OneDrive\\Documents\\" + "martin" + ".txt");
        //  loadGameMovesFromFile("C:\\Users\\marti\\OneDrive\\Documents\\" + "martin" + ".txt");
        Image icone1 = new Image(getClass().getResourceAsStream("/Images/leftArrow.png"));
        btnLeftBottom.setGraphic(new ImageView(icone1));
        Image icone2 = new Image(getClass().getResourceAsStream("/Images/rightArrow.png"));
        btnRightTop.setGraphic(new ImageView(icone2));
    }

    public void initilizeFaceCube() {
        face = new Face(Color.YELLOW);
        paneCube = new PaneCube();
        positionCube = new PositionCube();
        faceCuboBackground.getChildren().add(face);
        gameMoves.add("Caras:" + 1);
        textColorPosition(Color.YELLOW);
    }

    //nuevo
    public void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimer() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }
        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        gameTime.setText(formattedTime);
    }

    public void setMovementCount(int movementCount) {
        this.movementCount = movementCount;
        numberMoves.setText(Integer.toString(movementCount));
    }

    public void setPlayerName(String name) {
        playerName.setText(name);
    }

    public void changeFaceCube() {
        Map<Toggle, Runnable> actionMap = new HashMap<>();
        actionMap.put(btnWhite, () -> {
            auxFace = paneCube.getFace(Color.WHITE);
            paneCube.whiteFace();
            textColorPosition(Color.WHITE);
        });
        actionMap.put(btnYellow, () -> {
            auxFace = paneCube.getFace(Color.YELLOW);
            paneCube.yellowFace();
            textColorPosition(Color.YELLOW);
        });
        actionMap.put(btnBlue, () -> {
            auxFace = paneCube.getFace(Color.BLUE);
            paneCube.blueFace();
            textColorPosition(Color.BLUE);
        });
        actionMap.put(btnGreen, () -> {
            auxFace = paneCube.getFace(Color.GREEN);
            paneCube.greenFace();
            textColorPosition(Color.GREEN);
        });
        actionMap.put(btnOrange, () -> {
            auxFace = paneCube.getFace(Color.ORANGE);
            paneCube.orangeFace();
            textColorPosition(Color.ORANGE);
        });
        actionMap.put(btnRed, () -> {
            auxFace = paneCube.getFace(Color.RED);
            paneCube.redFace();
            textColorPosition(Color.RED);
        });

        colorToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Runnable action = actionMap.get(newValue);
                if (action != null) {
                    action.run();
                    int newIndex = Arrays.asList(btnWhite, btnYellow, btnBlue, btnGreen, btnOrange, btnRed).indexOf(newValue);
                    int oldIndex = Arrays.asList(btnWhite, btnYellow, btnBlue, btnGreen, btnOrange, btnRed).indexOf(oldValue);
                    auxPos = oldIndex;
                    gameMoves.add("Caras:" + oldIndex);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
                        }
                    }
                    movementCount++;
                    numberMoves.setText(Integer.toString(movementCount));
                }
            }
        });
    }

    public void cuboMovement() {
        rowColumnToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                Image icone1, icone2;
                if (newValue == radioBtn1 || newValue == radioBtn2 || newValue == radioBtn3) {
                    icone1 = new Image(getClass().getResourceAsStream("/Images/leftArrow.png"));
                    icone2 = new Image(getClass().getResourceAsStream("/Images/rightArrow.png"));
                    if (newValue == radioBtn1) {
                        sides = 0;
                    } else if ((newValue == radioBtn2)) {
                        sides = 1;
                    } else if ((newValue == radioBtn3)) {
                        sides = 2;
                    }
                } else {
                    icone1 = new Image(getClass().getResourceAsStream("/Images/upArrow.png"));
                    icone2 = new Image(getClass().getResourceAsStream("/Images/downArrow.png"));

                    if (newValue == radioBtn4) {
                        sides = 3;
                    } else if ((newValue == radioBtn5)) {
                        sides = 4;
                    } else if ((newValue == radioBtn6)) {
                        sides = 5;
                    }
                }

                btnLeftBottom.setGraphic(new ImageView(icone1));
                btnRightTop.setGraphic(new ImageView(icone2));
            }
        });
    }

    @FXML
    private void moveLeftBottom(ActionEvent event) {
        System.out.println(sides);
        if (sides <= 2) {
            paneCube.edgesLeftRight(sides, 0);
            gameMoves.add("izquierda:" + sides);
        } else {
            paneCube.edgesTopBottom(sides, 0);
            gameMoves.add("abajo:" + sides);
        }
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
        movementCount++;
        numberMoves.setText(Integer.toString(movementCount));
    }

    @FXML
    private void moveRightTop(ActionEvent event) {
        System.out.println("Derecha:" + sides);
        if (sides <= 2) {
            paneCube.edgesLeftRight(sides, 1);
            gameMoves.add("derecha:" + sides);
        } else {
            paneCube.edgesTopBottom(sides, 1);
            gameMoves.add("arriba:" + sides);
        }
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
        movementCount++;
        numberMoves.setText(Integer.toString(movementCount));
    }

    @FXML
    private void btnAntiSchedule(ActionEvent event) {
        paneCube.rotation(0);
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
        gameMoves.add("antiHora");
        movementCount++;
        numberMoves.setText(Integer.toString(movementCount));
    }

    @FXML
    private void btnSchedule(ActionEvent event) {
        paneCube.rotation(1);
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
        gameMoves.add("Horario");
        movementCount++;
        numberMoves.setText(Integer.toString(movementCount));
    }
//mezclar

    @FXML
    private void btnMix(ActionEvent event) {
        //Se debe de agregar una lista para guardar los movimientos creados
        System.out.println("Mezclado");
        Random generator = new Random();
        for (int i = 0; i < 15; i++) {
            int number = generator.nextInt(5);
            int aux = number;

            number = generator.nextInt(14);
            if (number == 0) {
                paneCube.edgesLeftRight(0, 0);
                gameMoves.add("izquierda:" + 0);
            } else if (number == 1) {
                paneCube.edgesLeftRight(1, 0);
                gameMoves.add("izquierda:" + 1);
            } else if (number == 2) {
                paneCube.edgesLeftRight(2, 0);
                gameMoves.add("izquierda:" + 2);
            } else if (number == 3) {
                paneCube.edgesLeftRight(0, 1);
                gameMoves.add("derecha:" + 0);
            } else if (number == 4) {
                paneCube.edgesLeftRight(1, 1);
                gameMoves.add("derecha:" + 1);
            } else if (number == 5) {
                paneCube.edgesLeftRight(2, 1);
                gameMoves.add("derecha:" + 2);
//////////////////////////////////////////////////////////////////////////////
            } else if (number == 6) {
                paneCube.edgesTopBottom(3, 1);
                gameMoves.add("arriba:" + 3);
            } else if (number == 7) {
                paneCube.edgesTopBottom(4, 1);
                gameMoves.add("arriba:" + 4);
            } else if (number == 8) {
                paneCube.edgesTopBottom(5, 1);
                gameMoves.add("arriba:" + 5);
            } else if (number == 9) {
                paneCube.edgesTopBottom(3, 0);
                gameMoves.add("abajo:" + 3);
            } else if (number == 10) {
                paneCube.edgesTopBottom(4, 0);
                gameMoves.add("abajo:" + 4);
            } else if (number == 11) {
                paneCube.edgesTopBottom(5, 0);
                gameMoves.add("abajo:" + 5);
///////////////////////////////////////////////////////////////////////////////
            } else if (number == 12) {
                paneCube.rotation(0);
                gameMoves.add("antiHora:");
            } else if (number == 13) {
                paneCube.rotation(1);
                gameMoves.add("Horario");
            }
        }
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
        movementCount++;
        numberMoves.setText(Integer.toString(movementCount));
    }
//Pone los textos de posiciones
    void textColorPosition(Color color) {
        Map<Color, String[]> colorMappings = new HashMap<>();
        colorMappings.put(Color.WHITE, new String[]{"Atras", "Frontal", "Izquierda", "Derecha", "Abajo", "Arriba"});
        colorMappings.put(Color.YELLOW, new String[]{"Frontal", "Atras", "Derecha", "Izquierda", "Abajo", "Arriba"});
        colorMappings.put(Color.BLUE, new String[]{"Derecha", "Izquierda", "Atras", "Frontal", "Abajo", "Arriba"});
        colorMappings.put(Color.GREEN, new String[]{"Izquierda", "Derecha", "Frontal", "Atras", "Abajo", "Arriba"});
        colorMappings.put(Color.ORANGE, new String[]{"Abajo", "Arriba", "Derecha", "Izquierda", "Atras", "Frontal"});
        colorMappings.put(Color.RED, new String[]{"Abajo", "Arriba", "Izquierda", "Derecha", "Frontal", "Atras"});

        String[] labels = colorMappings.get(color);
        lab1.setText(labels[0]);
        lab2.setText(labels[1]);
        lab3.setText(labels[2]);
        lab4.setText(labels[3]);
        lab5.setText(labels[4]);
        lab6.setText(labels[5]);
    }

//Guarda en txt
    @FXML
    private void saveGame(ActionEvent event) {
        try {
            String playerNameValue = playerName.getText();
            String fileRecord = "C:\\Users\\melan\\Pictures\\Screenshots\\Record.txt"; // Ruta 
            FileWriter writers = new FileWriter(fileRecord, true);
            BufferedWriter bufferedWriters = new BufferedWriter(writers);
            bufferedWriters.write("Nombre del Jugador: " + playerNameValue);
            bufferedWriters.newLine();
            bufferedWriters.write("Número de Movimientos: " + gameMoves.size());
            bufferedWriters.newLine();

            // Guarda el tiempo de la partida
            bufferedWriters.write("Tiempo de la Partida: " + gameTime.getText());
            bufferedWriters.newLine();
            bufferedWriters.close();

            String filePath = "C:\\Users\\melan\\Pictures\\Screenshots\\" + playerName.getText() + ".txt"; // Ruta 
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Nombre del Jugador: " + playerNameValue);
            bufferedWriter.newLine();
            bufferedWriter.write("Número de Movimientos: " + gameMoves.size());
            bufferedWriter.newLine();

            // Guarda el tiempo de la partida
            bufferedWriter.write("Tiempo de la Partida: " + gameTime.getText());
            bufferedWriter.newLine();

            // Recorre la lista de movimientos y escribe cada movimiento en el archivo
            for (String movimiento : gameMoves) {
                bufferedWriter.write(movimiento);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Información y movimientos guardados en " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//Resulve el cubo
    @FXML
    private void cubeSolve(ActionEvent event) {
        int cont = 0;
        if (gameMoves.size() != 0) {
            for (endList = gameMoves.size() - 1; endList >= 0; endList--) {
                String currentMove = gameMoves.get(endList);
                System.out.println(currentMove);
                if (currentMove.startsWith("izquierda:")) {   // Es un movimiento "izquierda:X"
                    int value = Integer.parseInt(currentMove.substring(10));
                    cont++;
                    chageRSave(value);
                } else if (currentMove.startsWith("Caras:")) {
                    int value = Integer.parseInt(currentMove.substring(6));
                    cont++;
                    chageFacesSave(value);
                } else if (currentMove.startsWith("derecha:")) {
                    int value = Integer.parseInt(currentMove.substring(8));
                    cont++;
                    chageLSave(value);
                } else if (currentMove.startsWith("arriba:")) {
                    int value = Integer.parseInt(currentMove.substring(7));
                    cont++;
                    chageBSave(value);
                } else if (currentMove.startsWith("abajo:")) {
                    int value = Integer.parseInt(currentMove.substring(6));
                    cont++;
                    chageTSave(value);
                } else if (currentMove.startsWith("antiHora")) {
                    cont++;
                    chageH();
                } else if (currentMove.startsWith("Horario")) {
                    cont++;
                    chageAH();
                }
            }
            System.out.println("cont:" + cont);
        } else {
            System.out.println("No hay movimientos.");
        }
    }

    //cambie
    void loadGameMovesFromFile(String filePath) {
        int lineCount = 0; 
        gameMoves.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineCount > 1) {
                    gameMoves.add(line);
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameTimeFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Tiempo de la Partida: ")) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String[] timeParts = parts[1].split(":");
                        if (timeParts.length == 2) {
                            minutes = Integer.parseInt(timeParts[0]);
                            seconds = Integer.parseInt(timeParts[1]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ordenar el cubo con los movimientos para continuar
    @FXML
    public void continueCubeSolve() {
        for (int i = 0; i < gameMoves.size(); i++) {
            String currentMove = gameMoves.get(i);
            if (currentMove.startsWith("izquierda:")) {
                int value = Integer.parseInt(currentMove.substring(10));
                chageLSave(value);
            } else if (currentMove.startsWith("Caras:")) {
                int value = Integer.parseInt(currentMove.substring(6));
                chageFacesSave(value);
            } else if (currentMove.startsWith("derecha:")) {
                int value = Integer.parseInt(currentMove.substring(8));
                chageRSave(value);
            } else if (currentMove.startsWith("arriba:")) {
                int value = Integer.parseInt(currentMove.substring(7));
                chageTSave(value);
            } else if (currentMove.startsWith("abajo:")) {
                int value = Integer.parseInt(currentMove.substring(6));
                chageBSave(value);
            } else if (currentMove.startsWith("antiHora")) {
                chageAH();
            } else if (currentMove.startsWith("Horario")) {
                chageH();
            }
        }
    }

    //cambia las posiciones  
    public void chageFacesSave(int x) {
        if (x == 0) {
            paneCube.whiteFace();
        } else if (x == 1) {
            paneCube.yellowFace();
        } else if (x == 2) {
            paneCube.blueFace();
        } else if (x == 3) {
            paneCube.greenFace();
        } else if (x == 4) {
            paneCube.orangeFace();
        } else if (x == 5) {
            paneCube.redFace();
        }
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
    }

    //Gira izquieda
    public void chageLSave(int x) {
        paneCube.edgesLeftRight(x, 0);
        //actualiza
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }

    }
    
    //Gira derecha
    public void chageRSave(int x) {
        paneCube.edgesLeftRight(x, 1);
        //actualiza
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }

    }
    
    //Gira arriba
    public void chageTSave(int x) {
        paneCube.edgesTopBottom(x, 1);
        //actualiza
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
    }

    //Gira abajo
    public void chageBSave(int x) {
        paneCube.edgesTopBottom(x, 0);
        //actualiza
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
    }

    //Gira en sentido horario
    public void chageH() {
        paneCube.rotation(1);
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
    }

    //Gira en sentido antihorario
    public void chageAH() {
        paneCube.rotation(0);
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }

    }

    @FXML
    private void exitMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
            Parent root = loader.load();
            MenuController menuController = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Juego del Cubo Rubik");
            stage.setScene(new Scene(root));
            stage.show();

            Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gameStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
