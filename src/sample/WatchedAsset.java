package sample;

/**
 * Created by Nick on 4/3/2016.
 */
public class WatchedAsset {
    private String name;

    private double lowTrigger;

    private double highTrigger;

    public WatchedAsset(String name, double low, double high){
        //if there is no low trigger or no high trigger, the parameter should be -1
        this.name = name;
        this.lowTrigger = low;
        this.highTrigger = high;
    }

    public boolean hasLowTrigger() {
        return (this.lowTrigger != -1);
    }
    public boolean hasHighTrigger() {
        return (this.highTrigger != -1);
    }

    public void editTriggers(double low, double high) {
        this.lowTrigger = low;
        this.highTrigger = high;
    }

    public String getName(){
        return this.name;
    }

    public double getLowTrigger() {
        return this.lowTrigger;
    }

    public double getHighTrigger() {
        return this.highTrigger;
    }

    public String toString() {
        return this.name;
    }
}
