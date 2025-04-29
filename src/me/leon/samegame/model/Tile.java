package me.leon.samegame.model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * The {@code Tile} class represents a tile in the SameGame game.
 * It extends {@link JComponent}.
 * Each tile has a color, and can be marked as removed or visited.
 * The tile is drawn as a colored rectangle, with a border when not
 * removed.
 *
 * @see javax.swing.JComponent
 */
public class Tile extends JComponent {
    private Color color;
    private boolean removed;
    private boolean visited;

    /**
     * Constructs a new {@code Tile} with the specified color.
     *
     * @param color the color of the tile
     */
    public Tile(Color color) {
        this.color = color;
        this.removed = false;
        setBorder(new LineBorder(Color.BLACK));
    }

    /**
     * Constructs a new {@code Tile} with the specified color and removed state.
     * This constructor is typically used for tiles that are initially removed in
     * the process of shifting the grid left.
     *
     * @param color   the color of the tile
     * @param removed the removed state of the tile
     */
    public Tile(Color color, boolean removed) {
        this.color = color;
        this.removed = removed;
    }

    /**
     * Paints the tile. The tile is drawn as a filled rectangle with the tile's color.
     *
     * @param g the {@code Graphics} object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw tile as filled rectangle using Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    /**
     * Returns the color of the tile.
     *
     * @return the color of the tile
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Marks the tile as removed. The tile's color is set to white and its border is removed.
     */
    public void remove() {
        this.removed = true;
        this.color = Color.white;
        setBorder(null);
    }

    /**
     * Checks if the tile is removed.
     *
     * @return {@code true} if the tile is removed, {@code false} otherwise
     */
    public boolean isRemoved() {
        return this.removed;
    }

    /**
     * Sets the visited state of the tile.
     *
     * @param visited {@code true} to mark the tile as visited, {@code false} otherwise
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Checks if the tile is visited.
     *
     * @return {@code true} if the tile is visited, {@code false} otherwise
     */
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * Returns a string representation of the tile. The string includes the color,
     * removed state, and visited state of the tile.
     *
     * @return a string representation of the tile
     */
    @Override
    public String toString() {
        String c = color.toString();
        if (c.equalsIgnoreCase("java.awt.Color[r=255,g=0,b=0]")) c = "RED  ";
        if (c.equalsIgnoreCase("java.awt.Color[r=255,g=255,b=0]")) c = "YELLO";
        if (c.equalsIgnoreCase("java.awt.Color[r=0,g=0,b=255]")) c = "BLUE ";
        if (c.equalsIgnoreCase("java.awt.Color[r=0,g=255,b=0]")) c = "GREEN";
        if (c.equalsIgnoreCase("java.awt.Color[r=255,g=200,b=0]")) c = "ORANG";
        if (c.equalsIgnoreCase("java.awt.Color[r=255,g=255,b=255]")) c = "WHITE";
        return "[" + c + " rem=" + (removed ? "t" : "f") + " vis=" + (visited ? "t" : "f") + "]";
    }
}
