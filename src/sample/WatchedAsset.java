package sample;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Nick on 4/3/2016.
 */
public interface WatchedAsset {
    //String name;
    //if either trigger value is -1, then there was no trigger specified
    //double lowTrigger;

    //double highTrigger;

    boolean lowIsTripped();

    boolean highIsTripped();

    boolean hasLowTrigger();

    boolean hasHighTrigger();

    void editTriggers(double low, double high);

    String getName();

    double getLowTrigger();

    double getHighTrigger();

    HashMap<Date, Double> getTriggers();

    void clearTriggers();

    String toString();

    void accept(AssetVisitor v);
}
