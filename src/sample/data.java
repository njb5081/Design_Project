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
public class  data  {

    List<User> userList;

    /*
    * save a new account to data
    * */

    public void saveAccount(User newAccount){
        if (!isUserExist(newAccount)) {
            userList = this.listOfUser();
            //userList = new ArrayList<User>();
            try {
                FileOutputStream fileOut = new FileOutputStream("employee.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                //userList.add(newAccount);
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
    * check if the user has made an account before
    * */
    public boolean isUserExist(User checkUser){

        return this.listOfUser().contains(checkUser);

    }

    /*c
    * return list of accounts that have been made
    * */
    public List<User> listOfUser (){
        List<User> listOfAccount = new ArrayList<User>();
        try {
            FileInputStream fileOut = new FileInputStream("employee.txt");
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                listOfAccount = (ArrayList<User>) is.readObject();
//                for (User b: listOfAccount) {
//                    System.out.println("check username: "+b.username()+". PW: "+b.password());
//                }
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
//    public static void main(String [] args)
//    {
//        User a = new User("mcd48776","1234");
//        //User b = new User("mk123","567");
//        data s = new data();
//        s.saveAccount(a);
//        //s.saveAccount(b);
//        System.out.println("finish");
//
//
//
//    }

}
