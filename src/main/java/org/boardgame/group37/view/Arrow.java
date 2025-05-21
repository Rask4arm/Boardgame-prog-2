package org.boardgame.group37.view;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

import javafx.scene.shape.Line;

/**
 * Arrow class
 * This class is responsible for creating an arrow shape for snake & ladders.
 */
public class Arrow extends Group {

    private final Line line;

    public Arrow() {
        this(new Line(), new Line(), new Line());
    }

    private static final double arrowLength = 20;
    private static final double arrowWidth = 7;

    private Arrow(Line line, Line arrow1, Line arrow2) {
        super(line, arrow1, arrow2);
        this.line = line;
        InvalidationListener updater = o -> {
            double ex = getEndX();
            double ey = getEndY();
            double sx = getStartX();
            double sy = getStartY();

            arrow1.setEndX(ex);
            arrow1.setEndY(ey);
            arrow2.setEndX(ex);
            arrow2.setEndY(ey);

            if (ex == sx && ey == sy) {
                // arrow parts of length 0
                arrow1.setStartX(ex);
                arrow1.setStartY(ey);
                arrow2.setStartX(ex);
                arrow2.setStartY(ey);
            } else {
                double factor = arrowLength / Math.hypot(sx-ex, sy-ey);
                double factorO = arrowWidth / Math.hypot(sx-ex, sy-ey);

                // part in direction of main line
                double dx = (sx - ex) * factor;
                double dy = (sy - ey) * factor;

                // part ortogonal to main line
                double ox = (sx - ex) * factorO;
                double oy = (sy - ey) * factorO;

                arrow1.setStartX(ex + dx - oy);
                arrow1.setStartY(ey + dy + ox);
                arrow2.setStartX(ex + dx + oy);
                arrow2.setStartY(ey + dy - ox);
            }
        };

        // add updater to properties
        startXProperty().addListener(updater);
        startYProperty().addListener(updater);
        endXProperty().addListener(updater);
        endYProperty().addListener(updater);
        updater.invalidated(null);
    }

    // start/end properties

    /**
     * Set the start x coordinate of the arrow.
     * @param value the x coordinate of the start point
     */
    public final void setStartX(double value) {
        line.setStartX(value);
    }

    /**
     * Get the start x coordinate of the arrow.
     * @return the x coordinate of the start point
     */
    public final double getStartX() {
        return line.getStartX();
    }

    /**
     * Get the start x property of the arrow.
     * @return the x property of the start point
     */
    public final DoubleProperty startXProperty() {
        return line.startXProperty();
    }

    /**
     * Set the start y coordinate of the arrow.
     * @param value the y coordinate of the start point
     */
    public final void setStartY(double value) {
        line.setStartY(value);
    }

    /**
     * Get the start y coordinate of the arrow.
     * @return the y coordinate of the start point
     */
    public final double getStartY() {
        return line.getStartY();
    }

    /**
     * Get the start y property of the arrow.
     * @return the y property of the start point
     */
    public final DoubleProperty startYProperty() {
        return line.startYProperty();
    }

    // end properties

    /**
     * Set the end x coordinate of the arrow.
     * @param value the x coordinate of the end point
     */
    public final void setEndX(double value) {
        line.setEndX(value);
    }

    /**
     * Get the end x coordinate of the arrow.
     * @return the x coordinate of the end point
     */
    public final double getEndX() {
        return line.getEndX();
    }

    /**
     * Get the end x property of the arrow.
     * @return the x property of the end point
     */
    public final DoubleProperty endXProperty() {
        return line.endXProperty();
    }

    /**
     * Set the end y coordinate of the arrow.
     * @param value the y coordinate of the end point
     */
    public final void setEndY(double value) {
        line.setEndY(value);
    }

    /**
     * Get the end y coordinate of the arrow.
     * @return the y coordinate of the end point
     */
    public final double getEndY() {
        return line.getEndY();
    }

    /**
     * Get the end y property of the arrow.
     * @return the y property of the end point
     */
    public final DoubleProperty endYProperty() {
        return line.endYProperty();
    }

}
