package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Nick on 4/8/2016.
 */
public class WatchedMarketAverage implements WatchedAsset, Serializable {

    private String name;

    private double lowTrigger;

    private double highTrigger;

    private boolean lowCurrentlyTripped;

    private boolean highCurrentlyTripped;

    private HashMap<Date, Double> triggers;

    public WatchedMarketAverage(String name, double low, double high){
        //if either trigger is -1 no value was specified
        this.name = name;
        this.lowTrigger = low;
        this.highTrigger = high;
        this.triggers = new HashMap<Date, Double>();
        this.lowCurrentlyTripped = false;
        this.highCurrentlyTripped = false;
    }

    @Override
    public boolean lowIsTripped() {
        return this.lowCurrentlyTripped;
    }

    @Override
    public boolean highIsTripped() {
        return this.highCurrentlyTripped;
    }

    @Override
    public boolean hasLowTrigger() {
        return (this.lowTrigger != -1);
    }

    @Override
    public boolean hasHighTrigger() {
        return (this.highTrigger != -1);
    }

    @Override
    public void editTriggers(double low, double high) {
        this.lowTrigger = low;
        this.highTrigger = high;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getLowTrigger() {
        return this.lowTrigger;
    }

    @Override
    public double getHighTrigger() {
        return this.highTrigger;
    }

    public void checkMarketAverageTriggers(Double price) {
        if (price >= this.highTrigger) {
            this.highCurrentlyTripped = true;
            Date date = new Date();
            this.triggers.put(date, price);
        } else {
            this.highCurrentlyTripped = false;
        }
        if (price <= this.lowTrigger) {
            this.lowCurrentlyTripped = true;
            Date date = new Date();
            this.triggers.put(date, price);
        }
    }

    @Override
    public HashMap<Date, Double> getTriggers() {
        return this.triggers;
    }

    @Override
    public void clearTriggers() {
        this.triggers.clear();
    }

    @Override
    public void accept(AssetVisitor v) {
        v.visit(this);
    }
}
