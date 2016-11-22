package Seeds;

import Model.Role;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by jasonkelly on 22/11/2016.
 */
public class roleTableSeeder {
    public void Seed(){
        Role p = new Role();
        p.set("title", "Administrator");
        p.set("description", "Description here");
        p.saveIt();
    }
}
