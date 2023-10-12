/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Ashley Mendoza V
 */
public class PaneCube extends GridPane {

    public final PositionCube positions;
    private final Face[] faces;
    private Color color, color2, color3, color4;
    private Face faceTemp;// para realizar la evaluacion de las caras sin afectar las originales
    public Face pane;

    public PaneCube() {
        this.positions = new PositionCube();
        this.faces = new Face[6];
        faces[0] = new Face(Color.WHITE);
        faces[1] = new Face(Color.YELLOW);
        faces[2] = new Face(Color.BLUE);
        faces[3] = new Face(Color.GREEN);
        faces[4] = new Face(Color.ORANGE);
        faces[5] = new Face(Color.RED);
        inicializar();
    }

    private void inicializar() {

        for (int i = 0; i < 6; i++) {
            getChildren().add(faces[i]);
        }
        yellowFace();
    }

    public Face updateFace() {

        return positions.getFront();
    }

    public void whiteFace() {
        positions.setFront(faces[0]);
        positions.setBack(faces[1]);
        positions.setRight(faces[2]);
        positions.setLeft(faces[3]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);

    }

    public void yellowFace() {
        positions.setFront(faces[1]);
        positions.setBack(faces[0]);
        positions.setRight(faces[3]);
        positions.setLeft(faces[2]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
    }

    public void greenFace() {
        positions.setFront(faces[3]);
        positions.setBack(faces[2]);
        positions.setRight(faces[0]);
        positions.setLeft(faces[1]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
    }

    public void blueFace() {
        positions.setFront(faces[2]);
        positions.setBack(faces[3]);
        positions.setRight(faces[1]);
        positions.setLeft(faces[0]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
    }

    public void redFace() {
        positions.setFront(faces[5]);
        positions.setBack(faces[4]);
        positions.setRight(faces[2]);
        positions.setLeft(faces[3]);
        positions.setTop(faces[0]);
        positions.setBottom(faces[1]);
    }

    public void orangeFace() {
        positions.setFront(faces[4]);
        positions.setBack(faces[5]);
        positions.setRight(faces[3]);
        positions.setLeft(faces[2]);
        positions.setTop(faces[0]);
        positions.setBottom(faces[1]);
    }

    public Face getFace(Color color) {
        if (color == Color.WHITE) {
            return faces[0];
        } else if (color == Color.YELLOW) {
            return faces[1];
        } else if (color == Color.BLUE) {
            return faces[2];
        } else if (color == Color.GREEN) {
            return faces[3];
        } else if (color == Color.ORANGE) {
            return faces[4];
        } else if (color == Color.RED) {
            return faces[5];
        } else {
            return null;
        }
    }

    public void turnLeftRow(int row) {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getFront().rectangles[i][row].getFill();
            positions.getFront().setColor((Color) positions.getRight().rectangles[i][row].getFill(), i, row);
            color2 = (Color) positions.getLeft().rectangles[i][row].getFill();
            color3 = (Color) positions.getBack().rectangles[i][row].getFill();
            positions.getLeft().setColor((Color) color, i, row);
            positions.getBack().setColor((Color) color2, i, row);
            positions.getRight().setColor((Color) color3, i, row);
        }
    }

    public void turnRightRow(int row) {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getFront().rectangles[i][row].getFill();
            positions.getFront().setColor((Color) positions.getLeft().rectangles[i][row].getFill(), i, row);
            color2 = (Color) positions.getRight().rectangles[i][row].getFill();
            color3 = (Color) positions.getBack().rectangles[i][row].getFill();
            positions.getRight().setColor((Color) color, i, row);
            positions.getBack().setColor((Color) color2, i, row);
            positions.getLeft().setColor((Color) color3, i, row);
        }
    }

    public void turnTopColum(int row) {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getFront().rectangles[row][i].getFill();
            positions.getFront().setColor((Color) positions.getBottom().rectangles[row][i].getFill(), row, i);
            color2 = (Color) positions.getTop().rectangles[row][i].getFill();
            color3 = (Color) positions.getBack().rectangles[row][i].getFill();
            positions.getTop().setColor((Color) color, row, i);
            positions.getBack().setColor((Color) color2, row, i);
            positions.getBottom().setColor((Color) color3, row, i);
        }
    }

