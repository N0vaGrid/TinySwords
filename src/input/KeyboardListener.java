package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        InputManager.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        InputManager.keyReleased(e.getKeyCode());
    }
}