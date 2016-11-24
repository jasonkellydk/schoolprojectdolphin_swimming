package Enligthen;

/*
 * This is the enlighten Validation class. It may be used to validate user input.
 * Made with <3 by Jason kelly.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    /**
     * @param email
     * @return Boolean
     */
    public Boolean vEmail(String email){
        String EMAIL_REGIX = "^.+@.+\\..+$";
        Pattern pattern = Pattern.compile(EMAIL_REGIX);
        Matcher matcher = pattern.matcher(email);

        return ((!email.isEmpty()) && (email!=null) && (matcher.matches()));
    }

    /**
     * @param text
     * @param length
     * @return Boolean
     */
    public Boolean vMinLength(String text, int length){
        if(!(text.length() >= length)){
            return false;
        }

        return true;
    }

}
