package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */
public interface MarketSimulation {


    //runs the selected market simulation
    public float runSimulation(float percentage, ArrayList<equity> EQ, boolean continuous,int stepNum, String timeInterval );

    //resets the equity prices to what they were before the simulation
    public ArrayList<equity> reset(ArrayList<equity> EQ);

}
