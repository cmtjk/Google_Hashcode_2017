package hcde.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Endpoint {

    public final int id;
    public final int dcLat;
    public final int cachesCount;

    public final Map<Integer, Video> availableVideos;

    public Map<Integer, ConnectedCache> connectedCaches = new HashMap<>();
    // <VideoId, RequestCount>
    public Map<Integer, Integer> requests = new HashMap<>();
    public List<Request> requestsList;

    public Endpoint(int i, int d, int c, Map<Integer, Video> videos) {
        this.id = i;
        this.dcLat = d;
        this.cachesCount = c;
        this.availableVideos = videos;
    }

    public void addRequest(int id, int count) {
        if (requests.containsKey(id)) {
            requests.replace(id, requests.get(id) + count);
        } else {
            requests.put(id, count);
        }
    }

    public void sendRequest() {
        for (Map.Entry<Integer, Integer> request : requests.entrySet()) {
            for (ConnectedCache connectedCache : connectedCaches.values()) {
                int saving = request.getValue() * (dcLat - connectedCache.latency) / availableVideos.get(request.getKey()).size;
                connectedCache.cache.addRequest(new Request(this.id, request.getKey(), saving));
            }
        }
    }

}
