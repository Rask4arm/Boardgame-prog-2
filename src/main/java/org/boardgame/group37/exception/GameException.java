package org.boardgame.group37.exception;

/**
 * GameException class
 * This class is responsible for handling exceptions in the game.
 */
public class GameException extends Exception {
    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameException(Throwable cause) {
        super(cause);
    }
}
