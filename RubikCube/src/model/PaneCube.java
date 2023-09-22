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
    public final Position positions;
    private final Face[] faces;
    private Color color0, color1, color2, color3;
    private Face faceTemp;// para realizar la evaluacion de las caras sin afectar las originales
    public Face pane;

    public PaneCube() {
        this.positions = new Position();
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
        
    }
    
    public void redFace(){
        
    }
    
    public void yellowFace(){
        
    }
    
    public void blueFace(){
        
    }
    
    public void greenFace(){
        
    }
    
    public void orangeFace(){
        
    }
    
    public void order(){
        
    }
    
    public void mixCube(){
        
    }
    
    
}
