package Enligthen;

/*
 * This is the enlighten Auth class. It may be used to authenticate a user.
 * Made with <3 by Jason kelly.
 */

import Model.User;
import org.javalite.activejdbc.Model;
import org.mindrot.jbcrypt.BCrypt;

public class Auth {


    private static Model user;

    /**
     *
     * @param email
     * @param password
     * @return Boolean
     */
    public boolean Login(String email, String password){


        /*
         * This is the enlighten email validator class.
         * It accepts different email validation parameters.
         */
        Validation val = new Validation();
        if(!val.vEmail(email)){return false;}
        if(!val.vMinLength(password,4)){return false;}


            User authUser = User.findFirst("email = ?",email);
            if(authUser != null){
                String hashedPassword = (String) authUser.get("password");
                System.out.println(authUser.getString("name"));
                if(BCrypt.checkpw(password, hashedPassword)){
                     user = authUser;

                    return true;
                }

        }

        return false;
    }

    public boolean Logout(){
        user = null;
        return true;
    }

    public static Model user() {
        return user;
    }
}
