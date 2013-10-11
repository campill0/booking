package dao.jpa;

/**
 *
 * @author Francisco Campillo Asensio
 */
public class UniqueConstraintViolationException extends Exception {

    /**
     * Creates a new instance of <code>UniqueConstraintViolationException</code> without detail message.
     */
    public UniqueConstraintViolationException() {
    }


    /**
     * Constructs an instance of <code>UniqueConstraintViolationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UniqueConstraintViolationException(String msg) {
        super(msg);
    }
}