package sample;

/**
 * Created by Nick on 4/8/2016.
 */
public interface AssetVisitor {

    void visit(WatchedEquity e);

    void visit(WatchedMarketAverage avg);
}
