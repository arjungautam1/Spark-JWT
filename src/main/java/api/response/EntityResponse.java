/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 14:41
 */
package api.response;

import com.google.gson.annotations.Expose;

public class EntityResponse<T> extends BaseResponse {
    @Expose
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
