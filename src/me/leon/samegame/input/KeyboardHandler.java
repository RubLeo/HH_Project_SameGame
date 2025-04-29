package me.leon.samegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The {@code KeyboardHandler} class handles keyboard events and notifies an {@link InputObserver}.
 * This class implements the {@link KeyListener} interface to listen for key events and delegate the handling to the observer.
 */
public class KeyboardHandler implements KeyListener {
    private InputObserver observer;

    /**
     * Constructs a new {@code KeyboardHandler} with the specified {@link InputObserver}.
     *
     * @param observer observer that will handle the key events
     */
    public KeyboardHandler(InputObserver observer) {
        this.observer = observer;
    }

    /**
     * Invoked when a key has been pressed.
     * Delegates the event handling to the {@link InputObserver#onKeyPressed(KeyEvent)} method.
     *
     * @param e event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        observer.onKeyPressed(e);
    }

    /**
     * Not used.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Not used.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
