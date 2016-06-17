package controllers;

import play.mvc.Http;
import play.mvc.Security;

/**
 * Created by yuhuanxi on 16/6/17.
 */
public class TestAuthenticator extends Security.Authenticator {

    public String getUsername(Http.Context ctx) {

        String username = ctx.session().get("username");
        if (username != null && username.equals("yhx")) {
            return ctx.session().get("username");
        }
        return null;
    }
}
