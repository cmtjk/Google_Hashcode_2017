package hcde.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Cache {

    public final int id;
    public final int size;
    public final Map<Integer, Video> availableVideos;
    // <VideoId, Request>
    public Map<Integer, Request> requests = new HashMap<>();
    // <Savings, VideoId>
    public Map<Integer, Request> savings;

    private List<Integer> storedVideos = new LinkedList<>();

    public Cache(int id, int size, Map<Integer, Video> videos) {
        this.id = id;
        this.size = size;
        this.availableVideos = videos;
    }

    public void addRequest(Request request) {
        if (requests.containsKey(request.videoId)) {
            // TODO Requests should be mapped to their merged counterpart
            Request mergedRequest = new Request(request.endpointId, request.videoId,
                    request.savings + requests.get(request.videoId).savings);
            requests.put(request.videoId, mergedRequest);
        } else {
            requests.put(request.videoId, request);
        }
    }

    public void createInternalRanking() {
        savings = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Request> entry : requests.entrySet()) {
            savings.put(entry.getValue().savings, entry.getValue());
        }

    }

    public void storeVideos() {
        int tempSize = size;
        for (Map.Entry<Integer, Request> savingEntry : savings.entrySet()) {
            int videoSize = availableVideos.get(savingEntry.getValue().videoId).size;
            if (!DataSet.globallyStoredRequests.contains(savingEntry.getValue()) && videoSize <= tempSize) {
                storedVideos.add(savingEntry.getValue().videoId);
                DataSet.globallyStoredRequests.add(savingEntry.getValue());
                tempSize -= videoSize;
            } 
        }

    }

    public List<Integer> getStoredVideos() {
        return storedVideos;
    }

}
