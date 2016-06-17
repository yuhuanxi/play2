package controllers;

import play.mvc.Http;
import play.mvc.Security;

/**
 * Created by yuhuanxi on 16/6/17.
 */
public class TestAuthenticator extends Security.Authenticator {

    public String getUsername(Http.Context ctx) {

        // 自定义验证逻辑,这里是看看session中的username是否为yhx,如果是,则通过验证
        String username = ctx.session().get("username");
        if (username != null && username.equals("yhx")) {
            return ctx.session().get("username");
        }
        return null;
    }
}
