package sample;
import java.util.Observer;
/**
 * Created by minhduong on 3/13/16.
 */
/*
* subject interface will be responsible for create and delete account
* */

public interface subject {
    public void saveAccount(User newAccount);
    public void deleteUserAccount(User account);
}
