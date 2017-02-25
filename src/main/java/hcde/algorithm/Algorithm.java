package hcde.algorithm;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hcde.entities.Cache;
import hcde.entities.DataSet;
import hcde.entities.Endpoint;

public class Algorithm {

    public static Map<Integer, List<Integer>> analyze(DataSet dataSet) {

        for (Endpoint endpoint : dataSet.endpoints.values()) {
            endpoint.sendRequest();
        }

        for (Cache cache : dataSet.caches.values()) {
            cache.createInternalRanking();
        }

        for (Cache cache : dataSet.caches.values()) {
            cache.storeVideos();
        }

        Map<Integer, List<Integer>> result = new TreeMap<>();
        for (Cache cache : dataSet.caches.values()) {
            result.put(cache.id, cache.getStoredVideos());
        }

        return result;
    }

}
