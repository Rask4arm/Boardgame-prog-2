package org.boardgame.group37.view;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * PlayerToken class
 * This class is responsible for creating a player token.
 */
public class PlayerToken extends Circle {
    public PlayerToken(Color color) {
        super(10,color);
    }
}
