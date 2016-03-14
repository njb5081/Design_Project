package sample;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by minhduong on 3/3/16.
 * This class will create the user Object to represent the User account
 */
public class User implements Serializable,observer  {

    /*
    * Private variable to support the User object
    * */
    private String password;
    private String username;
    /*
    * encryption the password
    * */
    private String encryption = "1234";


    /*
    * Initial the object with information
    * */
    public User(String username, String password) {
        this.username = username;
        // encrypte the password
        this.password = password+encryption;
    }

    /*
    * return username
    * */
    public String username(){
        return this.username;
    }

    /*
    * return a password after decrypte
    * */
    public String password(){
        int finalIndex = this.password.length()-this.encryption.length()+1;
        String temporalPW = this.password.substring(0,finalIndex);
        return temporalPW;
    }

    /*
    * compare two user whether both are equal or not
    * Both account need to have the same username and password
    * */
    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof User)
        {
            if(this.username().equals(((User) object).username()) && this.password().equals(((User) object).password())){
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public void update(String message) {
        System.out.println( "the account has been "+message);
    }

}
