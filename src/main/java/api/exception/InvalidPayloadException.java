/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 14:16
 */
package api.exception;

public class InvalidPayloadException extends Exception {
    public InvalidPayloadException() {
        super();
    }

    public InvalidPayloadException(String message) {
        super(message);
    }
}
