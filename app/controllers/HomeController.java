package controllers;

import auth.AdminAuthenticator;
import com.avaje.ebean.ExpressionList;
import models.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.cache.Cached;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.login;
import views.html.loginAction;

import java.util.Date;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
//        return ok("Hello World");
        return ok(index.render("Your new application is ready."));
    }

    public Result login() {
        return ok(login.render("please input your username and password"));
    }

    public Result loginAction() {
        return ok(loginAction.render("login success!"));
    }

    @Security.Authenticated(AdminAuthenticator.class)
    public Result addUser(Long uid, String nick, String sex, Integer age) {
        User user = new User();
        user.nick = nick;
        user.sex = sex;
        user.age = age;
        user.birth = new Date();
        user.save();
        return ok("HelloWorld");
    }

    public Result findById(Long id) {
        User user = User.finder.byId(id);
        if (user != null) {
            return ok(Json.toJson(user));
        }
        return ok("Data not found!");
    }

    public Result findAll() {
        List<User> users = User.finder.all();
        return ok(Json.toJson(users));
    }

    public Result findByNick(String nick, Long id, Integer curPage, Integer pageSize) {
        ExpressionList<User> where = User.finder.where();
        if (StringUtils.isNotBlank(nick)) {
            where.ilike("nick", "%" + nick + "%");
        }
        List<User> users = where
                .orderBy("id desc")
                .findPagedList(curPage, pageSize)
                .getList();
        return ok(Json.toJson(users));
    }

    public Result toHtml() {

        // play2设置cookie的方法
        Http.Cookie cookie = Http.Cookie.builder("theme", "blue").build();
        Http.Cookie hello = Http.Cookie.builder("hello", "123").withMaxAge(10).build();
        response().setCookie(cookie);
        response().setCookie(hello);

        // 丢弃cookie
        response().discardCookie("theme");

        // 返回Content-type 为text/html
        return ok("<h1><font color = 'red'>Hello World</font></h1>").as("text/html");
    }

    public Result setSession() {
        session("user", "adobe1874@126.com");
        session("username", "yhx");
        // session().remove("user"); 移除 session
        return ok("Welcome!");
    }

    @Cached(key = "index.result")
    public Result getSession() {
        String user = session("user");
        return ok(Json.toJson(user));
    }

    /**
     * play2注解,如果为true,则会打印日志
     *
     * @param ctx
     * @return
     */
    @VerboseAnnotation(false)
    public Result verboseAnnotationIndex(String ctx) {
        return ok("It works!");
    }

    @Security.Authenticated(TestAuthenticator.class)
    public Result testAuthenticated() {
        return ok("It works!");
    }
}
