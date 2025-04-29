package me.leon.samegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The {@code InputObserver} interface defines the contract for objects that observe and respond to input events.
 * This interface is part of the <b>Observer</b> pattern and allows implementing classes to handle mouse and keyboard events.
 */
public interface InputObserver {

    /**
     * Called when a mouse click event is observed.
     *
     * @param e MouseEvent
     */
    void onMouseClicked(MouseEvent e);

    /**
     * Called when a key press event is observed.
     *
     * @param e KeyEvent
     */
    void onKeyPressed(KeyEvent e);
}
