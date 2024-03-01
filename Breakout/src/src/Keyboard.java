package src;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    HashMap<Key, Boolean> state;
    HashMap<Key, Boolean> previousState;
    boolean enterPressedLastFrame;
    private GameBoard gameBoard;

    public Keyboard(GameBoard gameBoard) {
        state = new HashMap<>();
        previousState = new HashMap<>();
        enterPressedLastFrame = false;
        this.gameBoard = gameBoard;

        for (Key key : Key.values()) {
            state.put(key, false);
            previousState.put(key, false);
        }
    }

    public boolean isKeyDown(Key key) {
        return state.containsKey(key) && state.get(key);
    }

    public boolean isKeyUp(Key key) {
        return !isKeyDown(key);
    }

    public boolean isKeyPressed(Key key) {
        return isKeyDown(key) && !previousState.get(key);
    }

    public void processKeyEvent(int keyCode, boolean pressed) {
        // Update the previousState map before updating the state map
        for (Map.Entry<Key, Boolean> entry : state.entrySet()) {
            previousState.put(entry.getKey(), entry.getValue());
        }
        
        // Update the state map with the new key event
        for (Key key : Key.values()) {
            if (key.getKeyCode() == keyCode) {
                state.put(key, pressed);
                break;
            }
        }
        
        if (keyCode == KeyEvent.VK_ENTER && pressed && !enterPressedLastFrame && !gameBoard.isDead()) {
            // Toggle pause state here
            if (gameBoard.isPaused()) {
            	gameBoard.hideMenu();
            } else {
                gameBoard.showMenu();
            }
            
            enterPressedLastFrame = true;
        } else if (keyCode == KeyEvent.VK_ENTER && !pressed) {
            enterPressedLastFrame = false;
        }
    }
}