package controllers;

import com.avaje.ebean.ExpressionList;
import models.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
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

    public Result addUser(String nick, String sex, Integer age) {
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
        return ok("<h1><font color = 'red'>Hello World</font></h1>").as("text/html");
    }
}
