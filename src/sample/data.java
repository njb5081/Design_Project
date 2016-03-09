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

    public void saveAccount(User newAccount){
        if (!isUserExist(newAccount)) {

            try {
                FileOutputStream fileOut = new FileOutputStream("employee.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                out.writeObject(newAccount);
                out.close();
                fileOut.close();
            } catch (FileNotFoundException i) {
                i.printStackTrace();
            } catch (IOException i) {
                i.printStackTrace();
            }

            System.out.println("Done");
        } else {
            System.out.println("account exists");
        }
    }

    /*
    * check if the user has made an account before
    * */
    public boolean isUserExist(User checkUser){
        userList = this.listOfUser();
        return userList.contains(checkUser);

    }

    /*
    * return list of accounts that have been made
    * */
    public List<User> listOfUser (){
        List<User> listOfAccount = null;
        try {
            FileInputStream fileOut = new FileInputStream("employee.txt");
            ObjectInputStream is = new ObjectInputStream(fileOut);
            //System.out.println(is);
            if (is.readObject() != null){
                listOfAccount = (List<User>) is.readObject();
                System.out.println("sucess get list of account");
            }
            is.close();

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
    public static void main(String [] args)
    {
        User a = new User("mcd4874","1234");
        data s = new data();
        s.saveAccount(a);
        System.out.println("finish");



    }

}
