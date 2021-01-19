import api.controller.AuthController;
import api.controller.CustomerController;
import api.service.CustomerService;
import api.util.CommonUtil;

/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 15:07
 */

public class Main {
    public static void main(String[] args) {
        new CustomerController(new CustomerService(),CommonUtil.getJsonConvertor());
        new AuthController(CommonUtil.getJsonConvertor());
    }
}
