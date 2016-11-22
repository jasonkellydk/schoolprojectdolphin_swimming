package Enligthen;

import Model.User;
import org.javalite.activejdbc.Model;
import org.mindrot.jbcrypt.BCrypt;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by jasonkelly on 15/11/2016.
 */
public class Auth {


    private static Model name;

    public boolean Login(String email, String password){

      /*  String hashed = BCrypt.hashpw("test", BCrypt.gensalt());
        User p = new User();
        p.set("name", "John Hansen");
        p.set("email", "john@example.com");
        p.set("password", hashed);
        p.set("role", "1");
        p.saveIt();
*/
        /*
         * Checks if the email field is filled.
         */
        if(email == null){
            return false;
        }
         /*
         * Checks if the password field is filled.
         */
        if(password == null){
            return false;
        }

            User authUser = User.findFirst("email = ?",email);
            if(authUser != null){
                String hashedPassword = (String) authUser.get("password");

                if(BCrypt.checkpw(password, hashedPassword)){
                     name = authUser;

                    return true;
                }

        }
        return false;
    }

    public boolean Logout(){
         name = null;
        return true;
    }

    public static Model user() {
        return name;
    }
}
