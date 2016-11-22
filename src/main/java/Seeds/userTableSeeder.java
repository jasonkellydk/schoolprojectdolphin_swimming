package Seeds;
import Model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by jasonkelly on 22/11/2016.
 */
public class userTableSeeder {

    public void Seed(){
        String hashed = BCrypt.hashpw("test", BCrypt.gensalt());
        User p = new User();
        p.set("name", "Asger Hansen");
        p.set("email", "asger@example.com");
        p.set("password", hashed);
        p.set("role_id", "1");
        p.saveIt();
    }

}
