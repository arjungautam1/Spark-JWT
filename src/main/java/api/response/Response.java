/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 14:41
 */
package api.response;

import com.google.gson.annotations.Expose;

public class Response extends BaseResponse{
    @Expose
    private Object result;

    public Response() {
    }

    public Response(Object result) {
        this.result = result;
    }

    public Response(int code, String message) {
        super(code, message);
    }

    public Response(int code, String message, Object result) {
        super(code, message);
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
