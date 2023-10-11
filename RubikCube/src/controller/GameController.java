/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Face;
import model.PaneCube;
import model.PositionCube;

/**
 * FXML Controller class
 *
 * @author marti
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

    private Face face;
    private PaneCube paneCube;
    private PositionCube positionCube;
    Face auxFace;
    private int sides;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initilizeFaceCube();
        changeFaceCube();
        cuboMovement();
    }

    public void initilizeFaceCube() {
        face = new Face(Color.YELLOW);
        paneCube = new PaneCube();
        positionCube = new PositionCube();
        faceCuboBackground.getChildren().add(face);
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
                    int oldIndex = Arrays.asList(btnWhite, btnYellow, btnBlue, btnGreen, btnOrange, btnRed).indexOf(oldValue);
                    int newIndex = Arrays.asList(btnWhite, btnYellow, btnBlue, btnGreen, btnOrange, btnRed).indexOf(newValue);
                    paneCube.organize(oldIndex, newIndex);

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
                        }
                    }
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
                    //icone1 = new Image(getClass().getResourceAsStream("images/rotate_left.png"));
                    //icone2 = new Image(getClass().getResourceAsStream("images/rotate_right.png"));
                    if (newValue == radioBtn1) {
                        sides = 0;
                    } else if ((newValue == radioBtn2)) {
                        sides = 1;
                    } else if ((newValue == radioBtn3)) {
                        sides = 2;
                    }
                } else {
                    //icone1 = new Image(getClass().getResourceAsStream("/images/rotate_right.png"));
                    //icone2 = new Image(getClass().getResourceAsStream("/images/rotate_left.png"));

                    if (newValue == radioBtn4) {
                        sides = 3;
                    } else if ((newValue == radioBtn5)) {
                        sides = 4;
                    } else if ((newValue == radioBtn6)) {
                        sides = 5;
                    }
                }

                // btnLeftBottom.setGraphic(new ImageView(icone1));
                //btnRightTop.setGraphic(new ImageView(icone2));
            }
        });
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void moveLeftBottom(ActionEvent event) {
        System.out.println(sides);
        if (sides <= 2) {
            paneCube.edgesLeftRight(sides, 0);
        } else {
            paneCube.edgesTopBottom(sides, 0);
        }
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
    }

    @FXML
    private void moveRightTop(ActionEvent event) {
        System.out.println("Derecha:" + sides);
        if (sides <= 2) {
            paneCube.edgesLeftRight(sides, 1);
        } else {
            paneCube.edgesTopBottom(sides, 1);
        }
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
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
    }

    @FXML
    private void btnMix(ActionEvent event) {
        paneCube.mixCube();
        auxFace = paneCube.updateFace();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face.setColor((Color) auxFace.getMatrix()[i][j].getFill(), i, j);
            }
        }
    }

    void textColorPosition(Color color) {
        Map<Color, String[]> colorMappings = new HashMap<>();
        colorMappings.put(Color.WHITE, new String[]{"Atras", "Frontal", "Izquierda", "Derecha", "Abajo", "Arriba"});
        colorMappings.put(Color.YELLOW, new String[]{"Frontal", "Atras", "Derecha", "Izquierda", "Abajo", "Arriba"});
        colorMappings.put(Color.BLUE, new String[]{"Derecha", "Izquierda", "Frontal", "Atras", "Abajo", "Arriba"});
        colorMappings.put(Color.GREEN, new String[]{"Izquierda", "Derecha", "Frontal", "Atras", "Abajo", "Arriba"});
        colorMappings.put(Color.ORANGE, new String[]{"Abajo", "Arriba", "Frontal", "Atras", "Derecha", "Izquierda"});
        colorMappings.put(Color.RED, new String[]{"Abajo", "Arriba", "Derecha", "Izquierda", "Frontal", "Atras"});

        String[] labels = colorMappings.get(color);
        lab1.setText(labels[0]);
        lab2.setText(labels[1]);
        lab3.setText(labels[2]);
        lab4.setText(labels[3]);
        lab5.setText(labels[4]);
        lab6.setText(labels[5]);
    }
}
