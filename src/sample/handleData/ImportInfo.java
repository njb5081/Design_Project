package sample.handleData;

import sample.Holdings.Equity;

import java.util.List;
import java.util.Map;

/**
 * Created by Nicholas on 4/8/2016.
 */
public interface ImportInfo {
    public void parseEquityFile();

    public Map<String, List<String>> getIndexMap();

    public Map<String, Equity> getEquityMap();
}
