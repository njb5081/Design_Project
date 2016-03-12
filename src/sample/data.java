package sample;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by minhduong on 3/4/16.
 */
public class data  {

    List<User> userList;

    /*
    * save a new account to data
    */

    public void saveAccount(User newAccount){
        //check wthether the account has been created or not
        if (!isUserExist(newAccount)) {
            //get the list of User account from the text file
            userList = this.listOfUser();
            //open the text file and save the new account
            try {
                FileOutputStream fileOut = new FileOutputStream("employee.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                //userList.add(newAccount);h
                int userID = userList.size();
                newAccount.setUserID(userID);
                userList.add(newAccount);
                out.writeObject(userList);
                out.close();
                fileOut.close();
            } catch (FileNotFoundException i) {
                i.printStackTrace();
            } catch (IOException i) {
                i.printStackTrace();
            }
            //System.out.println("Done");
        } else {
            System.out.println("account exists");

        }
    }

    /*
    * pre-condition: the user account is created before the fortfolio account is created
    * Post-condition: save the Portfolio account into the array in the text file
    * */
    public void savePortfolioAccount(Portfolio p){

    }

    /*
    * check if the user has made an account before
    * */
    public boolean isUserExist(User checkUser){

        return this.listOfUser().contains(checkUser);

    }

    /*c
    * return list of accounts that have been made from the text file
    * */
    public List<User> listOfUser (){
        List<User> listOfAccount = new ArrayList<User>();
        //access the text file employee.text
        try {
            FileInputStream fileOut = new FileInputStream("employee.txt");
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                listOfAccount = (ArrayList<User>) is.readObject();
                is.close();
            }
        } catch (FileNotFoundException i){
            i.printStackTrace();
        }catch(IOException i)
        {
            i.printStackTrace();
        } catch (ClassNotFoundException i){
            i.printStackTrace();
        }

        return listOfAccount;
    }



}
