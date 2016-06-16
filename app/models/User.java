package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by yuhuanxi on 16/6/16.
 */
@Entity
public class User extends Model {

    @Id
    @Constraints.Min(1)
    @GeneratedValue
    public Long id;

    @Constraints.Required
    public String nick;

    @Constraints.Min(0)
    @Constraints.Max(150)
    public int age;

    @Constraints.Required
    public String sex;

    @Formats.DateTime(pattern = "yyyy/MM/dd")
    public Date birth;

    public static Finder<Long, User> finder = new Finder<Long, User>(User.class);
}
