package auth;

import models.User;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import play.twirl.api.Content;

/**
 * Created by yuhuanxi on 16/6/18.
 */
public class AdminAuthenticator extends Security.Authenticator {

    public String getUsername(Http.Context ctx) {

        String uid = ctx.request().getQueryString("uid");
        if (uid != null) {
            User user = User.finder.byId(Long.parseLong(uid));
            if (user != null) {
                if ("abc".equals(user.nick)) {
                    return user.nick;
                }
            }
        }
        return null;

        // 自定义验证逻辑,这里是看看session中的username是否为yhx,如果是,则通过验证
//        String username = ctx.session().get("username");
//        if (username != null && username.equals("yhx")) {
//            return ctx.session().get("username");
//        }
//        return null;
    }

    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized("未授权");
    }
}
