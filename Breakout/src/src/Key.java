package src;

import java.awt.event.KeyEvent;

public enum Key {
	Up(KeyEvent.VK_UP),
	Down(KeyEvent.VK_DOWN),
	Left(KeyEvent.VK_LEFT),
	Right(KeyEvent.VK_RIGHT),
	Escape(KeyEvent.VK_ESCAPE),
	Enter(KeyEvent.VK_ENTER),
	Space(KeyEvent.VK_SPACE);

	private final int keyCode;

	Key(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}
}
