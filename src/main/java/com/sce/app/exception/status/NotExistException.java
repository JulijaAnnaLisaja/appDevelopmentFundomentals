package com.sce.app.exception.status;

/**
 * 'Not Exists' Exception.
 *
 * @author julija.anna.lisaja@accenture.com
 */
public class NotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotExistException(String message) {
        super(message);
    }

}
