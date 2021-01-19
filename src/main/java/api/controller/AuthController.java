/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 15:05
 */
package api.controller;


import api.response.Response;
import api.util.AuthServiceUtil;
import api.util.CommonUtil;
import api.util.Constants;
import com.google.gson.Gson;
import model.UserLogin;

import static spark.Spark.get;
import static spark.Spark.post;

public class AuthController extends BaseController {
    public AuthController(final Gson jsonConverter) {
        super(jsonConverter);
        initializeController(jsonConverter);
    }

    public void initializeController(Gson jsonConverter) {

        // -- login
        post("/login", (req, res) -> {
            validateContentType(req);

            String payload = req.body();
            UserLogin userlogin = jsonConverter.fromJson(payload, UserLogin.class);
            return new Response(AuthServiceUtil.login(userlogin.getUserName(), userlogin.getPassword()));

        } , CommonUtil.getJsonTransformer());

        // -- extend
        get("/extend", (req, res) -> {
            return new Response(AuthServiceUtil.extendToken(req.queryParams(Constants.URL_PARAM_TOKEN)));
        } , CommonUtil.getJsonTransformer());
    }

}
