package sample.handleData;

import sample.Holdings.Equity;
import sample.Portfolio;

import java.util.List;
import java.util.Map;

/**
 * Created by Nicholas on 4/8/2016.
 */
public interface ImportInfo {
    public void parseImportFile(String filename, Portfolio currentAcoount);

    //public Map<String, List<String>> getMap(String filename);


}
