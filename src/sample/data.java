package sample;
import javax.sound.sampled.Port;
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
    */
    public void saveAccount(User newAccount){

        //check wthether the account has been created or not
        if (!isUserExist(newAccount)) {
            //get the list of User account from the text file
            userList = this.listOfUser();
            userList.add(newAccount);
            //open the text file and save the new account
            this.updateAccountList(userList);
            //create new PortfolioAccount
            this.savePortfolioAccount(newAccount.username());
        } else {
            System.out.println("account exists");

        }
    }

    /*
  * pre-condition: the user account is created before the fortfolio account is created
  * Post-condition: save the Portfolio account into the array in the text file
  * */
    public void savePortfolioAccount(String username){
        Portfolio newPortfolio = new Portfolio(username,new ArrayList<Equity>(),new ArrayList<CashAccount>());
        List<Portfolio> listOfPortfolio = this.listOfPortfolio();
        listOfPortfolio.add(newPortfolio);
        this.updatePortfolioList(listOfPortfolio);
    }

    /*
    * delete the account and portfolio with the known username
    * */
    public void deleteUserAccount(String username){
        List<User> listOfAccount = this.listOfUser();
        List<Portfolio> listOfPortfolio = this.listOfPortfolio();
        for (User user: listOfAccount){
            if (user.username().equals(username)){
                listOfAccount.remove(user);
                break;
            }
        }

        for (Portfolio p : listOfPortfolio){
            if(p.getUserID().equals(username)){
                listOfPortfolio.remove(p);
                break;
            }
        }
        this.updateAccountList(listOfAccount);

    }

    /*
    * update the portfolio list in the text file
    * */
    public void updatePortfolioList(List<Portfolio> listOfPortfolio){
        try {
            FileOutputStream fileOut = new FileOutputStream("portfolio.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfPortfolio);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * update the account list in the text file
     *
     */
    public void updateAccountList(List<User> listOfAccount){
        try {
            FileOutputStream fileOut = new FileOutputStream("employee.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfAccount);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
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
        Object temporary = this.listOfObject("employee.txt");
        if (temporary != null){
            listOfAccount = (ArrayList<User>) temporary;
        }

        return listOfAccount;
    }

    public List<Portfolio> listOfPortfolio (){
        List<Portfolio> listOfPortfolio = new ArrayList<Portfolio>();
        //access the text file portfolio.text
        Object temporary = this.listOfObject("portfolio.txt");
        if (temporary != null){
            listOfPortfolio = (ArrayList<Portfolio>) temporary;
        }

        return listOfPortfolio;
    }

    public Object listOfObject(String text){
        Object list= null;
        try {
            FileInputStream fileOut = new FileInputStream(text);
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                list = is.readObject();
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

        return list;
    }



}
