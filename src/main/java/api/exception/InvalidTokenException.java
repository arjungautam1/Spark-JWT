/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 14:17
 */
package api.exception;

@SuppressWarnings("serial")
public class InvalidTokenException extends Exception {
    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message) {
        super(message);
    }

}
