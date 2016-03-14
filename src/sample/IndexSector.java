package sample;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by benjamin on 3/13/16.
 */
public class IndexSector implements Serializable, Asset {

    private String name;
    private Double sharePrice;
    private ArrayList<Equity> equities = new ArrayList<Equity>();

    public IndexSector(String name, ArrayList<Equity> equities){

        this.name = name;
        this.equities = equities;

        sharePrice = 0.0;

        for(int i = 0; i < equities.size(); i++){

            sharePrice += equities.get(i).getSharePrice();

        }

        sharePrice = sharePrice / equities.size();

    }

    public void updateSharePrice(){

        sharePrice = 0.0;

        for(int i = 0; i < equities.size(); i++){

            sharePrice += equities.get(i).getSharePrice();

        }

        sharePrice = sharePrice / equities.size();

    }

    public String getName(){
        return name;
    }

    public Double getSharePrice(){
        return sharePrice;
    }

}
