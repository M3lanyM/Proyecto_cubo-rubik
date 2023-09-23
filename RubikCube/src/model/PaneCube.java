/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Ashley Mendoza V
 */
public class PaneCube extends GridPane {
    public final PositionCube positions;
    private final Face[] faces;
    private Color color0, color1, color2, color3;
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
    
    private void inicializar(){
        for(int i = 0; i < 6; i++){
            getChildren().add(faces[i]);
        }
        
        //Llamar el metodo de la cara que vamos a usar de principal
        
    }
    
    public void whiteFace(){
        positions.setFront(faces[0]);
        positions.setBack(faces[1]);
        positions.setRight(faces[2]);
        positions.setLeft(faces[3]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
        
    }
    
    public void redFace(){
        positions.setFront(faces[5]);
        positions.setBack(faces[4]);
        positions.setRight(faces[2]);
        positions.setLeft(faces[3]);
        positions.setTop(faces[0]);
        positions.setBottom(faces[1]);
    }
    
    public void yellowFace(){
        positions.setFront(faces[1]);
        positions.setBack(faces[0]);
        positions.setRight(faces[3]);
        positions.setLeft(faces[2]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
    }
    
    public void blueFace(){
        positions.setFront(faces[2]);
        positions.setBack(faces[3]);
        positions.setRight(faces[1]);
        positions.setLeft(faces[0]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
    }
    
    public void greenFace(){
        positions.setFront(faces[3]);
        positions.setBack(faces[2]);
        positions.setRight(faces[0]);
        positions.setLeft(faces[1]);
        positions.setTop(faces[4]);
        positions.setBottom(faces[5]);
    }
    
    public void orangeFace(){
        positions.setFront(faces[4]);
        positions.setBack(faces[5]);
        positions.setRight(faces[2]);
        positions.setLeft(faces[3]);
        positions.setTop(faces[1]);
        positions.setBottom(faces[0]);
    }
    
    public void order(){
        
    }
    
    public void mixCube(){
        
    }
    
    
}
