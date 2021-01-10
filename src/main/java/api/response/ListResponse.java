/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 14:41
 */
package api.response;

import java.util.List;

public class ListResponse<T> extends BaseResponse {

    private List<T> result;

    public List<T> getResult() {
        return result;
    }
    public void setResult(List<T> result) {
        this.result = result;
    }
}
