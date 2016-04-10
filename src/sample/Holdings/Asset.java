package sample.Holdings;

import sample.AssetVisitor;

/**
 * Created by benjamin on 3/13/16.
 */
public interface Asset {

    String getTickerSymbol();
    String getName();
    Double getSharePrice();

}