    public void turnBottomColum(int row) {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getFront().rectangles[row][i].getFill();
            positions.getFront().setColor((Color) positions.getTop().rectangles[row][i].getFill(), row, i);
            color2 = (Color) positions.getBottom().rectangles[row][i].getFill();
            color3 = (Color) positions.getBack().rectangles[row][i].getFill();
            positions.getBottom().setColor((Color) color, row, i);
            positions.getBack().setColor((Color) color2, row, i);
            positions.getTop().setColor((Color) color3, row, i);
        }
    }

    public void turnFaceAntiSchedule(Face face) {
        //Mueve color esquinas
        color = (Color) face.rectangles[0][0].getFill();
        color2 = (Color) face.rectangles[2][0].getFill();
        color3 = (Color) face.rectangles[0][2].getFill();
        color4 = (Color) face.rectangles[2][2].getFill();

        face.setColor(color, 0, 2);
        face.setColor(color2, 0, 0);
        face.setColor(color3, 2, 2);
        face.setColor(color4, 2, 0);

        //mueve color del centro
        color = (Color) face.rectangles[0][1].getFill();
        color2 = (Color) face.rectangles[1][0].getFill();
        color3 = (Color) face.rectangles[1][2].getFill();
        color4 = (Color) face.rectangles[2][1].getFill();

        face.setColor(color, 1, 2);
        face.setColor(color2, 0, 1);
        face.setColor(color3, 2, 1);
        face.setColor(color4, 1, 0);
    }

    public void turnFaceSchedule(Face face) {
        //Mueve color esquinas
        color = (Color) face.rectangles[0][0].getFill();
        color2 = (Color) face.rectangles[0][2].getFill();
        color3 = (Color) face.rectangles[2][0].getFill();
        color4 = (Color) face.rectangles[2][2].getFill();

        face.setColor(color, 2, 0);
        face.setColor(color2, 0, 0);
        face.setColor(color3, 2, 2);
        face.setColor(color4, 0, 2);

        //mueve color del centro
        color = (Color) face.rectangles[1][0].getFill();
        color2 = (Color) face.rectangles[0][1].getFill();
        color3 = (Color) face.rectangles[2][1].getFill();
        color4 = (Color) face.rectangles[1][2].getFill();

        face.setColor(color, 2, 1);
        face.setColor(color2, 1, 0);
        face.setColor(color3, 1, 2);
        face.setColor(color4, 0, 1);
    }

    public void swapCorners(Face face, int colum) {
        if (colum == 0) {
            color = (Color) face.rectangles[0][0].getFill();
            color2 = (Color) face.rectangles[0][2].getFill();

            face.setColor(color, 0, 2);
            face.setColor(color2, 0, 0);

        } else if (colum == 2) {

            color = (Color) face.rectangles[2][0].getFill();
            color2 = (Color) face.rectangles[2][2].getFill();

            face.setColor(color, 2, 2);
            face.setColor(color2, 2, 0);

        }
    }

    public void rotationCornersFaceAntiSchedule() {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getBottom().rectangles[i][0].getFill();
            color2 = (Color) positions.getRight().rectangles[0][i].getFill();
            color3 = (Color) positions.getTop().rectangles[i][2].getFill();
            color4 = (Color) positions.getLeft().rectangles[2][i].getFill();
            positions.getRight().setColor((Color) color, 0, i);
            positions.getTop().setColor((Color) color2, i, 2);
            positions.getLeft().setColor((Color) color3, 2, i);
            positions.getBottom().setColor((Color) color4, i, 0);

        }
    }

    public void rotationCornersFaceSchedule() {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getBottom().rectangles[i][0].getFill();
            color2 = (Color) positions.getRight().rectangles[0][i].getFill();
            color3 = (Color) positions.getTop().rectangles[i][2].getFill();
            color4 = (Color) positions.getLeft().rectangles[2][i].getFill();
            positions.getRight().setColor((Color) color3, 0, i);
            positions.getTop().setColor((Color) color4, i, 2);
            positions.getLeft().setColor((Color) color, 2, i);
            positions.getBottom().setColor((Color) color2, i, 0);

        }
    }

    public void edgesLeftRight(int sides, int line) {
        if (line == 0) {
            if (sides == 0) {
                turnLeftRow(0);
                turnFaceSchedule(positions.getTop());
            } else if (sides == 1) {
                turnLeftRow(0);
                turnLeftRow(2);
                turnFaceAntiSchedule(positions.getBottom());
                turnFaceSchedule(positions.getTop());
            } else if (sides == 2) {
                turnLeftRow(2);
                turnFaceAntiSchedule(positions.getBottom());
            }
        } else {
            if (sides == 0) {
                turnRightRow(0);
                turnFaceAntiSchedule(positions.getTop());
            } else if (sides == 1) {
                turnRightRow(0);
                turnRightRow(2);
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            } else if (sides == 2) {
                turnRightRow(2);
                turnFaceSchedule(positions.getBottom());
            }

        }
    }

    public void edgesTopBottom(int sides, int line) {
        if (line == 0) {
            if (sides == 3) {
                turnTopColum(0);
                turnFaceAntiSchedule(positions.getLeft());
            } else if (sides == 4) {
                turnBottomColum(0);
                turnBottomColum(2);
                turnFaceSchedule(positions.getLeft());
                turnFaceAntiSchedule(positions.getRight());
            } else if (sides == 5) {
                turnTopColum(2);
                turnFaceSchedule(positions.getRight());
            }

        } else {
            if (sides == 3) {
                turnBottomColum(0);
                turnFaceSchedule(positions.getLeft());
            } else if (sides == 4) {
                turnTopColum(0);
                turnTopColum(2);
                turnFaceAntiSchedule(positions.getLeft());
                turnFaceSchedule(positions.getRight());
            } else if (sides == 5) {
                turnBottomColum(2);
                turnFaceAntiSchedule(positions.getRight());
            }
        }
    }

    public void rotation(int op) {
        if (op == 0) {
            turnFaceAntiSchedule(positions.getFront());
            rotationCornersFaceAntiSchedule();
            swapCorners(positions.getLeft(), 2);
            swapCorners(positions.getRight(), 0);
        } else if (op == 1) {
            swapCorners(positions.getLeft(), 2);
            swapCorners(positions.getRight(), 0);
            turnFaceSchedule(positions.getFront());
            rotationCornersFaceSchedule();
        }
    }

    public void mixCube() {
        
    }

}
