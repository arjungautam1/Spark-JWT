/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 14:17
 */
package api.exception;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super();
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
