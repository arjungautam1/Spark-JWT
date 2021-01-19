/**
 * @author :arjun
 * Project :Spark-JWT
 * Date : 2021-01-10
 * Time : 15:05
 */
package api.controller;

import api.exception.InvalidCredentialsException;
import api.exception.InvalidPayloadException;
import api.exception.NotFoundException;
import api.response.Response;
import api.util.AuthServiceUtil;
import api.util.Constants;
import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.lang.JoseException;
import spark.Request;

import static spark.Spark.*;


public class BaseController {
    public BaseController(final Gson jsonConverter) {

        // -- Check the authentication
        before((req, res) -> {
            validateLogin(jsonConverter, req);
        });

        // -- Set proper content-type to all responses
        after((req, res) -> {
            res.type(Constants.STANDARD_RESPONSE_CONTENTTYPE);
        });

        // -- Handle the exceptions
        handleExceptions(jsonConverter);
    }

    private void validateLogin(final Gson jsonConverter, Request req) throws JoseException, MalformedClaimException {

        if (req.pathInfo().equalsIgnoreCase("/login")) {
            return;
        }

        // -- Get token
        String token = req.queryParams(Constants.URL_PARAM_TOKEN);
        String username = AuthServiceUtil.getUsername(token);

        if (username == null) {
            halt(HttpStatus.FORBIDDEN_403,
                    jsonConverter.toJson(new Response(Constants.RESPONSE_ERROR, "Invalid/expired authentication")));
        }

        // -- Add the username as attribute so that subsequent requests can know who is currently logged in
        req.attribute("principal", username);
    }

    protected void validateContentType(Request req) throws InvalidPayloadException {
        if (req.contentType() == null || !req.contentType().toLowerCase().contains("application/json")) {
            throw new InvalidPayloadException("Invalid content type: " + req.contentType());
        }
    }

    protected void handleExceptions(final Gson jsonConverter) {
        exception(InvalidCredentialsException.class, (ex, req, res) -> {
            res.status(HttpStatus.FORBIDDEN_403);
            res.body(jsonConverter.toJson(new Response(Constants.RESPONSE_ERROR, ex.getMessage())));
        });
        exception(InvalidPayloadException.class, (ex, req, res) -> {
            res.status(HttpStatus.BAD_REQUEST_400);
            res.body(jsonConverter.toJson(new Response(Constants.RESPONSE_ERROR, ex.getMessage())));
        });
        exception(NotFoundException.class, (ex, req, res) -> {
            res.status(HttpStatus.NOT_FOUND_404);
            res.body(jsonConverter.toJson(new Response(Constants.RESPONSE_ERROR, ex.getMessage())));
        });
        exception(Exception.class, (ex, req, res) -> {
            res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            res.body(jsonConverter.toJson(new Response(Constants.RESPONSE_ERROR, ex.getMessage())));
        });
    }
}