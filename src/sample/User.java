package sample;

import java.io.Serializable;

/**
 * Created by minhduong on 3/3/16.
 */
public class User implements Serializable {

    /*
    * Private variable to support the User object
    * */
    private String password;
    private String username;
    private String encryption = "1234";
    private int portfolioID;
    //private int id;

    /*
    * Initial the object with information
    * */
    public User(String username, String password) {
        this.username = username;
        // encrypte the password
        this.password = password+encryption;
    }

    /*
    * connect with the associate portfolio id
    * */
    public void setPortfolioID(int ID){
        this.portfolioID = ID;
    }

    /*
    * return the associate portforlio id
    * */
    public int getPortfolioID(){
        return this.portfolioID;
    }

//    /*
//    * set a new ID for user
//    * */
//    public void setUserID(int ID){
//        this.id = ID;
//    }
//
//    /*
//    * return userID
//    * */
//    public int getUserID(int ID){
//        return this.id;
//    }


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

    public void changePassword(String newPassword){

    }
    /*
    * compare two user whether both are equal or not
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
