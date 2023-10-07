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

    //Carga las caras del cubo en la pantalla al inicar
    public void whiteFace() {
        positions.setFront(faces[0]);
        positions.setBack(faces[1]);
        positions.setRight(faces[2]);
        positions.setLeft(faces[3]);
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

    public void yellowFace() {
        positions.setFront(faces[1]);
        positions.setBack(faces[0]);
        positions.setRight(faces[3]);
        positions.setLeft(faces[2]);
        positions.setTop(faces[5]);
        positions.setBottom(faces[4]);
    }

    public void blueFace() {
        positions.setFront(faces[2]);
        positions.setBack(faces[3]);
        positions.setRight(faces[4]);
        positions.setLeft(faces[5]);
        positions.setTop(faces[0]);
        positions.setBottom(faces[1]);
    }

    public void greenFace() {
        positions.setFront(faces[3]);
        positions.setBack(faces[2]);
        positions.setRight(faces[5]);
        positions.setLeft(faces[4]);
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

    public void organize(int previous, int current) {
        if (previous == 0) {//blanco
            if (current == 1) {//amarillo
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 2) {//azul
                turnFaceSchedule(positions.getBottom());
                turnFaceAntiSchedule(positions.getBottom());
            } else if (current == 3) {//verde
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 4) {//anaranjado
                turnFaceAntiSchedule(positions.getRight());
                turnFaceSchedule(positions.getLeft());
            } else if (current == 5) {//rojo
                turnFaceAntiSchedule(positions.getLeft());
                turnFaceSchedule(positions.getRight());
            }
///////////////////////////////////////////////////////////////////////////////
        } else if (previous == 1) {//amarillo
            if (current == 0) {//blanco
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 2) {//azul
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 3) {//verde
                turnFaceSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getBottom());
            } else if (current == 4) {//anaranjado
                turnFaceAntiSchedule(positions.getRight());
                turnFaceSchedule(positions.getLeft());
            } else if (current == 5) {//rojo
                turnFaceSchedule(positions.getRight());
                turnFaceAntiSchedule(positions.getLeft());
            }
////////////////////////////////////////////////////////////////////////////////
        } else if (previous == 2) {//azul
            if (current == 0) {//blanco
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 1) {//amarillo
                turnFaceSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getBottom());
            } else if (current == 3) {//verde
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 4) {//anaranjado
                turnFaceAntiSchedule(positions.getRight());
                turnFaceSchedule(positions.getLeft());
            } else if (current == 5) {//rojo
                turnFaceSchedule(positions.getRight());
                turnFaceAntiSchedule(positions.getLeft());
            }
////////////////////////////////////////////////////////////////////////////////
        } else if (previous == 3) {//verde
            if (current == 0) {//blanco
                turnFaceSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getBottom());
            } else if (current == 1) {//blanco
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 2) {//azul
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 4) {//anaranjado
                turnFaceAntiSchedule(positions.getRight());
                turnFaceSchedule(positions.getLeft());
            } else if (current == 5) {//rojo
                turnFaceSchedule(positions.getRight());
                turnFaceAntiSchedule(positions.getLeft());
            }
////////////////////////////////////////////////////////////////////////////////
        } else if (previous == 4) {//anaranjado
            if (current == 0) {//blanco
                turnFaceSchedule(positions.getLeft());
                turnFaceAntiSchedule(positions.getRight());
            } else if (current == 1) {//amarillo
                turnFaceAntiSchedule(positions.getRight());
                turnFaceSchedule(positions.getLeft());
            } else if (current == 2) {//azul
                turnFaceSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getBottom());
            } else if (current == 5) {//rojo
                turnFaceAntiSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 3) {//verde
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            }
////////////////////////////////////////////////////////////////////////////////
        } else if (previous == 5) {//rojo
            if (current == 0) {//branco
                turnFaceSchedule(positions.getLeft());
                turnFaceAntiSchedule(positions.getRight());
            } else if (current == 1) {//amarillo
                turnFaceAntiSchedule(positions.getLeft());
                turnFaceSchedule(positions.getRight());
            } else if (current == 2) {//azul
                turnFaceSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getBottom());
            } else if (current == 4) {//anaranjado
                turnFaceAntiSchedule(positions.getTop());
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
                turnFaceSchedule(positions.getBottom());
            } else if (current == 3) {//verde
                turnFaceAntiSchedule(positions.getTop());
                turnFaceSchedule(positions.getBottom());
            }
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

    public void turnTopColum(int row, int auxBack) {
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getFront().rectangles[row][i].getFill();
            positions.getFront().setColor((Color) positions.getBottom().rectangles[row][i].getFill(), row, i);
            color2 = (Color) positions.getTop().rectangles[row][i].getFill();
            color3 = (Color) positions.getBack().rectangles[auxBack][i].getFill();
            positions.getTop().setColor((Color) color, row, i);
            positions.getBack().setColor((Color) color2, auxBack, i);
            positions.getBottom().setColor((Color) color3, row, i);
        }
    }

    public void turnBottomColum(int row, int auxBack) {
         System.out.println("BUeno1-1");
        for (int i = 0; i < 3; i++) {
            color = (Color) positions.getFront().rectangles[row][i].getFill();
            positions.getFront().setColor((Color) positions.getTop().rectangles[row][i].getFill(), row, i);
            color2 = (Color) positions.getBottom().rectangles[row][i].getFill();
            color3 = (Color) positions.getBack().rectangles[auxBack][i].getFill();
            positions.getBottom().setColor((Color) color, row, i);
            positions.getBack().setColor((Color) color2, auxBack, i);
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
                turnTopColum(0, 2);
                swapCorners(positions.getBottom(), 0);
                turnFaceAntiSchedule(positions.getLeft());
                swapCorners(positions.getBack(), 2);
            } else if (sides == 4) {
                turnBottomColum(0, 2);
                turnBottomColum(2, 0);
                turnFaceSchedule(positions.getLeft());
                turnFaceAntiSchedule(positions.getRight());
                swapCorners(positions.getTop(), 0);
                swapCorners(positions.getTop(), 2);
                swapCorners(positions.getBack(), 0);
                swapCorners(positions.getBack(), 2);
            } else if (sides == 5) {
                turnTopColum(2, 0);
                turnFaceSchedule(positions.getRight());
                swapCorners(positions.getBottom(), 2);
                swapCorners(positions.getBack(), 0);

            }

        } else {
            if (sides == 3) {
                turnBottomColum(0, 2);
                turnFaceSchedule(positions.getLeft());
                swapCorners(positions.getTop(), 0);
                swapCorners(positions.getBack(), 2);
            } else if (sides == 4) {

                turnTopColum(0, 2);
                turnTopColum(2, 0);
                turnFaceAntiSchedule(positions.getLeft());
                turnFaceSchedule(positions.getRight());
                swapCorners(positions.getBottom(), 0);
                swapCorners(positions.getBottom(), 2);
                swapCorners(positions.getBack(), 0);
                swapCorners(positions.getBack(), 2);
            } else if (sides == 5) {
                turnBottomColum(2, 0);
                turnFaceAntiSchedule(positions.getRight());
                swapCorners(positions.getTop(), 2);
                swapCorners(positions.getBack(), 0);

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
        //Se debe de agregar una lista para guardar los movimientos creados
        System.out.println("Mezclado");
        Random generator = new Random();
        for (int i = 0; i < 100; i++) {
            int number = generator.nextInt(5);

            if (number == 0) {
                whiteFace();
            } else if (number == 1) {
                yellowFace();
            } else if (number == 2) {
                blueFace();
            } else if (number == 3) {
                greenFace();
            } else if (number == 4) {
                orangeFace();
            } else if (number == 5) {
                redFace();
            }

            number = generator.nextInt(14);
            if (number == 0) {
                edgesLeftRight(0, 0);
            } else if (number == 1) {
                edgesLeftRight(0, 1);
            } else if (number == 2) {
                edgesLeftRight(0, 2);
            } else if (number == 3) {
                edgesLeftRight(2, 0);
            } else if (number == 4) {
                edgesLeftRight(2, 1);
            } else if (number == 5) {
                edgesLeftRight(2, 2);
                //-----------------
            } else if (number == 6) {
                edgesTopBottom(0, 0);
            } else if (number == 7) {
                edgesTopBottom(0, 1);
            } else if (number == 8) {
                edgesTopBottom(0, 2);
            } else if (number == 9) {
                edgesTopBottom(2, 0);
            } else if (number == 10) {
                edgesTopBottom(2, 1);
            } else if (number == 11) {
                edgesTopBottom(2, 2);
                //----------
            } else if (number == 12) {
                rotation(0);
            } else if (number == 13) {
                rotation(1);
            }
        }
    }

}
