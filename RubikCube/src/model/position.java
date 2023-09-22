/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Ashley Mendoza V
 */
public class Position {
    private Face front;
    private Face back;
    private Face right;
    private Face left;
    private Face top;
    private Face bottom;

    public Face getFront() {
        return front;
    }

    public Face getBack() {
        return back;
    }

    public Face getRight() {
        return right;
    }

    public Face getLeft() {
        return left;
    }

    public Face getTop() {
        return top;
    }

    public Face getBottom() {
        return bottom;
    }

    public void setFront(Face face) {
        GridPane.setConstraints(face, 1, 1); 
        front = face;
    }

    public void setBack(Face face) {
        GridPane.setConstraints(face, 3, 1); 
        back = face;
    }

    public void setRight(Face face) {
        GridPane.setConstraints(face, 2, 1); 
        right = face;
    }

    public void setLeft(Face face) {
        GridPane.setConstraints(face, 0, 1); 
        left = face;
    }

    public void setTop(Face face) {
        GridPane.setConstraints(face, 1, 0); 
        top = face;
    }

    public void setBottom(Face face) {
        GridPane.setConstraints(face, 1, 2); 
        bottom = face;
    }
}
