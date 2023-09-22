/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Ashley Mendoza V
 */
public class Face extends GridPane{
    private final Color color;
    public final Rectangle[][] rectangles;

    public Face(Color color) {
        this.color = color;
        this.rectangles = new Rectangle[3][3];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rectangle r = new Rectangle(30, 30, color);
                rectangles[i][j] = r;

                r.setStroke(Color.BLACK);
                getChildren().add(r);
                GridPane.setConstraints(r, i, j);

            }
        }
    }

    public Color getColor() {
        return color;
    }

    public Rectangle[][] getMatrix() {
        return rectangles;
    }

    
    public void setColor(Color color, int i, int j) {
        if ((i < 0 || i >= 3) || (j < 0 || j >= 3)) {
            throw new IllegalArgumentException("Posición no existente en la matriz");
        }
        rectangles[i][j].setFill(color);
    }
}
