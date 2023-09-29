/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Button btnMix;
    @FXML
    private Button btnLeftBottom;
    @FXML
    private Button btnRightTop;

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
        colorToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    if (newValue == btnWhite && oldValue != null) {
                        auxFace = paneCube.getFace(Color.WHITE);
                        paneCube.whiteFace();
                        if (oldValue == btnYellow) {
                            paneCube.organize(1, 0);
                        } else if (oldValue == btnBlue) {
                            paneCube.organize(2, 0);
                        } else if (oldValue == btnGreen) {
                            paneCube.organize(3, 0);
                        } else if (oldValue == btnOrange) {
                            paneCube.organize(4, 0);
                        } else if (oldValue == btnRed) {
                            paneCube.organize(5, 0);
                        }

                    } else if (newValue == btnYellow) {
                        auxFace = paneCube.getFace(Color.YELLOW);
                        paneCube.yellowFace();
                        if (oldValue == btnWhite) {
                            paneCube.organize(0, 1);
                        } else if (oldValue == btnBlue) {
                            paneCube.organize(2, 1);
                        } else if (oldValue == btnGreen) {
                            paneCube.organize(3, 1);
                        } else if (oldValue == btnOrange) {
                            paneCube.organize(4, 1);
                        } else if (oldValue == btnRed) {
                            paneCube.organize(5, 1);
                        }

                    } else if (newValue == btnBlue) {
                        auxFace = paneCube.getFace(Color.BLUE);
                        paneCube.blueFace();
                        if (oldValue == btnWhite) {
                            paneCube.organize(0, 2);
                        } else if (oldValue == btnYellow) {
                            paneCube.organize(1, 2);
                        } else if (oldValue == btnGreen) {
                            paneCube.organize(3, 2);
                        } else if (oldValue == btnOrange) {
                            paneCube.organize(4, 2);
                        } else if (oldValue == btnRed) {
                            paneCube.organize(5, 2);
                        }

                    } else if (newValue == btnGreen) {
                        auxFace = paneCube.getFace(Color.GREEN);
                        paneCube.greenFace();
                        if (oldValue == btnWhite) {
                            paneCube.organize(0, 3);
                        } else if (oldValue == btnYellow) {
                            paneCube.organize(1, 3);
                        } else if (oldValue == btnBlue) {
                            paneCube.organize(2, 3);
                        } else if (oldValue == btnOrange) {
                            paneCube.organize(4, 3);
                        } else if (oldValue == btnRed) {
                            paneCube.organize(5, 3);
                        }

                    } else if (newValue == btnOrange) {
                        auxFace = paneCube.getFace(Color.ORANGE);
                        paneCube.orangeFace();
                        if (oldValue == btnYellow) {
                            paneCube.organize(1, 4);
                        } else if (oldValue == btnBlue) {
                            paneCube.organize(2, 4);
                        } else if (oldValue == btnGreen) {
                            paneCube.organize(3, 4);
                        } else if (oldValue == btnRed) {
                            paneCube.organize(5, 4);
                        } else if (oldValue == btnWhite) {
                            paneCube.organize(0, 4);
                        }
                    } else if (newValue == btnRed) {
                        auxFace = paneCube.getFace(Color.RED);
                        paneCube.redFace();
                        if (oldValue == btnYellow) {
                            paneCube.organize(1, 5);
                        } else if (oldValue == btnBlue) {
                            paneCube.organize(2, 5);
                        } else if (oldValue == btnGreen) {
                            paneCube.organize(3, 5);
                        } else if (oldValue == btnOrange) {
                            paneCube.organize(4, 5);
                        } else if (oldValue == btnWhite) {
                            paneCube.organize(0, 5);
                        }
                    }
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
                    } else if((newValue == radioBtn3)){
                        sides = 2;
                    }
                } else {
                    //icone1 = new Image(getClass().getResourceAsStream("/images/rotate_right.png"));
                    //icone2 = new Image(getClass().getResourceAsStream("/images/rotate_left.png"));

                    if (newValue == radioBtn4) {
                        sides = 3;
                    } else if ((newValue == radioBtn5)) {
                        sides = 4;
                    } else if((newValue == radioBtn6)){
                        sides = 5;
                    }
                }

                //btnLeftBottom.setGraphic(new ImageView(icone1));
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
        System.out.println("Derecha:"+ sides);
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
}
