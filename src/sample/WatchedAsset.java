package sample;

/**
 * Created by Nick on 4/3/2016.
 */
public class WatchedAsset {
    private String name;

    private double lowTrigger;

    private double highTrigger;

    public WatchedAsset(String name, double low, double high){
        this.name = name;
        this.lowTrigger = low;
        this.highTrigger = high;
    };

    public void changeLow(double newLow) {
        this.lowTrigger = newLow;
    }

    public void changeHigh(double newHigh) {
        this.highTrigger = newHigh;
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
