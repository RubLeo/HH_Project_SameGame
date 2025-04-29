package me.leon.samegame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The {@code MouseHandler} class handles mouse events and notifies an {@link InputObserver}.
 * This class implements the {@link MouseListener} interface to listen for mouse events and delegate the handling to the observer.
 */
public class MouseHandler implements MouseListener {
    private InputObserver observer;

    /**
     * Constructs a new {@code MouseHandler} with the specified {@link InputObserver}.
     *
     * @param observer the observer that will handle the mouse events
     */
    public MouseHandler(InputObserver observer) {
        this.observer = observer;
    }

    /**
     * Invoked when the mouse has been clicked.
     * Delegates the event handling to the {@link InputObserver#onMouseClicked(MouseEvent)} method.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        observer.onMouseClicked(e);
    }

    /**
     * Not used.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Not used.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Not used.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Not used.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
