package sample;

import java.io.Serializable;

/**
 * Created by minhduong on 3/3/16.
 */
public class User implements Serializable {

    private String password;
    private String username;

    public User(String username, String password) {
        this.username = username;
        this.password = password+"1234";
    }

    public String User123(User a){
        return a.username;
    }


    public void check (){

    }

    public String username(){
        return this.username;
    }

    public String password(){
        return this.password;
    }

    public void changePassword(String newPassword){

    }
    /*
    * compare two user
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


}
